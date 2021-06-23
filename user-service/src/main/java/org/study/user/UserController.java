package org.study.user;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final UserAssembler userAssembler;
    private final NotificationClient notificationClient;
    private final CourseClient courseClient;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users")
    public Collection<UserDTO> findAll(@RequestParam(required = false) Long courseId) {
        Specification<User> specification = Specification.where(UserSpecification.getUsersByCourse(courseId));
        return userMapper.toDTO(userService.findAll(specification));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}")
    public UserType findById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserDTO userDto = userMapper.toDTO(user);
        return userAssembler.toModel(userDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    public UserType createNew(@RequestBody UserDTO newUserDto) {
        User user = userMapper.toMODEL(newUserDto);
        user = userService.save(user);
        UserDTO userDTO = userMapper.toDTO(user);
        return userAssembler.toModel(userDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{id}")
    public UserType updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User u = userMapper.toMODEL(userDTO);
        return userAssembler.toModel(userMapper.toDTO(userService.updateUser(id, u)));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/page")
    public Page<UserDTO> getPage(int pageNum, int size) {
        Pageable pageRequest = PageRequest.of(pageNum, size, Sort.by(Sort.Order.asc("firstName")));
        Page<User> page = userService.findAll(pageRequest);
        Collection<UserDTO> dtos = userMapper.toDTO(page.getContent());
        List<UserDTO> listDtos = new ArrayList<>(dtos);
        return new PageImpl<>(listDtos, pageRequest, page.getTotalElements());
    }

    @Data
    @EqualsAndHashCode(callSuper = false)
    @AllArgsConstructor
    public static class UserType extends RepresentationModel<UserType> {

        @JsonUnwrapped
        private UserDTO userDTO;
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/students/{id}/courses/{courceid}")
    public UserType assignStudentToCourse(@PathVariable Long id, @PathVariable Long courceid, @RequestBody(required = false) UserDTO inputDTO) {
        if (!courseClient.isActive(courceid)) {
            throw new RuntimeException("Course " + courceid + "either doesn't exist or is completed");
        }

        User user = userService.assignCourseToUser(id, courceid);

        // notification
        NotificationEvent event = new NotificationEvent();
        event.setRecipient(user.getLogin());
        event.setSubject("Course registration");
        event.setText("Hello " + user.getFirstName() + ",\n\r you're assigned to the course " + courseClient.getNameById(courceid) + " successfully");
        notificationClient.sendNotification(event);

        UserDTO userDTO = userMapper.toDTO(user);
        return userAssembler.toModel(userDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping("/teachers/{userId}/courses/{courseId}")
    public void assignTeacherToCourse(@PathVariable Long userId, @PathVariable Long courseId, @RequestBody(required = false) UserDTO inputDTO) {
        User user = null;
        try {
            user = userService.findById(userId);
            if (user != null && user.getRole() != Role.TEACHER) {
                log.error("User with id " + userId + " is not the teacher");
                throw new RuntimeException("User with id " + userId + " is not the teacher");
            }
        } catch (RuntimeException e) {
            log.error("User with id " + userId + " not found");
            return;
        }

        if (user != null && courseClient.assignTeacher2Course(courseId, userId)) {
            // notification
            NotificationEvent event = new NotificationEvent();
            event.setRecipient(user.getLogin());
            event.setSubject("Teacher registration");
            event.setText("Hello " + user.getFirstName() + ",\n\r you're assigned to the course " + courseClient.getNameById(courseId) + " as a teacher successfully");
            notificationClient.sendNotification(event);
        } else {
            throw new RuntimeException("Something went wrong...");
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{id}/courses")
    List<Long> getCoursesForTheUser(@PathVariable Long id) {
        User user = userService.findByIdWithCourses(id);
        return user.getCourses();
    }


    @Data
    static class NotificationEvent {
        private String recipient;
        private String subject;
        private String text;
    }

    @FeignClient("notification-service")
    interface NotificationClient {

        @PostMapping("/notificate")
        void sendNotification(NotificationEvent event);
    }

    @FeignClient("course-service")
    interface CourseClient {

        @GetMapping("/courses/{courseId}/active")
        boolean isActive(@PathVariable Long courseId);

        @GetMapping("/courses/{courseId}/name")
        String getNameById(@PathVariable Long courseId);

        @PostMapping("/courses/{courseId}/teacher/{teacherId}")
        boolean assignTeacher2Course(@PathVariable Long courseId, @PathVariable Long teacherId);
    }
}
