package com.api.book.bootrestbook.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class fileuploadhelper {

    // public final String UPLOAD_DIR =
    // "/Users/kamalnayangupta/Downloads/SpringBootProject/bootrestbook/src/main/resources/static/image";
    public final String UPLOAD_DIR = new ClassPathResource("static/image/").getFile().getAbsolutePath();

    public fileuploadhelper() throws IOException {

    }

    public boolean uploadfile(MultipartFile multipartFile){

        boolean f = false;

        try {

            //
        //    InputStream is =  multipartFile.getInputStream();
 //        byte data[] = new byte[is.available()];
        //    is.read(data);

           //write
        //    FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename());
        //    fos.write(data);

        //    fos.flush();
        //    fos.close();

        Files.copy(multipartFile.getInputStream(), Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
           f=true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return f;

    }
}