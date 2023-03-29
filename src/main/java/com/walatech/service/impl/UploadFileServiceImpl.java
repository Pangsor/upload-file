package com.walatech.service.impl;

import com.walatech.exception.ValidationException;
import com.walatech.service.UploadFileService;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UploadFileServiceImpl implements UploadFileService {
    @Override
    public String save(MultipartFile file, String bankName) throws ValidationException {
        if(!Objects.equals(file.getContentType(), "text/plain")){
            throw new ValidationException("Only text file are allowed");
        }
        if(bankName == null || bankName.isEmpty() || bankName.trim().isEmpty()){
            throw new ValidationException("Bank name should be not empty");
        }
        try {
            String contents = new String(file.getBytes());
            String[] lines = contents.split("\\r?\\n");
            List<String> messages = new ArrayList<>();
            List<String> result = new ArrayList<>();
            String greeting = "Selamat Siang Rekan Bank " + bankName;
            String header = "Mohon bantuan untuk Sign on pada envi berikut:";
            String MP = "",statusEnv = "";
            boolean addMesaage;
            for (String line : lines) {
                String[] lines2 = line.split(";");
                addMesaage = false;
                for (int j = 0; j < lines2.length; j++) {
                    if (lines2[j].equalsIgnoreCase(bankName)) {
                        addMesaage = true;
                    }
                    if (j == 2) {
                        MP = lines2[j];
                    }
                    if (j == 4) {
                        if (!lines2[j].equalsIgnoreCase("OFFLINE")) {
                            addMesaage = false;
                        }
                        statusEnv = lines2[j];
                    }
                }
                if (addMesaage) {
                    messages.add("- Envi MP Port " + MP + " terpantau " + statusEnv);
                }
            }
            if(messages.size() > 0){
                messages.add(0,greeting);
                messages.add(1,header);
                messages.add("Terima Kasih");
                for(String msg: messages){
                    System.out.println(msg);
                }
            }

            return StringUtils.join(messages);
        }catch (IOException e){
            throw new ValidationException("INTERNAL_SERVER_ERROR");
        }
    }
}
