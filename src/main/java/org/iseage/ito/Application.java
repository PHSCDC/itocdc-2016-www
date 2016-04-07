package org.iseage.ito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    private static String UPLOAD_DIR;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) throws IOException {
        setUploadDir("/var/uploads/");
        System.out.println(UPLOAD_DIR);
        SpringApplication.run(Application.class, args);
    }

    private static void setUploadDir(String uploadDir) {
        UPLOAD_DIR = uploadDir;
    }

    public static String getUploadDir() {
        return UPLOAD_DIR;
    }

}
