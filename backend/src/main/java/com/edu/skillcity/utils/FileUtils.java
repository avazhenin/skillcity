package com.edu.skillcity.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class FileUtils {

    @Value("${uploadFilePath}")
    private String uploadFilePath;

    public File saveFile(MultipartFile multipartFile) {

        UUID uuid = UUID.randomUUID();

        File file = new File(this.uploadFilePath + File.separator + multipartFile.getOriginalFilename() + uuid);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }

    public void deleteFile(String fileName) {
        try {
            Files.delete(Paths.get(this.uploadFilePath + File.separator + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
