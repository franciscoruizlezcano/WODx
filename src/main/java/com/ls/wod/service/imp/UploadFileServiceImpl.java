package com.ls.wod.service.imp;

import com.ls.wod.service.UploadFileService;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author francisco
 */
@Service("UploadFileService")
public class UploadFileServiceImpl implements UploadFileService {

    private static final String UPLOAD_FOLDER = ".//src//main//resources//static//media//";
    private static final String URL = "/media/";

    @Override
    public String save(MultipartFile file) {
        String response = "";
        try {
            if (!file.isEmpty()) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
                Date date = new Date();

                String filename = formatter.format(date).toLowerCase() + file.getOriginalFilename();

                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_FOLDER + filename);
                Files.write(path, bytes);

                response = URL + filename;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean delete(String ubication) {
        boolean response = false;
        if (!ubication.isEmpty()) {
            try {
                String filename = ubication.replace(URL, "");
                Path path = Paths.get(UPLOAD_FOLDER + filename);
                response = Files.deleteIfExists(path);
            } catch (IOException ex) {
                Logger.getLogger(UploadFileServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return response;
    }

}
