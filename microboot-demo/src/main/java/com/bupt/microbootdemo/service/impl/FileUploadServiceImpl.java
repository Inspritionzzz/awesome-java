package com.bupt.microbootdemo.service.impl;

import com.bupt.microbootdemo.service.FileUploadService;
import org.springframework.stereotype.Service;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public String indexTest() {
        return "project run success";
    }

    @Override
    public int fileUpload() {
        return 1;
    }

}
