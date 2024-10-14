package vn.hoidanit.laptopshop.service;

import jakarta.servlet.ServletContext;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class UploadService {

    private final ServletContext servletContext;

    public UploadService(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleUploadFile(MultipartFile file, String targetFolder) {

        // don't upload file if it's empty
        if (file.isEmpty()) {
            return "";
        }

        String rootPath = this.servletContext.getRealPath("/resources/images");
        String finalPath = "";
        try{
            byte[] bytes = file.getBytes();
            File dir = new File(rootPath + File.separator + targetFolder);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            finalPath = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            File serverFile = new File( dir.getAbsolutePath() + File.separator + finalPath);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return finalPath;
    }
}
