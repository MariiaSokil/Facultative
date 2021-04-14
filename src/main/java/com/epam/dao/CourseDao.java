package com.epam.dao;

import com.epam.DBManager;
import com.epam.model.Category;
import com.epam.model.Course;
import com.epam.model.Status;
import com.epam.model.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.util.*;


/**
 * CourseDao.
 * @author M.Sokil
 */
public class CourseDao {
    private static final Logger log = Logger.getLogger(CourseDao.class);
    private static final String SQL_FIND_ALL =
            "SELECT * FROM courses c " +
                    "LEFT JOIN users u ON c.teacher=u.user_id " +
                    "LEFT JOIN categories ct ON c.category=ct.category_id " +
                    "ORDER BY DATE(start_date) DESC";

    private static final String SQL_FIND_ALL_BY_IDS =
            "SELECT * FROM courses c " +
                    "LEFT JOIN users u ON c.teacher=u.user_id " +
                    "LEFT JOIN categories ct ON c.category=ct.category_id " +
                    "WHERE c.id = ANY (?) " +
                    "ORDER BY DATE(start_date) DESC";

    private static final String SQL_FIND_ALL_COURSE_IDS_BY_STUDENT_ID =
            "SELECT course_id FROM users_courses " +
                    "WHERE userid =?";
    private static final String SQL_FIND_ALL_STUDENTS = "SELECT * FROM users_courses";

    private static final String SQL_UPDATE_COURSE =
            "UPDATE courses SET title=?, duration=?, price=?, start_date=?, teacher=?, status=?, category=?, enrollment=?" +
                    "	WHERE id=?";

    private static final String SQL_INSERT_COURSE = "INSERT INTO courses VALUES(DEFAULT,?,?,?,?,?,?,?,?)";

    private static final String SQL_ADD_USERS_TO_COURSES =
            "INSERT INTO users_courses(course_id, userid) VALUES(?,?)";
    private static final String SQL_REMOVE_USERS_FROM_COURSES =
            "DELETE FROM users_courses WHERE course_id=? AND userid=?";
    private static final String SQL_REMOVE_COURSE_FROM_COURSES =
            "DELETE FROM courses WHERE id=?";
    private static final String SQL_REMOVE_COURSE_ASSOCIATIONS =
            "DELETE FROM users_courses WHERE course_id=?";


    /**
     * Return List of courses by Student id.
     * @param studentId  Long.
     * @return list of courses.
     */
    public List<Course> findAllByStudentId(Long studentId) {
        List<Course> courses = new ArrayList<>();
        Course course = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();

            List<Long> ids = findAllCoursesByStudent(con, studentId);
            if (ids.size() == 0) {
                return courses;
            }

            CourseDao.CourseMapper mapper = new CourseDao.CourseMapper();
            pstmt = con.prepareStatement(SQL_FIND_ALL_BY_IDS);

            Array tagIdsInArray = con.createArrayOf("integer", ids.toArray());
            pstmt.setArray(1, tagIdsInArray);

            rs = pstmt.executeQuery();
            while (rs.next()) {
                course = mapper.mapRow(rs);
                courses.add(course);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return courses;
    }

    /**
     * Save a new course.
     *@param course Course.
     */
    public void saveNew(Course course) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_COURSE);
            int k = 1;
            pstmt.setString(k++, course.getTitle());
            pstmt.setInt(k++, course.getDuration());
            pstmt.setInt(k++, course.getPrice());
            pstmt.setDate(k++, Date.valueOf(course.getStartDate()));
           /* if (course.getTeacher() != null) {
                pstmt.setInt(k++, course.getTeacher().getId().intValue());
            } else {
                pstmt.setInt(k++, 0);
            }*/
            pstmt.setInt(k++, course.getStatus().getId());
           // pstmt.setInt(k++, course.getCategory().getId().intValue());
            pstmt.setInt(k++, course.getEnrollment());
            pstmt.executeUpdate();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update course.
     * If bindUser true, then add User to course.
     * If bindUser false, then delete User from course.
     * @param course Course.
     * @param bindUser  boolean.
     */
    public void updateCourse(Course course, boolean bindUser) {
        Connection con = null;
       /* try {
            con = DBManager.getInstance().getConnection();
            update(con, course);
            if (!course.getStudents().isEmpty()) {
                User user = course.getStudents().iterator().next();
                *//*if (bindUser) {
                    addUserToCourse(con,course.getId(), user.getId());
                } else {
                    removeUserFromCourse(con,course.getId(), user.getId());
                }*//*
            }
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }*/
    }

    /**
     * Update course.
     * @param course Course.
     */
    public void updateCourse(Course course) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            update(con, course);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Delete course.
     * If removeAssociations true, then delete all Users from course.
     * If removeAssociations false, just delete course.
     * @param courseId Long.
     * @param removeAssociations  boolean.
     */
    public void deleteCourse(Long courseId, boolean removeAssociations)  {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            if (removeAssociations) {
                removeAssociations(con, courseId);
            }
            remove(con, courseId);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Return List of courses.
     * If flag eagerStudents true, each course contains List of students.
     * If flag false, List of student is not completed
     * @param eagerStudents  boolean.
     * @return list of courses.
     */
    public List<Course> findAll(boolean eagerStudents) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            List<Course> courses = fetchAllCourses(con);
            if(eagerStudents && !courses.isEmpty()) {
                Map<Long,Set<User>> studentsByCourses = fetchAllUsers(con);
                mapCoursesWithStudents(courses, studentsByCourses);
            }
            return courses;
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }

        return new ArrayList<>();
    }

    private void removeUserFromCourse(Connection con, Long courseId, Long userId) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_REMOVE_USERS_FROM_COURSES);
        pstmt.setInt(1, courseId.intValue());
        pstmt.setInt(2, userId.intValue());
        pstmt.executeUpdate();
        pstmt.close();
    }

    private void removeAssociations(Connection con, Long courseId) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_REMOVE_COURSE_ASSOCIATIONS);
        pstmt.setInt(1, courseId.intValue());
        pstmt.executeUpdate();
        pstmt.close();
    }

    private void addUserToCourse(Connection con, Long courseId, Long userId) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_ADD_USERS_TO_COURSES);
        pstmt.setInt(1, courseId.intValue());
        pstmt.setInt(2, userId.intValue());
        pstmt.executeUpdate();
        pstmt.close();
    }

    private void update(Connection con, Course course) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_COURSE);
        int k = 1;
        pstmt.setString(k++, course.getTitle());
        pstmt.setInt(k++, course.getDuration());
        pstmt.setInt(k++, course.getPrice());
        pstmt.setDate(k++,  Date.valueOf(course.getStartDate()));
        /*if (course.getTeacher()!=null) {
            pstmt.setInt(k++, course.getTeacher().getId().intValue());
        } else {
            pstmt.setInt(k++, 0);
        }*/
        pstmt.setInt(k++, course.getStatus().getId());
       // pstmt.setInt(k++, course.getCategory().getId().intValue());
        pstmt.setInt(k++, course.getEnrollment());
        pstmt.setLong(k, course.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }

    private List<Long> findAllCoursesByStudent(Connection con, Long id) {
        List<Long> courses = new ArrayList<>();
        Course course = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = con.prepareStatement(SQL_FIND_ALL_COURSE_IDS_BY_STUDENT_ID);
            pstmt.setInt(1, id.intValue());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                courses.add(rs.getLong("course_id"));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        return courses;
    }

    private void mapCoursesWithStudents(List<Course> courses, Map<Long, Set<User>> studentsByCourses) {
        courses.forEach(course -> {
            if (studentsByCourses.containsKey(course.getId())) {
                Set<User> users = studentsByCourses.get(course.getId());
        //        course.getStudents().addAll(users);
            }
        });
    }

    private Map<Long,Set<User>> fetchAllUsers(Connection con) {
        Map<Long, Set<User>> userMap = new HashMap<>();
        /*try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL_STUDENTS);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Long courseId = rs.getLong("course_id");
                Long userId = rs.getLong("userid");
                User user = new User(userId);
                if (userMap.containsKey(courseId)) {
                    userMap.get(courseId).add(user);
                } else {
                    Set<User> set = new HashSet<>();
                    set.add(user);
                    userMap.put(courseId,set);
                }
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }*/
        return userMap;
    }

    private List<Course> fetchAllCourses(Connection con) {
        List<Course> courses = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Course course = new CourseMapper().mapRow(rs);
                courses.add(course);
            }
        } catch (SQLException ex) {
            log.error(ex.getMessage(), ex);
        }
        return courses;
    }

    private void remove(Connection con, Long courseId) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_REMOVE_COURSE_FROM_COURSES);
        pstmt.setInt(1, courseId.intValue());
        pstmt.executeUpdate();
        pstmt.close();
    }
    /**
     * Extracts a course from the result set row.
     */
    private static class CourseMapper implements EntityMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs) {
            try {
                Course course = new Course();
                course.setId(rs.getLong(Fields.COURSE_ID));
                course.setTitle(rs.getString(Fields.COURSE_TITLE));
                course.setDuration(rs.getInt(Fields.COURSE_DURATION));
             //   course.setCategory(toCategory(rs));
                course.setStatus(Status.of(rs.getInt(Fields.COURSE_STATUS)));
                course.setPrice(rs.getInt(Fields.COURSE_PRICE));
                course.setStartDate(rs.getDate(Fields.COURSE_START_DATE).toLocalDate());
              //  course.setTeacher(toTeacher(rs));
                course.setEnrollment(rs.getInt(Fields.ENROLLMENT));
                return course;
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new IllegalStateException(e);
            }
        }

        private User toTeacher(ResultSet rs) {
            UserDao.UserMapper mapper = new UserDao.UserMapper();
            return mapper.mapRow(rs);
        }

       /* private Category toCategory(ResultSet rs) throws SQLException {
            Category category = new Category(
                    rs.getLong(Fields.CATEGORY_ID),
                    rs.getString(Fields.CATEGORY_NAME));
            return category;
        }*/
    }
}



