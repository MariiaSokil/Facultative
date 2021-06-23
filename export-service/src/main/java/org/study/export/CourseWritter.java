package org.study.export;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface CourseWritter {

    String[] csvHeader = {"Course ID", "Title", "Duration", "Start Date", "Price", "Teacher","Status","Enrollment"};

    void write(HttpServletResponse response, List<ExportController.CourseDTO> courseList) throws IOException;
}
