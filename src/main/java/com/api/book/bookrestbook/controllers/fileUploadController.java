package com.api.book.bookrestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.bookrestbook.helper.fileuploadhelper;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class fileUploadController {
    @Autowired
    private fileuploadhelper fileuploadhelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // System.out.println(file.getOriginalFilename());
        // System.out.println(file.getSize());
        // System.out.println(file.getContentType());
        // System.out.println(file.getName());

        try {

            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("file not found");
            }

            if (!file.getContentType().equals("image/png")) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only png file type accepted");
            }

            fileuploadhelper.uploadFile(file);

            boolean f = fileuploadhelper.uploadFile(file);
            if (f) {
                // return ResponseEntity.ok("File is successfully uploaded");

                return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image/")
                        .path(file.getOriginalFilename()).toUriString());

            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong please try again");
    }

}
