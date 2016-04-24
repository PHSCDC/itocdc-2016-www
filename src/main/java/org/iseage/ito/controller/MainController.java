package org.iseage.ito.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.iseage.ito.Application;
import org.iseage.ito.model.Comment;
import org.iseage.ito.model.User;
import org.iseage.ito.model.ActiveSession;
import org.iseage.ito.repository.CommentRepository;
import org.iseage.ito.repository.FileRepository;
import org.iseage.ito.repository.ImageRepository;
import org.iseage.ito.repository.UserRepository;
import org.iseage.ito.repository.ActiveSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.time.Instant;


@Controller
public class MainController {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ImageRepository imageRepository;
    
    @Autowired
    ActiveSessionRepository sessionRepository;

    @RequestMapping("")
    public String home() {
        return "index";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest req) {
		validateSession(req);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); //get logged in username
        System.out.println(req.getSession().getId() + "\t" + name + "\t" + "bib--big money");
        return "index";
    }

    @RequestMapping("/download")
    public String download(HttpServletRequest req) {
		validateSession(req);
        return "download";
    }

    @RequestMapping("/downloadgame")
    public void downloadGame(HttpServletRequest req, HttpServletResponse response) throws IOException {
		validateSession(req);
        InputStream inputStream = new FileInputStream(fileRepository.getGameDownloadPath());
        IOUtils.copy(inputStream, response.getOutputStream());
        response.setHeader("Content-Disposition", "attachment; filename=game.zip");
        response.flushBuffer();
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody User user, HttpServletResponse response) {
        boolean registrationWasSuccessful = userRepository.addUser(user.getUsername(), user.getPassword(), user.getEmail());
        if (registrationWasSuccessful) {
            response.setStatus(201);
        } else {
            response.setStatus(500);
        }
    }

    @RequestMapping("/screenshots")
    public String screenshots(ModelMap modelMap) {
        List<String> approvedImages = imageRepository.getApprovedImages();

        List<String> imageList = getImageNames(approvedImages);

        modelMap.put("images", imageList);
        return "screenshots";
    }

    @RequestMapping("/profile")
    public String profile(ModelMap modelMap) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        String email = userRepository.getUserEmail(username);
        modelMap.put("email", email);
        return "profile";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/changepass", method = RequestMethod.POST)
    public void changePassword(HttpServletRequest req, @RequestBody User user) {
		if(validateSession(req))
			userRepository.changePassword(user.getUsername(), user.getPassword());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/changeemail", method = RequestMethod.POST)
    public void changeEmail(HttpServletRequest req, @RequestBody User user) {
		if(validateSession(req))
			userRepository.changeEmail(user.getUsername(), user.getEmail());
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    public String comments(ModelMap modelMap) {
        List<Comment> commentList = commentRepository.getComments();
        modelMap.put("comments", commentList);
        return "comments";
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/addcomment", method = RequestMethod.POST)
    public void comments(HttpServletRequest req, @RequestBody Comment comment) {
		if(validateSession(req))
			commentRepository.addComment(comment.getComment(), comment.getAuthor());
    }

    @RequestMapping("/admin")
    public String admin(HttpServletRequest req, ModelMap modelMap) {
		if(validateSession(req))
		{
			List<User> userList = userRepository.getAllUsers();
			modelMap.put("users", userList);

			List<String> unApprovedImages = imageRepository.getUnApprovedImages();
			List<String> imageList = getImageNames(unApprovedImages);
			modelMap.put("images", imageList);
		}	

        return "admin";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/approve_image")
    public String approveImage(HttpServletRequest req, @RequestParam(value = "image") String imageName) {
		if(validateSession(req))
			imageRepository.approveImage(imageName);
        return "admin";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/reject_image")
    public String rejectImage(HttpServletRequest req, @RequestParam(value = "image") String imageName) {
		if(validateSession(req))
			imageRepository.rejectImage(imageName);
        return "admin";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/change_download_link", method = RequestMethod.GET)
    public void setDownloadLink(HttpServletRequest req, @RequestParam(value = "to") String link) {
		if(validateSession(req))
			fileRepository.setGameDownloadPath(link);
    }

    private List<String> getImageNames(List<String> referenceList) {
        List<String> imageList = new ArrayList<>();

        File imageFolder = new File(Application.getUploadDir());
        File[] images = imageFolder.listFiles();

        for (int i = 0; i < images.length; i++) {
            if (images[i].isFile() && referenceList.contains(images[i].getName())) {
                imageList.add(images[i].getName());
            }
        }
        return imageList;
    }
    
    private boolean validateSession(HttpServletRequest req)
    {
		ActiveSession sess = sessionRepository.getActiveSessionById(req.getSession().getId());
		if(sess != null)
		{
			if(sess.getIp() != ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr())
			{
				sessionRepository.rmActiveSession(sess.getId());
				return false;
			}
			else if(sess.getExpiry() < (Instant.now().getEpochSecond() + 3600))
			{
				sessionRepository.rmActiveSession(sess.getId());
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			if(req.isRequestedSessionIdValid())
			{
				sessionRepository.addActiveSession(req.getSession().getId(), ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr());
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}
