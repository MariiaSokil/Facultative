package com.epam;

import org.apache.ibatis.jdbc.ScriptRunner;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

    private static final Logger log = Logger.getLogger(DBManager.class.getName());


    // //////////////////////////////////////////////////////////
    // singleton
    // //////////////////////////////////////////////////////////

    private static DBManager instance;
    private DBManager() {
    }
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
    public Connection getConnection() throws SQLException {
        Connection con = null;
        try {
            InitialContext cxt = new InitialContext();

            DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/Faculty" );
            con = ds.getConnection();
        } catch (NamingException ex) {
            log.log(Level.SEVERE,"Cannot obtain a connection from the pool", ex);
        }
        return con;
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
            log.log(Level.SEVERE,ex.getMessage(), ex);
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
            log.log(Level.SEVERE,ex.getMessage(), ex);
        }
    }

    /**
     * Initializing a DB schema.
     */
    public void initDBSchema() throws SQLException {
        Connection con = getConnection();
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        ClassLoader classLoader = DBManager.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("./sql/schema.sql");
        Reader reader = new BufferedReader(new InputStreamReader(inputStream));
        //Running the script
        sr.runScript(reader);
    }
}

