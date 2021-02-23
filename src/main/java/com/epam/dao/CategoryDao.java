package com.epam.dao;

import com.epam.DBManager;
import com.epam.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {

    private static final String SQL_ALL_CATEGORIES = "SELECT * FROM categories";

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
            ex.printStackTrace();
        } finally {
            DBManager.getInstance().commitAndClose(con);
        }
        return categories;
    }

    static class CategoryMapper implements EntityMapper<Category> {

        @Override
        public Category mapRow(ResultSet rs) {
            try {
                Category category = new Category();
                category.setId(rs.getLong("category_id"));
                category.setName(rs.getString("name"));
                return category;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
