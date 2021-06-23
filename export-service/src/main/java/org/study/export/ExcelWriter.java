package org.study.export;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExcelWriter implements CourseWritter{

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    @Override
    public void write(HttpServletResponse response, List<ExportController.CourseDTO> courseList) throws IOException {
        workbook= new XSSFWorkbook();

        writeHeaderLine();
        writeDataLines(courseList);

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }

    private void writeHeaderLine() {
       sheet = workbook.createSheet("Courses");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        for (int i=0; i< csvHeader.length; i++) {
            createCell(row, i,csvHeader[i] , style);
        }
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines(List<ExportController.CourseDTO> courseList) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ExportController.CourseDTO dto : courseList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, dto.getId(), style);
            createCell(row, columnCount++, dto.getTitle(), style);
            createCell(row, columnCount++, dto.getDuration(), style);
            createCell(row, columnCount++, dto.getStartDate().toString(), style);
            createCell(row, columnCount++, dto.getPrice(), style);
            createCell(row, columnCount++, dto.getTeacher(), style);
            createCell(row, columnCount++, dto.getStatus().name(), style);
            createCell(row, columnCount++, dto.getEnrollment(), style);

        }
    }
}
