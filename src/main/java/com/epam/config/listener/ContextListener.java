package com.epam.config.listener;

import com.epam.DBManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ContextListener implements ServletContextListener {
    // private static final Logger log = Logger.getLogger(ContextListener.class);
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        log("Servlet context destruction starts");
        // do nothing
        log("Servlet context destruction finished");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log("Servlet context initialization starts");
        try {
            initDBSchema();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        ServletContext servletContext = event.getServletContext();
        //initLog4J(servletContext);
        //initCommandContainer();
        initI18N(servletContext);

        log("Servlet context initialization finished");
    }

    /**
     * Initializes i18n subsystem.
     */

    private void initI18N(ServletContext servletContext) {
//        log.debug("I18N subsystem initialization started");
        log("I18N subsystem initialization started");

        String localesValue = servletContext.getInitParameter("locales");
        if (localesValue == null || localesValue.isEmpty()) {
 //           log.warn("'locales' init parameter is empty, the default encoding will be used");
            log("'locales' init parameter is empty, the default encoding will be used");
        } else {
            List<String> locales = new ArrayList<String>();
            StringTokenizer st = new StringTokenizer(localesValue);
            while (st.hasMoreTokens()) {
                String localeName = st.nextToken();
                System.out.println(localeName);
                locales.add(localeName);
            }

//            log.debug("Application attribute set: locales --> " + locales);
            log("Application attribute set: locales --> " + locales);
            servletContext.setAttribute("locales", locales);
        }

 //       log.debug("I18N subsystem initialization finished");
        log("I18N subsystem initialization finished");
    }

    /**
     * Initializes log4j framework.
     *
     * @param servletContext
     */
    private void initLog4J(ServletContext servletContext) {
/*        log("Log4J initialization started");
        try {
            PropertyConfigurator.configure(servletContext.getRealPath(
                    "WEB-INF/log4j.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        log("Log4J initialization finished");*/
    }

    /**
     * Initializes CommandContainer.
     * <p>
     * //* @param servletContext
     */
    private void initDBSchema() throws SQLException {
        log("DB schema initialization starts");
        DBManager.getInstance().initDBSchema();
        log("DB schema initialized");
    }

    private void log(String msg) {
        System.out.println("[ContextListener] " + msg);
    }
}
