package com.bupt.awesomescaffold.controller;

import com.bupt.awesomescaffold.service.file.WriteToExcelService;
import com.bupt.awesomescaffold.utils.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

@RequestMapping("/index")
@RestController
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    WriteToExcelService writeToExcelService;

    @RequestMapping("/")
    String index() {
        LOGGER.info("IndexController...");
        writeToExcelService.ListWriter();
        return "just a test";
    }

    @RequestMapping("/index")
    String indexDemo() {
        return "just a test";
    }

    @RequestMapping(value = "/writefile", method = RequestMethod.POST)
    @ResponseBody
    String writeExcel() {
        LOGGER.info("IndexController...");
        writeToExcelService.ListWriter();
        return "success";
    }

    @GetMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        LinkedHashMap<String, String> titleMap = new LinkedHashMap<>(16);
        titleMap.put("id", "编号");
        titleMap.put("name", "姓名");
        titleMap.put("sex", "性别");
        titleMap.put("age", "年龄");
        ExcelUtil.exportExcel(response,
                titleMap,
                null,
                "sheet",
                "Excel模板");
    }
}
