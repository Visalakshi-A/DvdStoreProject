package com.ideas2it.dvdstore.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;

/**
 * Performs all operations of getting input from the user to
 * perform all the DVD related operations and displaying the result.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.service.CategoryService
 * @see com.ideas2it.dvdstore.service.impl.CategoryServiceImpl
 */
@Controller
@RequestMapping("category")
public class CategoryController {

    private static final String PAGE_HOME = "AdminHome";

    private static final String PAGE_VIEW_CATEGORIES = "ViewCategories";

    private static final String URL_DISPLAY = "display";

    private static final String URL_RESTORE_DISPLAY = "restore-display";

    private CategoryService categoryService = new CategoryServiceImpl();

    /**
     * To redirect to the Home page.
     */
    @GetMapping("home")
    private String home() {
        return PAGE_HOME;
    }

    /**
     * Gets the new value to be inserted and checks if the value already exists
     * then invokes the services method to insert the category.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return String value containing the Redirect url.
     */
    @PostMapping("insert")
    private String insert(HttpServletRequest request) {
        String category = request.getParameter(Constants.LABEL_VALUE);
        String message = new String();
        try {
            Boolean status = categoryService.checkAlreadyExists(category);
            if (null == status) {
                if (categoryService.addCategory(category)) {
                    message = Constants.MESSAGE_ADD_CATEGORY_SUCCESS;
                }
            } else if (status) {
                message = Constants.MESSAGE_CATEGORY_ALREADY_EXISTS;
            } else {
                message = Constants.MESSAGE_RESTORE_CATEGORY;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_DISPLAY;
    }

    /**
     * Retrieves the categories list and adds the request object.
     *
     * @param request
     *        HttpServletRequest object.
     * @param response
     *        HttpServletResponse object.
     */
    @GetMapping("display")
    private ModelAndView display() {

        List<Category> categories = new ArrayList<Category>();
        try {
            categories = categoryService.getCategories(Boolean.TRUE);
        } catch (DVDException e) {
            return new ModelAndView(PAGE_HOME, Constants.LABEL_MESSAGE,
                                                                e.getMessage());
        }
        return new ModelAndView(PAGE_VIEW_CATEGORIES,
            Constants.LABEL_CATEGORIES, categories);
    }

    /**
     * Retrieves the categories list and adds the request object.
     *
     * @return ModelAndView object containing Inactive categories list.
     */
    @GetMapping("restore-display")
    private ModelAndView restoreDisplay() {

        Boolean status = Boolean.FALSE;
        ModelAndView model = new ModelAndView(PAGE_VIEW_CATEGORIES);
        model.addObject(Constants.LABEL_STATUS, status);
        try {
            model.addObject(Constants.LABEL_CATEGORIES,
                categoryService.getCategories(status));
        } catch (DVDException e) {
            model.setViewName(PAGE_HOME);
            model.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return model;
    }

    /**
     * Retrieves the DVDs list having the same category and forwards to 
     * display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return ModelAndView object containing list of DVDs forwarding to
     *         Display page.
     */
    @PostMapping("display-dvds-by-category")
    private ModelAndView getDvdsByCategory(HttpServletRequest request) {

        Category category = new Category();
        ModelAndView model = new ModelAndView(PAGE_VIEW_CATEGORIES);
        try {
            category = categoryService.getCategoryById(Integer.
                            parseInt(request.getParameter(Constants.LABEL_ID)));
            model.addObject(Constants.LABEL_VALUE, category.getValue());
            model.addObject(Constants.LABEL_DVDS, category.getDvds());
        } catch (DVDException e) {
            model.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return model;
    }

    /**
     * Gets the Category object to be updated and forwards to Update form.
     *
     * @return ModelAndView object containing the category object
     *         to be updated pointing to Update form.
     */
    @PostMapping("update-form")
    private ModelAndView updateForm(HttpServletRequest request) {
        Category category = new Category();
        category.setId(Integer.parseInt(request.getParameter(Constants.LABEL_ID)));
        category.setValue(request.getParameter(Constants.LABEL_SELECTED_CATEGORY));
        ModelAndView model =  new ModelAndView(PAGE_VIEW_CATEGORIES,
            Constants.LABEL_CATEGORY, category);
        try {
            model.addObject(Constants.LABEL_CATEGORIES,
                categoryService.getCategories(Boolean.TRUE));
        } catch (DVDException e) {
            model.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return model;
    }

    /**
     * Gets the new values for the DVD to be updated from the request page
     * and invokes the update method in {@code DVDStoreService}
     * and forwards to Display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return String value containing the Redirect url.
     */
    @PostMapping("update")
    private String update(HttpServletRequest request) {

        String message = new String();
        try {
            Category category = categoryService.getCategoryById(Integer.
                            parseInt(request.getParameter(Constants.LABEL_ID)));
            category.setValue(request.getParameter(Constants.LABEL_VALUE));
            Boolean status = categoryService.updateCategory(category);
            if (null == status) {
                message = Constants.MESSAGE_CATEGORY_ALREADY_EXISTS;
            } else if (status) {
                message = Constants.MESSAGE_UPDATE_CATEGORY_SUCCESS;
            } else {
                message = Constants.MESSAGE_UPDATE_CATEGORY_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_DISPLAY;
    }

    /**
     * Gets the id of the category to be deleted and invokes the remove
     * method of the {@code Category Service} and forwards to Display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return String value containing the Redirect url.
     */
    @PostMapping("delete")
    private String delete(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        String message = new String();
        try {
            if (categoryService.removeCategory(id)) {
                message = Constants.MESSAGE_REMOVE_CATEGORY_SUCCESS;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_DISPLAY;
    }

    /**
     * Gets the id of the category to be restored and invokes the restore
     * method of the {@code Category Service} and forwards to Display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return String value containing the Redirect url.
     */
    @PostMapping("restore")
    private String restore(HttpServletRequest request) {
        Integer id = Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        String message = new String();
        try {
            if (categoryService.restoreCategory(id)) {
                message = Constants.MESSAGE_RESTORE_CATEGORY_SUCCESS;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_RESTORE_DISPLAY;
    }

    /**
     * Gets the DVD Id and the category Id to remove the mapping
     * and forwards to the Display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return String value containing the Redirect url.
     */
    @PostMapping("remove-dvd")
    private String removeDvdFromCategory(HttpServletRequest request) {
        Integer dvdId =
                 Integer.parseInt(request.getParameter(Constants.LABEL_DVD_ID));
        Integer categoryId =
            Integer.parseInt(request.getParameter(Constants.LABEL_CATEGORY_ID));
        String message = new String();
        try {
            if (categoryService.removeDvdFromCategory(dvdId, categoryId)) {
                message = Constants.MESSAGE_REMOVE_DVD_CATEGORY_SUCCESS;
            } else {
                message = Constants.MESSAGE_REMOVE_DVD_CATEGORY_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_DISPLAY;
    }
}






