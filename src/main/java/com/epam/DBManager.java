package com.epam;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class DBManager {

    private static final Logger log = Logger.getLogger(DBManager.class.getName());


    // //////////////////////////////////////////////////////////
    // singleton
    // //////////////////////////////////////////////////////////

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();
        return instance;
    }

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in your
     * WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return A DB connection.
     */
//    public Connection getConnection() throws SQLException {
//        Connection con = null;
//        try {
//            Context initContext = new InitialContext();
//            Context envContext = (Context) initContext.lookup("java:/comp/env");
//
//            // ST4DB - the name of data source
//            DataSource ds = (DataSource) envContext.lookup("jdbc/ST4DB");
//            con = ds.getConnection();
//        } catch (NamingException ex) {
//            log.warning("Cannot obtain a connection from the pool");
//        }
//        return con;
//    }

    private DBManager() {
    }


    // //////////////////////////////////////////////////////////
    // DB util methods
    // //////////////////////////////////////////////////////////

    /**
     * Commits and close the given connection.
     *
     * @param con Connection to be committed and closed.
     */
    public void commitAndClose(Connection con) {
        try {
            con.commit();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Rollbacks and close the given connection.
     *
     * @param con Connection to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection con) {
        try {
            con.rollback();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

/**************** THIS METHOD IS NOT USED IN THE PROJECT *******/
    /**
     * Returns a DB connection. This method is just for a example how to use the
     * DriverManager to obtain a DB connection. It does not use a pool
     * connections and not used in this project. It is preferable to use
     * {@link #getConnection()} method instead.
     *
     * @return A DB connection.
     */
    public static Connection getConnection() throws SQLException {

//        Connection connection = DriverManager
//                .getConnection("jdbc:postgresql://localhost:5432/Faculty?user=postgres&password=admin&ssl=false");
//        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//        connection.setAutoCommit(false);
//        return connection;
         Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Faculty?user=postgres&password=admin&ssl=false");
         connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
         connection.setAutoCommit(false);
         return connection;
    }

    /**************************************************************/
    public static void initDBSchema() throws IOException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection con = getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        ClassLoader classLoader = DBManager.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("./sql/schema.sql");
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        //Running the script
        sr.runScript(reader);
    }

    /*private static void executeDDL(String ddl, String delimiter) {
        Connection con = null;

        try {
            con = getConnectionWithDriverManager(); // get the connection

            // enable transaction
            con.setAutoCommit(false);

            Statement statement = con.createStatement();

            // for every DDL statement, execute it
            for (String sql : ddl.split(delimiter)) {
                if (sql != null && sql.trim().length() > 0) {
                    statement.executeUpdate(sql);
                }
            }

            statement.close();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception ignored) {
            }
        }
    }*/
}

