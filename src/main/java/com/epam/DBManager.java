package com.epam;

import com.epam.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

import static com.epam.SQLConstant.PROPERTIES_FILE_PATH;
import static com.epam.SQLConstant.SQL_INSERT_USER;

public class DBManager {

    private final String url;

    private static DBManager dbManager;
    private static final Logger LOG = Logger.getLogger(DBManager.class.getName());

    private DBManager() {
        url = readUrl();
    }

    public static DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public static String readUrl() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(PROPERTIES_FILE_PATH.getValue())) {
            properties.load(inputStream);
            return properties.getProperty("connection.url");
        } catch (IOException e) {
            LOG.warning(e.getMessage());
        }

        return null;
    }

    public Connection getConnection(String url) throws SQLException {
        return DriverManager.getConnection(url);
    }





}

