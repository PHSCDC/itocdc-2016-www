package org.iseage.ito.controller;

import org.iseage.ito.Application;
import org.iseage.ito.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class UploadController {

    @Autowired
    ImageRepository imageRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        if (!file.isEmpty()) {
            try {
                String fileName = file.getOriginalFilename();
                if (!imageRepository.addImage(fileName)) {
                    redirectAttributes.addFlashAttribute("message", "An image with that name has already been uploaded!");
                    return "redirect:screenshots";
                }
                if (fileName.endsWith("png") || fileName.endsWith("jpg")) {
                    File uploadedFile = new File(Application.getUploadDir() + "/" + fileName);
                    BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                    FileCopyUtils.copy(file.getInputStream(), stream);
                    stream.close();
                    redirectAttributes.addFlashAttribute("message", "Upload successful! Your screenshot will be visible after it has been approved by an admin");
                } else {
                    redirectAttributes.addFlashAttribute("message", "Only files with type .png and .jpg are allowed!");
                }
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("message",
                    "Failed to upload " + file.getName() + " => " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            redirectAttributes.addFlashAttribute("message", "Please pick a file before clicking the upload button");
        }

        return "redirect:screenshots";
    }
}
