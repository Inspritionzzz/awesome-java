package com.bupt.microbootdemo.controller;

import com.bupt.microbootdemo.service.FileUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.io.File;

/**
 * @Description: demo
 * Created by jason on 2022/9/16 14:04
 */

@Controller
@Api(tags = "FileUploadController", description = "文件上传")
public class FileUploadController {

    @Resource
    FileUploadService fileUploadService;

    @ApiOperation("controller测试")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index() {
        return fileUploadService.indexTest();
    }


    @ApiOperation("文件上传")
    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    public int fileUpload(@RequestParam("id") String id,
                          @RequestParam("userId") String userId,
                          @RequestParam("fileName") String fileName,
                          @RequestParam("type") String type,
                          @RequestParam("lastModifiedDate") String lastModifiedDate,
                          @RequestParam("size") int size,
                          @RequestParam("file") File file) throws Exception {


        return fileUploadService.fileUpload();
    }



}
