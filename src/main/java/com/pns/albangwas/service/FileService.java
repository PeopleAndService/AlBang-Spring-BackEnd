package com.pns.albangwas.service;

import com.pns.albangwas.common.FileProperties;
import com.pns.albangwas.exception.FileDownloadException;
import com.pns.albangwas.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Service
public class FileService {

    private final Path fileLocation;

    @Autowired
    public FileService(FileProperties properties) {
        this.fileLocation = Paths.get(properties.getUploadPath()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            throw new FileUploadException("fail to create file upload directory", e);
        }
    }

    public String storeFile(MultipartFile file) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmss_");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String storeTime = simpleDateFormat.format(timestamp);
        String fileName = storeTime + StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileUploadException("bad file name");
            }

            Path storeLocation = this.fileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), storeLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new FileUploadException("fail to upload file", e);
        }
    }

    public Resource loadFile(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileDownloadException("file not exist");
            }
        } catch(MalformedURLException e) {
            throw new FileDownloadException("file can't find", e);
        }
    }
}
