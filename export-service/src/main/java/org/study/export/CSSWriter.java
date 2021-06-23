package org.study.export;

import org.springframework.stereotype.Component;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class CSSWriter implements CourseWritter {
    @Override
    public void write(HttpServletResponse response, List<ExportController.CourseDTO> courseList) throws IOException {
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] nameMapping = {"id", "title", "duration", "startDate", "price", "teacher","status","enrollment"};

        csvWriter.writeHeader(csvHeader);

        for (ExportController.CourseDTO courseDTO : courseList) {
            csvWriter.write(courseDTO, nameMapping);
        }

        csvWriter.close();
    }
}
