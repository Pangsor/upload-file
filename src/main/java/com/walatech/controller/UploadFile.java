package com.walatech.controller;

import com.walatech.service.UploadFileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/uploadFile")
public class UploadFile {

    private UploadFileService uploadFileService;

    @PostMapping("/input/{bank}")
    public ResponseEntity<String> hanldleFileUpload(@RequestParam("file")MultipartFile file, @PathVariable("bank") String bankName) throws IOException {
        String aa = (String) uploadFileService.save(file,bankName);
        //return ResponseEntity.ok().body("file received successfully");
        return ResponseEntity.ok(aa);
    }
}
