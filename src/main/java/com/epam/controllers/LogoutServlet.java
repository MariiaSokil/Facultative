package com.epam.controllers;

import org.apache.log4j.Logger;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


/**
 * LogoutServlet.
 * @author M.Sokil
 */

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(LogoutServlet.class.getName());

    public LogoutServlet() {
    }
    /**
     * Logout user and redirect to home page.
     * Invalidate a session.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    log.info("JSESSIONID=" + cookie.getValue());
                    break;
                }
            }
        }
        //invalidate the session if exists
        HttpSession session = request.getSession(false);
        log.info("Session-User=" + session.getAttribute("user"));
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("index.jsp");
    }
}