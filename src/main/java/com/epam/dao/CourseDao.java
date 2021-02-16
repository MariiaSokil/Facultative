package com.epam.dao;

import com.epam.DBManager;
import com.epam.model.Category;
import com.epam.model.Course;
import com.epam.model.Status;
import com.epam.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    private static final String SQL_FIND_COURSE_BY_TITLE =
            "SELECT * FROM users WHERE login=?";
    private static final String SQL_FIND_COURSE_BY_CATEGORY =
            "SELECT * FROM users WHERE login=?";

    private static final String SQL_FIND_COURSE_BY_ID =
            "SELECT * FROM courses WHERE id=?";

    private static final String SQL_FIND_ALL =
            "SELECT * FROM courses c " +
                    "LEFT JOIN users u ON c.teacher=u.user_id " +
                    "LEFT JOIN categories ct ON c.category=ct.category_id " +
                    "ORDER BY start_date";

    private static final String SQL_UPDATE_COURSE =
            "UPDATE courses SET title=?, duration=?, price=?, start_date=?, teacher=?, status=?, category=?, enrollment=?" +
                    "	WHERE id=?";

    private static final String SQL_FIND_ALL_BY_IDS =
            "SELECT * FROM courses c " +
                    "LEFT JOIN users u ON c.teacher=u.user_id " +
                    "LEFT JOIN categories ct ON c.category=ct.category_id " +
                    "WHERE c.id = ANY (?)";

    public Course findCourse(Long id) {
        Course course = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CourseDao.CourseMapper mapper = new CourseDao.CourseMapper();
            pstmt = con.prepareStatement(SQL_FIND_COURSE_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                course = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return course;
    }

    public List<Course> findAllWithLazyStudents() {
        List<Course> courses = new ArrayList<>();
        Course course = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CourseDao.CourseMapper mapper = new CourseDao.CourseMapper();
            pstmt = con.prepareStatement(SQL_FIND_ALL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                course = mapper.mapRow(rs);
                courses.add(course);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return courses;
    }

    public List<Course> findAllByStudentId(Long studentId) {
        List<Course> courses = new ArrayList<>();
        Course course = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            CourseDao.CourseMapper mapper = new CourseDao.CourseMapper();
            pstmt = con.prepareStatement(SQL_FIND_ALL_BY_IDS);
            List<Integer> ids = new ArrayList<>();
            ids.add(8);
            ids.add(9);
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
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return courses;
    }

    /**
     * Extracts a user from the result set row.
     */
    private static class CourseMapper implements EntityMapper<Course> {

        @Override
        public Course mapRow(ResultSet rs) {
            try {
                Course course = new Course();
                course.setId(rs.getLong(Fields.COURSE_ID));
                course.setTitle(rs.getString(Fields.COURSE_TITLE));
                course.setDuration(rs.getInt(Fields.COURSE_DURATION));
                course.setCategory(toCategory(rs));
                course.setStatus(Status.of(rs.getInt(Fields.COURSE_STATUS)));
                course.setPrice(rs.getInt(Fields.COURSE_PRICE));
                course.setStartDate(rs.getDate(Fields.COURSE_START_DATE).toLocalDate());
                course.setTeacher(toTeacher(rs));
                course.setEnrollment(rs.getInt(Fields.ENROLLMENT));
                return course;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }

        private User toTeacher(ResultSet rs) {
            UserDao.UserMapper mapper = new UserDao.UserMapper();
            return mapper.mapRow(rs);
        }

        private Category toCategory(ResultSet rs) throws SQLException {
            Category category = new Category(
                    rs.getLong(Fields.CATEGORY_ID),
                    rs.getString(Fields.CATEGORY_NAME));
            return category;
        }
    }

    public void updateCourse(Course course) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            update(con, course);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    private void update(Connection con, Course course) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_COURSE);
        int k = 1;
        pstmt.setString(k++, course.getTitle());
        pstmt.setInt(k++, course.getDuration());
        pstmt.setInt(k++, course.getPrice());
        pstmt.setDate(k++,  Date.valueOf(course.getStartDate()));
        pstmt.setInt(k++, course.getTeacher().getId().intValue());
        pstmt.setInt(k++, course.getStatus().getId());
        pstmt.setInt(k++, course.getCategory().getId().intValue());
        pstmt.setInt(k++, course.getEnrollment());
        pstmt.setLong(k, course.getId());
        pstmt.executeUpdate();
        pstmt.close();
    }
}



