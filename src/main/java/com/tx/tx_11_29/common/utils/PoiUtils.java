/*
package com.tx.tx_11_29.common.utils;

import com.richart.annotation.ExcelField;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class PoiUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(PoiUtils.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    public static <T> List<T> readExcel(String fileName, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        // 获取工作簿
        Workbook workBook = getWorkBook(fileName);
        // 读取数据
        List<T> list = readDataFromExcel(workBook, clazz);
        return list;
    }

    */
/**
     *
     * @param inputStream
     * @param extName 扩展名
     * @param clazz
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     *//*

    public static  final <T>  List<T> readExcel(InputStream inputStream, String extName, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        Workbook workbook = getWorkBook(inputStream, extName);
        return readDataFromExcel(workbook, clazz);
    }

    */
/**
     * 获取workbook
     *
     * @param fileName
     *//*

    private static Workbook getWorkBook(String fileName) {
        Workbook workbook = null;
        try {
            InputStream inputStream = new FileInputStream(fileName);
            if (fileName.endsWith(xls)) {
                // 2003
                // workbook = new HSSFWorkbook(inputStream);
            } else {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

        return workbook;
    }

    private static Workbook getWorkBook(InputStream inputStream, String fileName) {
        Workbook workbook = null;
        try {
            if (fileName.endsWith(xls)) {
                // 2003
                // workbook = new HSSFWorkbook(inputStream);
            } else {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        }

        return workbook;
    }


    */
/**
     * 从excel表格中读取数据
     *
     * @param workBook
     * @return
     *//*

    private static <T> List<T> readDataFromExcel(Workbook workBook, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        List<T> list = new ArrayList<T>();
        String[] headers = null;
        if (workBook != null) {
            for (int sheetNum = 0; sheetNum < workBook.getNumberOfSheets(); sheetNum++) {
                //获得当前sheet工作表
                Sheet sheet = workBook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }

                // 获取头部
                headers = getHeaders(sheet);
                // 将头部信息添加到list中
                //list.add(headers);

                //获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                    LOGGER.info("开始读取第" + rowNum + "行");
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    String[] cells = new String[row.getPhysicalNumberOfCells()];

                    // 创建对象
                    Object object = clazz.newInstance();

                    list.add(setValueOfFiled(row, clazz, headers));
                    LOGGER.info("第" + rowNum + "行的数据处理完毕");
                }
            }
            //workBook.close();
        }
        return list;
    }

    */
/**
     * 获取头信息
     *
     * @param sheet
     * @return
     *//*

    private static String[] getHeaders(Sheet sheet) {
        // 获取第一行的行号
        int firstRowNum = sheet.getFirstRowNum();
        // 获取第一行
        Row row = sheet.getRow(firstRowNum);

        if (row == null) {
            return new String[0];
        }
        // 定义存储头信息的数组
        String[] result = new String[row.getPhysicalNumberOfCells()];

        for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
            result[i] = getCellValue(row.getCell(i));
        }

        return result;
    }

    */
/**
     * 获取每一个单元格的值
     *
     * @param cell
     * @return
     *//*

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    */
/**
     * 根据头信息，给传入的class的相应的字段赋值
     * @param row
     * @param clazz
     * @param headers
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     *//*

    private static <T> T setValueOfFiled(Row row, Class<T> clazz, String[] headers) throws IllegalAccessException, InstantiationException {
        T object = clazz.newInstance();
        //循环当前行
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            String header = headers[cellNum];
            Cell cell = row.getCell(cellNum);
            // 获取所给类的所有字段
            Field[] declaredFields = clazz.getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                // 获取该注解的name值
                String name = declaredFields[i].getAnnotation(ExcelField.class).name();
                if (header != null && !header.equals("") && header.equals(name)) {
                    declaredFields[i].setAccessible(true);
                    declaredFields[i].set(object, getCellValue(cell));
                }
            }
        }

        return object;
    }

    */
/**
     * 导出Excel
     * @param sheetName sheet名称
     * @param title 标题
     * @param wb HSSFWorkbook对象
     * @return
     *//*

    public static <T> HSSFWorkbook getHSSFWorkbook(String sheetName, String []title, List<T> dataList, HSSFWorkbook wb) throws InvocationTargetException, IllegalAccessException {

        // 第一步，创建一个HSSFWorkbook，对应一个Excel文件
        if(wb == null){
            wb = new HSSFWorkbook();
        }

        // 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet(sheetName);

        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
        HSSFRow row = sheet.createRow(0);

        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        //声明列对象
        HSSFCell cell = null;

        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);

        //创建标题
        for(int i = 0;i < title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            cell.setCellStyle(style);
        }

        HSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFFont cellFont = wb.createFont();
        cellStyle.setFont(cellFont);
        //创建内容
        HSSFCell hssfCell = null;
        for(int i=0;i < dataList.size();i++){
            row = sheet.createRow(i + 1);
            T t = dataList.get(i);

            for (int j = 0; j < title.length; j++) {
                if (title[j] == null) {
                    continue;
                }
                hssfCell = row.createCell(j);
                hssfCell.setCellStyle(cellStyle);
                Object result = getValueByReflect(t, title[j], sheet, cellStyle, cellFont);
                if (result != null) {
                    if (result instanceof Integer) {
                        hssfCell.setCellValue((Integer) result);
                    } else if (result instanceof String) {
                        hssfCell.setCellValue(String.valueOf(result));
                    }
                } else {
                    hssfCell.setCellValue("");
                }
            }
        }
        return wb;
    }

    public static <T> HSSFWorkbook exportWorkBook(String sheetName, Class<T> clazz, List<T> dataList) throws InvocationTargetException, IllegalAccessException {
        // 获取所有的字段
        Field[] declaredFields = clazz.getDeclaredFields();
        String[] titles = new String[declaredFields.length];
        for (int i = 0; i < declaredFields.length; i++) {
            if (!declaredFields[i].isAnnotationPresent(ExcelField.class)) { // 没有加ExcelField注解，抛出异常
                throw new RuntimeException(declaredFields[i].getName() + "没有添加ExcelFied注解");
            } else {
                ExcelField excelField = declaredFields[i].getAnnotation(ExcelField.class);
                if (!excelField.exists()){continue;}
                titles[i] = excelField.name();
            }
        }

        return  getHSSFWorkbook(sheetName, titles, dataList, null);
    }

    public static  <T>  Object getValueByReflect(T t, String title, HSSFSheet sheet, HSSFCellStyle style, HSSFFont font) throws InvocationTargetException, IllegalAccessException {
        Class<?> clazz = t.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Object invoke = null;
        for (int i = 0; i < declaredFields.length; i++) {
            Field field = declaredFields[i];
            ExcelField excelField = field.getAnnotation(ExcelField.class);
            if (!excelField.exists()) {continue;}
            if (excelField.wrap()) {
                style.setWrapText(true);
            }
            // 设置字体
            String fontName = excelField.fontName();
            if (StringUtils.isNotBlank(fontName)) {
                font.setFontName(fontName);
            }
            if (excelField.fontSize() > 0) {
                font.setFontHeightInPoints(excelField.fontSize());
            }

            int columnWidth = excelField.columnWidth();
            if (columnWidth > 0 && sheet != null) {
                sheet.setColumnWidth(i, columnWidth);
            }
            if (title == null) {
                break;
            }
            if (title.equals(excelField.name())) {
                String getMethodName = "get" + firstLetterToUppercase(field.getName());
                for (Method method : declaredMethods) {
                    if (getMethodName.equals(method.getName())) {
                        invoke = method.invoke(t);
                        return invoke;
                    }
                }
                break;
            }
        }

        return invoke;
    }

    private static String firstLetterToUppercase(String str) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.length() > 1) {
            return (new StringBuilder()).append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
        }

        return str.toUpperCase();
    }

}
*/
