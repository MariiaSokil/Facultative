package com.epam.dao;


import com.epam.DBManager;
import com.epam.model.Role;
import com.epam.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * CategoryDao.
 *
 * @author M.Sokil
 */
public class UserDao {
    private static final Logger log = Logger.getLogger(UserDao.class);


    private static final String SQL_FIND_USER_BY_LOGIN =
            "SELECT * FROM users WHERE login=?";

    private static final String SQL_FIND_USER_BY_ID =
            "SELECT * FROM users WHERE id=?";

    private static final String SQL_UPDATE_USER =
            "UPDATE users SET password=?, first_name=?, last_name=?, locale_name=?" +
                    "	WHERE id=?";

    private static final String SQL_FIND_USERS_BY_ROLE = "SELECT * FROM users WHERE role_id=?";
    private static final String SQL_INSERT_USER = "INSERT INTO users VALUES(DEFAULT,?,?,?,?,?,?,?)";

    /**
     * Returns a user with the given identifier.
     *
     * @param id User identifier.
     * @return User entity.
     */
    public User findUser(Long id) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    /**
     * Returns a user with the given login.
     *
     * @param login User login.
     * @return User entity.
     */
    public User findUserByLogin(String login) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next())
                user = mapper.mapRow(rs);
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return user;
    }

    /**
     * Update user.
     *
     * @param user user to update.
     */
    public void updateUser(User user) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            updateUser(con, user);
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }

    /**
     * Update user.
     *
     * @param user user to update.
     * @throws SQLException
     */
    public void updateUser(Connection con, User user) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);
        /*int k = 1;
        pstmt.setString(k++, user.getPassword());
        pstmt.setString(k++, user.getFirstName());
        pstmt.setString(k++, user.getLastName());;
        pstmt.setLong(k, user.getId());
        pstmt.executeUpdate();
        pstmt.close();*/
    }

    /**
     * Find all users by role.
     *
     * @param role Role model.
     * @return List of User.
     */
    public List<User> findAllUsersByRole(Role role) {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<User> users = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            UserMapper mapper = new UserMapper();
            pstmt = con.prepareStatement(SQL_FIND_USERS_BY_ROLE);
            pstmt.setLong(1, role.getId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                user = mapper.mapRow(rs);
                users.add(user);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return users;
    }

    /**
     * Saving a new user.
     *
     * @param user user to update.
     */
    public void saveNew(User user) {
        Connection con = null;
        try {
            con = DBManager.getInstance().getConnection();
            /*PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER);
            int k = 1;
            pstmt.setString(k++, user.getLogin());
            pstmt.setString(k++, user.getPassword());
            pstmt.setString(k++, user.getFirstName());
            pstmt.setString(k++, user.getLastName());
            pstmt.setBoolean(k++, user.isStudent());
            pstmt.setBoolean(k++, user.isBlocked());
            pstmt.setInt(k++, user.getRole().getId());


            pstmt.executeUpdate();
            pstmt.close();*/
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
            log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
    }


    /**
     * Extracts a user from the result set row.
     */
    static class UserMapper implements EntityMapper<User> {

        @Override
        public User mapRow(ResultSet rs) {
            try {
                User user = new User();
               /* user.setId(rs.getLong(Fields.USER_ID));
                user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
                user.setLastName(rs.getString(Fields.USER_LAST_NAME));
                user.setRole(Role.of(rs.getInt(Fields.USER_ROLE_ID)));
                user.setLogin(rs.getString(Fields.USER_LOGIN));
                user.setPassword(rs.getString(Fields.USER_PASSWORD));
                user.setStudent(rs.getBoolean(Fields.USER_STUDENT));
                user.setBlocked(rs.getBoolean(Fields.USER_BLOCKED));*/
                return user;
            } finally {
                /*log.error(e.getMessage(), e);
                throw new IllegalStateException(e);
            }*/
            }
        }
    }
}

