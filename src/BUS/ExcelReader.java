package BUS;

import DTO.SalaryDTO;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

    // Interface để ánh xạ từng dòng Excel thành đối tượng bất kỳ
    public interface ExcelRowMapper<T> {
        T map(Row row);
    }

    // Hàm đọc Excel dùng chung cho mọi kiểu dữ liệu
    public static <T> List<T> readExcelFileGeneric(String filePath, ExcelRowMapper<T> mapper) {
        List<T> resultList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);  // Đọc sheet đầu tiên
            int rowCount = sheet.getLastRowNum();

            for (int i = 1; i <= rowCount; i++) { // Bỏ qua dòng tiêu đề (dòng 0)
                Row row = sheet.getRow(i);
                if (row != null) {
                    T mappedObject = mapper.map(row);
                    resultList.add(mappedObject);
                }
            }

        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file Excel: " + e.getMessage());
            e.printStackTrace();
        }

        return resultList;
    }
}
