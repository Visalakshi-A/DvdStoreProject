package com.ideas2it.dvdstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.common.enums.UserRole;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.UserService;

/**
 * Performs the login, signup, logout operation and redirects
 * to home page based on roles.
 * Opens login and signup form.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.common.enums.UserRole
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.logging.Logger
 * @see com.ideas2it.dvdstore.model.User
 * @see com.ideas2it.dvdstore.service.UserService
 */
@Controller
public class UserController {

    private static UserService userService;

    private static final String PAGE_ADMIN_HOME = "AdminHome";

    private static final String PAGE_LOGIN = "Login";

    private static final String PAGE_SIGNUP = "SignUp";

    private static final String URL_CUSTOMER_HOME = "/customer/";

    private static final String URL_REGISTER_FORM = "customer/register-form";

    private static final String URL_SIGNUP_FORM = "signup-form";

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Redirects to Login page.
     *
     * @return String value to forward to Jsp page.
     */
    @GetMapping("/")
    private ModelAndView dvdStoreHome() {
        return new ModelAndView(PAGE_LOGIN, Constants.LABEL_COMMAND, new User());
    }

    /**
     * Redirects to Home page based on the User Role.
     *
     * @return String value to redirect to Jsp page.
     */
    @GetMapping("home")
    private String home(HttpServletRequest request) {
        User user = (User) request.getSession(Boolean.FALSE).
            getAttribute(Constants.LABEL_USER);
        if (UserRole.ADMIN == user.getRole()) {
            return PAGE_ADMIN_HOME;
        } else {
            return Constants.LABEL_REDIRECT + URL_CUSTOMER_HOME;
        }
    }

    /**
     * Redirects to the Sign up form page.
     *
     * @return String value to forward to Jsp page.
     */
    @GetMapping("signup-form")
    private ModelAndView signUpForm() {
        return new ModelAndView(PAGE_SIGNUP, Constants.LABEL_COMMAND, new User());
    }

    /**
     * Gets the username and password and performs the login method.
     * If the user exits redirects to the Home page based on the user role
     * else to the Login page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return String value to redirect to Jsp page.
     */
    @PostMapping("login")
    private String login(@ModelAttribute()User user,
            HttpServletRequest request) {

        HttpSession session = request.getSession(Boolean.TRUE);
        try {
            user = userService.login(user);
            if (null != user) {
                user.setPassword(null);
                session.setAttribute(Constants.LABEL_USER, user);
                Logger.info(Constants.LOG_LOGGED_IN + user.getName());
            } else {
                session.setAttribute(Constants.LABEL_MESSAGE,
                                                 Constants.MESSAGE_RETRY_LOGIN);
            }
        } catch (DVDException e) {
            request.getSession().setAttribute(Constants.LABEL_MESSAGE,
                                                                e.getMessage());
        }
        return Constants.LABEL_REDIRECT + "/";
    }

    /**
     * Gets the username and password, checks if the user name already exists.
     * Gets the new input if already exists else invokes the signUp method.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return String value to redirect to Jsp page.
     */
    @PostMapping("signup")
    private String signUp(@ModelAttribute()User user,
            HttpServletRequest request) {

        HttpSession session = request.getSession(Boolean.TRUE);
        try {
            user = userService.signUp(user);
            if (null == user) {
                session.setAttribute(Constants.LABEL_MESSAGE,
                    Constants.MESSAGE_USER_NAME_ALREADY_EXISTS);
            } else {
                session.setAttribute(Constants.LABEL_USER, user);
                user.setPassword(null);
                return Constants.LABEL_REDIRECT + URL_REGISTER_FORM;
            }
        } catch (DVDException e) {
            session.setAttribute(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return Constants.LABEL_REDIRECT + URL_SIGNUP_FORM;
    }

    /**
     * Invalidates the session created during User login and redirects
     * to the login page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return String value to redirect to Jsp page.
     */
    @PostMapping("logout")
    private String logout(HttpServletRequest request) {

        String message = new String();
        HttpSession session = request.getSession(Boolean.FALSE);
        if (null != session) {
            session.invalidate();
            message = Constants.MESSAGE_LOGOUT_SUCCESSFUL;
        } else {
            message = Constants.MESSAGE_LOGIN_FIRST;
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + "/";
    }
}
