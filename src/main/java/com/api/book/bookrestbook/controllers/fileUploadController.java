package com.api.book.bookrestbook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.bookrestbook.helper.fileuploadhelper;

@RestController
public class fileUploadController {

    @Autowired
    private fileuploadhelper fileUploadHelper;

    @PostMapping("/upload-file")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File not found");
            }

            if (!file.getContentType().equals("image/png")) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only PNG file type accepted");
            }

            boolean isUploaded = fileUploadHelper.uploadFile(file);

            if (isUploaded) {
                String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/image/")
                        .path(file.getOriginalFilename())
                        .toUriString();

                return ResponseEntity.ok(fileUri);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong. Please try again");
    }
}
