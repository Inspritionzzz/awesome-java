package com.bupt.awesomescaffold.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static org.apache.logging.log4j.util.Strings.EMPTY;

public class ExcelUtil {
    // excel 2003 版本 excel
    public static final String MICROSOFT_EXCEL_2003 = "xls";
    // excel 2007 版本 excel
    public static final String MICROSOFT_EXCEL_2007 = "xlsx";

    /**
     * 读取excel文件
     *
     * @param path 文件地址
     */
    public static List<List<String>> readExcel(String path) throws Exception {
        File excelFile = new File(path);
        if (!excelFile.exists()) {
            throw new FileNotFoundException("文件不存在");
        }
        FileInputStream fileInput = new FileInputStream(excelFile);
        /*
           根据文件后缀判断用xls或是xlsx处理
         */
        String[] postfixArray = path.split("\\.");
        int lastIndex = postfixArray.length - 1;
        String postfix = postfixArray[lastIndex];
        boolean excel2003 = Objects.equals(postfix, ExcelUtil.MICROSOFT_EXCEL_2003);
        if (!excel2003
                && !Objects.equals(postfix, ExcelUtil.MICROSOFT_EXCEL_2007)) {
            throw new Exception("文件不符合要求");
        }

        try (Workbook wb = excel2003 ? new HSSFWorkbook(fileInput) : new XSSFWorkbook(fileInput)) {
            List<List<String>> dataList = new ArrayList<>();
            /*
             * wb.getSheetAt(0) 简单的取第一个sheet的表格读取
             */
            for (Row row : wb.getSheetAt(0)) {
                List<String> rowList = new ArrayList<>();
                for (Cell cell : row) {
                    rowList.add(getCellValue(cell));
                }
                dataList.add(rowList);
            }
            return dataList;
        }
    }

    /**
     * @param response
     * @param titleMap  key-value格式的 excel
     * @param dataList
     * @param sheetName
     * @param fileName
     * @throws IOException
     */
    public static void exportExcel(HttpServletResponse response,
                                   LinkedHashMap<String, String> titleMap,
                                   List<List<String>> dataList,
                                   String sheetName,
                                   String fileName) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet(sheetName);
            List<String> keyList = new ArrayList<>();
            List<String> titleList = new ArrayList<>();
            titleMap.forEach((k, v) -> {
                keyList.add(k);
                titleList.add(v);
            });
            // 设置 key
            XSSFRow keyRow = sheet.createRow(0);
            keyRow.setZeroHeight(true);
            for (int i = 0; i < keyList.size(); i++) {
                String title = keyList.get(i);
                Cell headerCell = keyRow.createCell(i);
                headerCell.setCellValue(title);
            }
            // 设置标题
            XSSFRow header = sheet.createRow(1);
            ExcelUtil.setExcelTitle(titleList, workbook, sheet, header);
            ExcelUtil.setExcelData(workbook, sheet, dataList, 2);
            extracted(response, fileName, workbook);
        }
    }

    /**
     * Excel 表格导出, 格式为 xlsx
     *
     * @param titleList 标题集合
     * @param dataList  内容集合
     * @throws IOException
     */
    public static void exportExcel(HttpServletResponse response,
                                   List<String> titleList,
                                   List<List<String>> dataList,
                                   String sheetName,
                                   String fileName) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet(sheetName);
            // 设置标题
            XSSFRow header = sheet.createRow(0);
            ExcelUtil.setExcelTitle(titleList, workbook, sheet, header);
            ExcelUtil.setExcelData(workbook, sheet, dataList, 1);
            extracted(response, fileName, workbook);
        }
    }

    private static void extracted(HttpServletResponse response,
                                  String fileName,
                                  XSSFWorkbook workbook) throws IOException {
        String fileNameEncode = URLEncoder.encode(fileName, "UTF-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("content-disposition", "attachment;filename=" + fileNameEncode + ".xlsx");
        response.setCharacterEncoding("utf-8");
        //刷新缓冲
        response.flushBuffer();
        //workbook将Excel写入到response的输出流中，供页面下载该Excel文件
        workbook.write(response.getOutputStream());
    }

    /**
     * 设置标题
     */
    public static void setExcelTitle(List<String> titleList,
                                     XSSFWorkbook workbook,
                                     XSSFSheet sheet,
                                     XSSFRow header) {
        XSSFCellStyle headerStyle = ExcelUtil.getTitleCellStyle(workbook);
        for (int i = 0; i < titleList.size(); i++) {
            String title = titleList.get(i);
            sheet.setColumnWidth(i, 20 * 256 + 184);
            Cell headerCell = header.createCell(i);
            headerCell.setCellStyle(headerStyle);
            headerCell.setCellValue(title);
        }
    }

    public static void setExcelData(XSSFWorkbook workbook,
                                    XSSFSheet sheet,
                                    List<List<String>> dataList,
                                    int startRowIndex) {
        if (null != dataList && !dataList.isEmpty()) {
            XSSFCellStyle cellStyle = ExcelUtil.getCellStyle(workbook);
            for (int i = startRowIndex; i < dataList.size(); i++) {
                XSSFRow row = sheet.createRow(i);
                List<String> rowList = dataList.get(i);
                for (int j = 0; j < rowList.size(); j++) {
                    XSSFCell cell = row.createCell(j);
                    cell.setCellValue(rowList.get(j));
                    cell.setCellStyle(cellStyle);
                }
            }
        }
    }

    /**
     * 设置单元格格式
     */
    public static XSSFCellStyle getCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 16);
        font.setColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFont(font);
        cellStyle.setBorderBottom(BorderStyle.THIN); //下边框
        cellStyle.setBorderLeft(BorderStyle.THIN);//左边框
        cellStyle.setBorderTop(BorderStyle.THIN);//上边框
        cellStyle.setBorderRight(BorderStyle.THIN);//右边框
        return cellStyle;
    }

    /**
     * 设置标题的风格
     */
    public static XSSFCellStyle getTitleCellStyle(XSSFWorkbook workbook) {
        XSSFCellStyle cellStyle = ExcelUtil.getCellStyle(workbook);
        cellStyle.setFillForegroundColor(IndexedColors.INDIGO.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return cellStyle;
    }

    /**
     * 单元格不同格式获取值
     */
    public static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒");
                    return timeFormatter.format(cell.getLocalDateTimeCellValue());
                } else {
                    return cell.getNumericCellValue() + "";
                }
            case FORMULA:
                return cell.getCellFormula() + EMPTY;
            default:
                return EMPTY;
        }
    }
}
