package org.iseage.ito.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.iseage.ito.Application;
import org.iseage.ito.model.Comment;
import org.iseage.ito.model.User;
import org.iseage.ito.repository.CommentRepository;
import org.iseage.ito.repository.FileRepository;
import org.iseage.ito.repository.ImageRepository;
import org.iseage.ito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    ActiveSession sessionRepository;

    @RequestMapping("")
    public String home() {
        return "index";
    }

    @RequestMapping("/index")
    public String index(HttpServletRequest req) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); //get logged in username
        System.out.println(req.getSession().getId() + "\t" + name + "\t" + "bib--big money");
        return "index";
    }

    @RequestMapping("/download")
    public String download() {
        return "download";
    }

    @RequestMapping("/downloadgame")
    public void downloadGame(HttpServletResponse response) throws IOException {
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
    public void changePassword(@RequestBody User user) {
        userRepository.changePassword(user.getUsername(), user.getPassword());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/changeemail", method = RequestMethod.POST)
    public void changeEmail(@RequestBody User user) {
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
    public void comments(@RequestBody Comment comment) {
        commentRepository.addComment(comment.getComment(), comment.getAuthor());
    }

    @RequestMapping("/admin")
    public String admin(ModelMap modelMap) {
        List<User> userList = userRepository.getAllUsers();
        modelMap.put("users", userList);

        List<String> unApprovedImages = imageRepository.getUnApprovedImages();
        List<String> imageList = getImageNames(unApprovedImages);
        modelMap.put("images", imageList);

        return "admin";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/approve_image")
    public String approveImage(@RequestParam(value = "image") String imageName) {
        imageRepository.approveImage(imageName);
        return "admin";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/reject_image")
    public String rejectImage(@RequestParam(value = "image") String imageName) {
        imageRepository.rejectImage(imageName);
        return "admin";
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/change_download_link", method = RequestMethod.GET)
    public void setDownloadLink(@RequestParam(value = "to") String link) {
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
}
