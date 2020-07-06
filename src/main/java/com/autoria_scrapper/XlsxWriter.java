package com.autoria_scrapper;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class XlsxWriter {

    public void writeAutoData(List<AutoData> allAuto, String filePath, String sheetName) {
        HSSFWorkbook workbook = createWorkbook(filePath, sheetName);
        createNewSheet(workbook, sheetName);
        fillUpSheet(workbook.getSheet(sheetName), allAuto);
        try {
            workbook.write(new FileOutputStream(filePath));
            workbook.close();
        } catch (IOException e) {
            System.out.println("Can not write to file");
        }
    }

    private HSSFWorkbook createWorkbook(String filePath, String fileAppender) {
        HSSFWorkbook workbook = null;
        File file = new File("filePath" + fileAppender);
        try (FileInputStream fIS = new FileInputStream(file)) {
            workbook = (HSSFWorkbook) WorkbookFactory.create(fIS);
            return workbook;
        } catch (IOException e) {
            log.warn("can not open existing file: " + filePath + "\n creating new file: " + filePath + fileAppender);
        }

        return workbook;
    }

    private void createNewSheet(HSSFWorkbook book, String sheetName) {

        HSSFSheet sheet = book.createSheet(sheetName);
        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Ad id");

        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Ad name");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Seller name");

        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue("Price USD");

        cell = row.createCell(4, CellType.NUMERIC);
        cell.setCellValue("Price UAH");

        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("City");

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Description");

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Url");

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Photo urls");
    }

    private void fillUpSheet(Sheet sheet, List<AutoData> autoList) {
        int rownum = 1;
        for (AutoData auto : autoList) {
            Row row = sheet.createRow(rownum);

            Cell cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(auto.getAdId());

            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(auto.getAdName());

            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(auto.getSeller());

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(auto.getPriceUSD());

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(auto.getPriceUAH());

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(auto.getCity());

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(auto.getDescription());

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue(auto.getUrl());

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue(auto.getPhotosUrls());

            rownum++;
        }


    }
}
