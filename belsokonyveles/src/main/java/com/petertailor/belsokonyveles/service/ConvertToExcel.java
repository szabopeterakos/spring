package com.petertailor.belsokonyveles.service;

import com.petertailor.belsokonyveles.domain.Bill;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ConvertToExcel {

    private static String filename = "bookShelf";

    public void convertToExcel(Iterable<Bill> bills) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(filename);

        // body stripped
        Font fontBody = workbook.createFont();
        fontBody.setFontName("Courier New");
        fontBody.setColor(HSSFColor.BLACK.index);

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyle.setShrinkToFit(true);
        cellStyle.setFont(fontBody);

        CellStyle cellStyle2 = sheet.getWorkbook().createCellStyle();
        cellStyle2.setAlignment(CellStyle.ALIGN_RIGHT);
        cellStyle2.setShrinkToFit(true);
        cellStyle2.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle2.setFont(fontBody);

        //header style
        Font font = workbook.createFont();
        font.setFontName("Courier New");
        font.setColor(HSSFColor.WHITE.index);

        CellStyle cellStyle3 = sheet.getWorkbook().createCellStyle();
        cellStyle3.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle3.setShrinkToFit(true);
        cellStyle3.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
        cellStyle3.setFont(font);

        //HEADER
        for (int i = 0; i < 3; i++) {
            Row head = sheet.createRow(0);

            Cell cell = head.createCell(0);
            cell.setCellValue("Fizetési határidő");
            cell.setCellStyle(cellStyle3);

            cell = head.createCell(1);
            cell.setCellValue("Számla kelte");
            cell.setCellStyle(cellStyle3);

            cell = head.createCell(2);
            cell.setCellValue("Partner");
            cell.setCellStyle(cellStyle3);

            cell = head.createCell(3);
            cell.setCellValue("Bizonylatszám");
            cell.setCellStyle(cellStyle3);

            cell = head.createCell(4);
            cell.setCellValue("Összeg");
            cell.setCellStyle(cellStyle3);

            cell = head.createCell(5);
            cell.setCellValue("Fizetés jellege");
            cell.setCellStyle(cellStyle3);

            cell = head.createCell(6);
            cell.setCellValue("Megjegyzés");
            cell.setCellStyle(cellStyle3);

            cell = head.createCell(7);
            cell.setCellValue("Kiadás jellege");
            cell.setCellStyle(cellStyle3);

        }

        //BODY
        int rowInit = 2;
        for (Bill c : bills) {
            Row row = sheet.createRow(rowInit++);

            // deadline
            Cell cell = row.createCell(0);
            cell.setCellValue(c.getDeadline().toString());
            cell.setCellStyle(rowInit % 2 == 0 ? cellStyle2 : cellStyle);

            // release date
            cell = row.createCell(1);
            cell.setCellValue(c.getReleaseDate().toString());
            cell.setCellStyle(rowInit % 2 == 0 ? cellStyle2 : cellStyle);

            // partner
            cell = row.createCell(2);
            cell.setCellValue(c.getPartner().getName());
            cell.setCellStyle(rowInit % 2 == 0 ? cellStyle2 : cellStyle);

            // voucherNumber
            cell = row.createCell(3);
            cell.setCellValue(c.getVoucherNumber());
            cell.setCellStyle(rowInit % 2 == 0 ? cellStyle2 : cellStyle);

            // Amount
            cell = row.createCell(4);
            cell.setCellValue(c.getAmount());
            cell.setCellStyle(rowInit % 2 == 0 ? cellStyle2 : cellStyle);


            // payment type
            cell = row.createCell(5);
            cell.setCellValue(c.getPaymentType().getTypeName());
            cell.setCellStyle(rowInit % 2 == 0 ? cellStyle2 : cellStyle);

            // notes
            cell = row.createCell(6);
            cell.setCellValue(c.getNotes());
            cell.setCellStyle(rowInit % 2 == 0 ? cellStyle2 : cellStyle);

            // payment group
            cell = row.createCell(7);
            cell.setCellValue(c.getPaymant().getName());
            cell.setCellStyle(rowInit % 2 == 0 ? cellStyle2 : cellStyle);

        }

        sheet.autoSizeColumn(0, true); // column auto size
        sheet.autoSizeColumn(1, true); // column auto size
        sheet.autoSizeColumn(2, true); // column auto size
        sheet.autoSizeColumn(3, true); // column auto size
        sheet.autoSizeColumn(4, true); // column auto size
        sheet.autoSizeColumn(5, true); // column auto size
        sheet.autoSizeColumn(6, true); // column auto size
        sheet.autoSizeColumn(7, true); // column auto size

        // write like stream
        File file = new File("src/main/resources/"+filename+".xlsx");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("READY");
    }
}
