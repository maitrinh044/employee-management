package BUS;

import DTO.SalaryDTO;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*; // Thêm để dùng JOptionPane
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.awt.Desktop;
import java.io.File;


public class PdfExporter {

    // Hàm xuất danh sách SalaryDTO thành file PDF
    public void exportToPdf(List<SalaryDTO> salaryList, String filePath) {
        try {
            // Tạo tài liệu PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Tạo bảng
            PdfPTable table = new PdfPTable(4);
            table.addCell("Employee ID");
            table.addCell("Salary Amount");
            table.addCell("Month");
            table.addCell("Year");

            for (SalaryDTO salary : salaryList) {
                table.addCell(String.valueOf(salary.getEmployeeId()));
                table.addCell(String.valueOf(salary.getSalaryAmount()));
                table.addCell(String.valueOf(salary.getMonth()));
                table.addCell(String.valueOf(salary.getYear()));
            }

            document.add(table);
            document.close();

            // Thông báo thành công
            showExportSuccessDialog(filePath);

        } catch (DocumentException | FileNotFoundException e) {
            // Thông báo lỗi
            JOptionPane.showMessageDialog(null, "Lỗi khi tạo file PDF:\n" + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void showExportSuccessDialog(String filePath) {
        int option = JOptionPane.showOptionDialog(
                null,
                "Xuất PDF thành công!\nBạn có muốn mở file?",
                "Xuất thành công",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Mở file", "Đóng"},
                "Mở file"
        );

        if (option == JOptionPane.YES_OPTION) {
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    Desktop.getDesktop().open(file); // Mở bằng trình đọc PDF mặc định
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy file để mở!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Không thể mở file: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
