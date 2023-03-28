package com.walatech.service.impl;

import com.walatech.service.UploadFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Override
    public Object save(MultipartFile file, String bankName) {
        if(!file.getContentType().equals("text/plain")){
            return ResponseEntity.badRequest().body("Only text file are allowed");
        }
        String getContentType = file.getContentType();
        try {
            String contents = new String(file.getBytes());
            String[] lines = contents.split("\\r?\\n");
            System.out.println(lines);
            StringBuilder sb = new StringBuilder();
            List<String> messages = new ArrayList<>();
            String MP = "",statusEnv = "";
            boolean addMesaage;
            for(int i = 0; i < lines.length;i++){
                String[] lines2 = lines[i].split(";");
                addMesaage = false;
                for(int j = 0; j <lines2.length;j++){
                    String vv = lines2[j];
                    if(lines2[j].equalsIgnoreCase(bankName)){
                        addMesaage = true;
                    }
                    if(j == 2){
                        MP = lines2[j];
                    }
                    if(j == 4){
                        statusEnv = lines2[j];
                    }
                }
                if(addMesaage){
                    messages.add("- Envi MP Port " + MP + " terpantau" + statusEnv);
                }
            }
            System.out.println(messages);
            return messages;
        }catch (IOException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }
}
