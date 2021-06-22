package org.study.export;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Component
public class CSSWriter implements CourseWritter {
    @Override
    public void write(Writer writer, List<ExportController.CourseDTO> courseList) throws IOException {
        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);
        String[] csvHeader = {"Course ID", "Title", "Duration", "Start Date", "Price", "Teacher","Status","Enrollment"};
        String[] nameMapping = {"id", "title", "duration", "startDate", "price", "teacher","status","enrollment"};

        csvWriter.writeHeader(csvHeader);

        for (ExportController.CourseDTO courseDTO : courseList) {
            csvWriter.write(courseDTO, nameMapping);
        }

        csvWriter.close();
    }
}
