package com.epam.dao;

import com.epam.DBManager;
import com.epam.model.Category;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;


/**
 * CategoryDao.
 * @author M.Sokil
 */
public class CategoryDao {

    private static final String SQL_ALL_CATEGORIES = "SELECT * FROM categories";
  //  private static final Logger log = Logger.getLogger(CategoryDao.class);
    /**
     * Return List of categories.
     * @return list of categories.
     */
    public List<Category> findAll() {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        List<Category> categories = new ArrayList<>();
        try {
            con = DBManager.getInstance().getConnection();
            CategoryDao.CategoryMapper mapper = new CategoryDao.CategoryMapper();
            pstmt = con.prepareStatement(SQL_ALL_CATEGORIES);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Category category = mapper.mapRow(rs);
                categories.add(category);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException ex) {
            DBManager.getInstance().rollbackAndClose(con);
  //          log.error(ex.getMessage(), ex);
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return categories;
    }
    /**
     * Extracts a category from the result set row.
     */
    static class CategoryMapper implements EntityMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs) {
            /*try {
                Category category = new Category();
                category.setId(rs.getLong("category_id"));
                category.setName(rs.getString("name"));
                return category;
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new IllegalStateException(e);
            }*/
            return null;
        }
    }
}
