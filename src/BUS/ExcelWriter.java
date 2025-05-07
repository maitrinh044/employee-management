/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

/**
 *
 * @author MaiTrinh
 */

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    // Interface ánh xạ dữ liệu đối tượng -> mảng String (để ghi từng ô)
    public interface ExcelRowWriter<T> {
        String[] map(T object);
    }

    // Ghi file Excel từ danh sách dữ liệu
    public static <T> void writeExcelFileGeneric(String filePath, List<T> dataList, String[] headers, ExcelRowWriter<T> writer) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        int rowIndex = 0;

        // Ghi dòng tiêu đề
        Row headerRow = sheet.createRow(rowIndex++);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }

        // Ghi dữ liệu
        for (T data : dataList) {
            Row row = sheet.createRow(rowIndex++);
            String[] values = writer.map(data);
            for (int i = 0; i < values.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(values[i]);
            }
        }

        // Auto resize
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Ghi file
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            workbook.write(fos);
            System.out.println("Xuất Excel thành công: " + filePath);
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file Excel: " + e.getMessage());
        }
    }
}
