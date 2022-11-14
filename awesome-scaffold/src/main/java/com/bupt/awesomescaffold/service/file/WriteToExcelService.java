package com.bupt.awesomescaffold.service.file;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class WriteToExcelService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WriteToExcelService.class);



    public void ListWriter() {
        List<String> row1 = CollUtil.newArrayList("aa", "bb", "cc", "dd");
        List<String> row2 = CollUtil.newArrayList("aa1", "bb1", "cc1", "dd1");
        List<String> row3 = CollUtil.newArrayList("aa2", "bb2", "cc2", "dd2");
        List<String> row4 = CollUtil.newArrayList("aa3", "bb3", "cc3", "dd3");
        List<String> row5 = CollUtil.newArrayList("aa4", "bb4", "cc4", "dd4");

        List<List<String>> rows = CollUtil.newArrayList(row1, row2, row3, row4, row5);

        String reportPath = System.getProperty("user.dir") + File.separator;
        String fileName = "writeTest.xlsx";

        // ExcelWriter writer = ExcelUtil.getWriter("d:/writeTest.xlsx");
        if (FileUtil.listFileNames(reportPath).contains(fileName)) {
            FileUtil.del(reportPath + fileName);
        }

        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter(reportPath + fileName);
        // ExcelWriter writer = new ExcelWriter("d:/writeTest.xls");

        // 跳过当前行，既第一行，非必须，在此演示用
        writer.passCurrentRow();
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(row1.size() - 1, "测试标题");
        // 一次性写出内容，强制输出标题
        writer.write(rows, true);
        // 关闭writer，释放内存
        writer.close();
    }

    public static void main(String[] args) {
        // ListWriter();
    }

}
