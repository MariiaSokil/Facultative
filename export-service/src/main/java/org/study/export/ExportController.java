package org.study.export;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ExportController {

    private final CourseClient courseClient;
    private final CourseWritter courseWritter;

    @GetMapping("/export/csv")
    public void exportToCsv(HttpServletResponse response, @RequestParam (required = false)Long id) throws IOException {
        response.setContentType("text/csv");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".csv";
        response.setHeader(headerKey, headerValue);

        List<CourseDTO> listCourses = courseClient.getCourses(id);
        courseWritter.write(response, listCourses);
    }

    @GetMapping("/export/xls")
    public void exportToExcel(HttpServletResponse response, @RequestParam (required = false)Long id) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<CourseDTO> listCourses = courseClient.getCourses(id);
        CourseWritter courseWritter = new ExcelWriter();

        courseWritter.write(response, listCourses);
    }


    @FeignClient("course-service")
    interface CourseClient {

        @GetMapping("/courses")
        List<CourseDTO> getCourses(@RequestParam Long student);
    }

    @Data
    public static class CourseDTO {
        private Long id;
        private String title;
        private CategoryDTO category;
        private int duration;
        private LocalDate startDate;
        private Integer price;
        private Long teacher;
        private Status status;
        private int enrollment;
    }
    @Data
    public static class CategoryDTO {
        private Long id;
        private String name;
    }

    public enum Status {
        COMING_SOON(0),ONGOING(1),COMPLETED(2);

        private final int id;

        Status(int id){
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static Status of(int id) {
            return Arrays.stream(Status.values())
                    .filter(status -> status.getId() == id)
                    .findAny()
                    .orElseThrow(() -> new RuntimeException("Wrong argument"));
        }
    }
}
