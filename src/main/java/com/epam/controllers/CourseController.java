package com.epam.controllers;

import com.epam.dto.CourseDTO;
import com.epam.mappers.impl.CourseMapper;
import com.epam.model.Course;
import com.epam.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@Log4j2
@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses")
    public Collection<CourseDTO> findAll() {
            return courseMapper.toDTO(courseService.findAll());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/courses/{id}")
    public CourseDTO findById(@PathVariable Long id) {
        return courseMapper.toDTO(courseService.findById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/courses")
    public CourseDTO createNew(@RequestBody Course newCourse) {
        return courseMapper.toDTO(courseService.save(newCourse));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/courses/{id}")
    public void deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/courses/{id}")
    public CourseDTO updateCourse(@PathVariable Long id, CourseDTO courseDTO) {
        Course course = courseMapper.toMODEL(courseDTO);
        return courseMapper.toDTO(courseService.updateCourse(id, course));
    }
}


/**
 * Apply user to a course.
 * If removeFlag is present and equals true, then unassign user from a course.
 * If user  is not logged in, redirects to the page login.
 * <p>
 * If removeFlag is not present, then apply user to a course.
 * If user  is not logged in, redirects to the page login.
 *//*
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String courseId = request.getParameter("id");
        String title = request.getParameter("title");
        String categoryId = request.getParameter("category_id");
        String categoryName = request.getParameter("category_name");
        String duration = request.getParameter("duration");
        String enrollment = request.getParameter("enrollment");
        String status = request.getParameter("status");
        String price = request.getParameter("price");
        String teacherId = request.getParameter("teacher_id");
        String startDate = request.getParameter("start_date");
        String students = request.getParameter("students");
        String removeFlag = request.getParameter("remove");

        if (user!= null) {
            Course course = new Course();
            course.setId(new Long(courseId));
            course.setTitle(title);
      //      course.setCategory(new Category(new Long(categoryId), categoryName));
            course.setDuration(new Integer(duration));
            course.setPrice(new Integer(price));
            course.setStatus(Status.valueOf(status));
       //     course.setTeacher(new User(new Long(teacherId)));
            course.setStartDate(toLocalDate(startDate));
            Set<User> users = new HashSet<>();
            users.add(user);
      //      course.setStudents(users);
            if (removeFlag !=null && "true".equals(removeFlag)) {
                course.setEnrollment(new Integer(enrollment) - 1);
                courseService.updateCourse(course, false);
            } else {
                course.setEnrollment(new Integer(enrollment) + 1);
                courseService.updateCourse(course, true);
            }

            //redirect to student page
            request.setAttribute("user", user);
          //  request.setAttribute("courses", courseService.findAllByStudentId(user.getId()));
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/student.jsp");
            rd.forward(request, response);
        } else {

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
            request.setAttribute("message", "Either user name or password is wrong");
            rd.include(request, response);
        }
    }

    private LocalDate toLocalDate(String stringDate) {
        DateTimeFormatter formatter;
        if (stringDate.contains(".")) {
            //15.05.2021
            formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        } else {
            //suppose 2021-05-15
            formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        }
        return LocalDate.parse(stringDate, formatter);
    }*/
