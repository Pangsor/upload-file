package com.walatech.service;

import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    Object save(MultipartFile multipartFile,String bankName);
}
