package com.ideas2it.dvdstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.common.enums.UserRole;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.model.User;

/**
 * Implements filter to prevent access to the page after logging out,
 * prevent admin from accessing customer pages and customer from accessing
 * Admin pages.
 *
 * @author Visalakshi
 */
public class AuthorizationFilter implements Filter {

    private static final String STATIC_FILE_EXTENSIONS = ".*(js|png)";

    private static final String URI_ADMIN_HOME = "/DvdStore/home";

    private static final String URI_CUSTOMER = "customer";

    private static final String URI_CUSTOMER_HOME = "/DvdStore/customer/";

    private static final String URI_CUSTOMER_DISPLAY = "customer/display";

    private static final String URI_DISPLAY = "display";

    private static final String URI_DVDSTORE = "DvdStore";

    private static final String URI_HOME = "home";

    private static final String URI_LOGIN = "login";

    private static final String URI_LOGOUT = "logout";

    private static final String URI_SIGNUP = "signup";

    private static final String FORWARD_SLASH = "/";

    public void init(FilterConfig filterConfig) throws ServletException {}

    /**
     * Prevents the pages from caching and storing in Client browser.
     * Allows login, signup pages and static files while Session is null
     * otherwise redirects to the Index page.
     * Prevents customer from accessing admin pages and vice versa.
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(Boolean.FALSE);

        User user = new User();
        if (null != session) {
            user = (User) session.getAttribute(Constants.LABEL_USER);
        }

        String uri = httpRequest.getRequestURI();
        System.out.println(uri);
        
        httpResponse.setHeader("Cache-Control", "no-cache");
        httpResponse.setHeader("Cache-Control", "no-store");
        httpResponse.setHeader("Pragma", "no-cache"); //HTTP 1.0 implementation
        //httpResponse.setDateHeader ("Expires", 0);

        if ( null != session && null != user ) {
            if ( uri.matches(STATIC_FILE_EXTENSIONS)
                    || uri.endsWith(URI_HOME) || uri.endsWith(URI_LOGOUT) ) {
                /* Allows static files, home page and logout page */
                chain.doFilter(request, response);
            } else if ( uri.endsWith(URI_DVDSTORE + FORWARD_SLASH) ) {
                /* Redirects to home page of the user */
                httpResponse.sendRedirect(URI_HOME);
            } else if ( UserRole.CUSTOMER == user.getRole() 
                    && ( !uri.contains(URI_CUSTOMER) 
                    || uri.contains(URI_CUSTOMER_DISPLAY) ) ) {
                /* Prevents customer from accessing other pages */
                session.setAttribute(Constants.LABEL_MESSAGE,
                                          Constants.MESSAGE_PAGE_NOT_AVAILABLE);
                Logger.info(Constants.MESSAGE_UNAUTHORIZED_ACCESS_BY_CUSTOMER 
                                                              + user.getName());
                httpResponse.sendRedirect(URI_CUSTOMER_HOME);
            } else if ( UserRole.ADMIN == user.getRole() 
                    && (!uri.contains(URI_DISPLAY) 
                    && uri.contains(URI_CUSTOMER)) ) {
                /* Prevents admin from opening customer pages */
                session.setAttribute(Constants.LABEL_MESSAGE,
                                          Constants.MESSAGE_PAGE_NOT_AVAILABLE);
                Logger.info(Constants.MESSAGE_UNAUTHORIZED_ACCESS_BY_ADMIN);
                httpResponse.sendRedirect(URI_ADMIN_HOME);
            } else {
                chain.doFilter(request, response);
            }
        } else if ( uri.contains(URI_LOGIN) || uri.contains(URI_SIGNUP) 
                || uri.endsWith(URI_DVDSTORE + FORWARD_SLASH) 
                || uri.matches(STATIC_FILE_EXTENSIONS) ) {
            chain.doFilter(request, response);
        } else {
            httpRequest.getSession().setAttribute(Constants.LABEL_MESSAGE,
                Constants.MESSAGE_LOGIN_FIRST);
            Logger.info(Constants.MESSAGE_UNAUTHORIZED_ACCESS);
            httpResponse.sendRedirect(FORWARD_SLASH + URI_DVDSTORE);
        }
    }

    public void destroy() {}
}
