package com.ls.wod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author francisco
 */

public interface UploadFileService {
    String save(MultipartFile file);
    
    boolean delete(String ubication);
}
