package com.walatech.service;

import com.walatech.exception.ValidationException;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileService {
    String save(MultipartFile multipartFile, String bankName) throws ValidationException;
}
