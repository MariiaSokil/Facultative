package org.study.export;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public interface CourseWritter {

    void write(Writer writer, List<ExportController.CourseDTO> courseList) throws IOException;
}
