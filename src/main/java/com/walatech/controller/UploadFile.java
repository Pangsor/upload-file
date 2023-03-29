package com.walatech.controller;

import com.walatech.exception.ValidationException;
import com.walatech.service.UploadFileService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/uploadFile")
public class UploadFile {

    private UploadFileService uploadFileService;

    @PostMapping("/input/{bank}")
    public String hanldleFileUpload(@RequestParam("file")MultipartFile file, @PathVariable("bank") String bankName) throws ValidationException {
        return uploadFileService.save(file,bankName);
    }
}
