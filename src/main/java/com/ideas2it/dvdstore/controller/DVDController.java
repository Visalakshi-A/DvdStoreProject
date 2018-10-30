package com.ideas2it.dvdstore.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.service.DVDService;
import com.ideas2it.dvdstore.service.impl.DVDServiceImpl;

/**
 * Performs all operations of getting input from the user to
 * perform all the DVD related operations and displaying the result.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.logging.Logger
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.service.DVDService
 * @see com.ideas2it.dvdstore.service.impl.DVDServiceImpl
 * @see com.ideas2it.dvdstore.utils.DateUtils
 */
@Controller
@RequestMapping("dvd")
public class DVDController {

    private static DVDService dvdService;

    private static final String PAGE_HOME = "AdminHome";

    private static final String PAGE_DVD_FORM = "DvdForm";

    private static final String PAGE_VIEW_DVDS = "ViewDvds";

    private static final String URL_DISPLAY = "display";

    private static final String URL_RESTORE_DISPLAY = "restore-display";

    public void setDvdService(DVDService dvdService) {
        this.dvdService = dvdService;
    }

    //private DVDService dvdService = new DVDServiceImpl();

    /**
     * Redirects to the Insert Dvd form page after creating a new DVD object.
     *
     * @return ModelAndView object redirecting to Insert Form page with 
     *         empty DVD object.
     */
    @GetMapping("insert-form")
    private ModelAndView insertForm() {
        ModelAndView model = new ModelAndView(PAGE_DVD_FORM,
            Constants.LABEL_COMMAND, new DVD());
        try {
            model.addObject(Constants.LABEL_CATEGORIES,
                dvdService.getCategories(Boolean.TRUE));
        } catch (DVDException e) {
            Logger.error(Constants.LOG_RETRIEVE_CATEGORIES_EXCEPTION);
        }
        return model;
    }

    /**
     * Gets the new DVD values from the form and invokes the insert method
     * of {@code DVDService} and forwards to the Display page.
     *
     * @param dvd
     *        DVD object from the Form.
     * @param request
     *        HttpServletRequest object.
     *
     * @return String value to forward to Jsp page.
     */
    @PostMapping("insert")
    private String insert(@ModelAttribute()DVD dvd,
            HttpServletRequest request) {

        String message = new String();
        try {
            dvd.setCategories(getDvdCategories(request.
                                 getParameterValues(Constants.LABEL_CATEGORY)));
            Boolean status = dvdService.checkAlreadyExistsDvd(dvd);
            if (null == status) {
                if (dvdService.addNewDvd(dvd)) {
                    message = Constants.MESSAGE_ADD_DVD_SUCCESS;
                }
            } else if (status) {
                message = Constants.MESSAGE_DVD_ALREADY_EXISTS;
            } else {
                message = Constants.MESSAGE_RESTORE_DVD;
            }
        } catch (DVDException e) {
           message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_DISPLAY;
    }

    /**
     * Invokes the service method to get all the DVDs in the store
     * and diplays in another page.
     *
     * @return ModelAndView object forwarding to View DVDs page with
     *         list of DVDs.
     */
    @GetMapping("display")
    private ModelAndView display() {

        List<DVD> dvds = new ArrayList<DVD>();
        try {
            dvds = dvdService.getAllDvds(Boolean.TRUE);
        } catch (DVDException e) {
            return new ModelAndView(PAGE_HOME,
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return new ModelAndView(PAGE_VIEW_DVDS, Constants.LABEL_DVDS, dvds);
    }

    /**
     * Invokes the service method to get all the DVDs in the store
     * and diplays in another page.
     *
     * @return ModelAndView object forwarding to View DVDs page with
     *         list of inactive DVDs.
     */
    @GetMapping("restore-display")
    private ModelAndView restoreDisplay() {

        Boolean status = Boolean.FALSE;
        ModelAndView model = new ModelAndView(PAGE_VIEW_DVDS);
        model.addObject(Constants.LABEL_STATUS, status);
        try {
            model.addObject(Constants.LABEL_DVDS, dvdService.getAllDvds(status));
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
     * @return ModelAndView object forwarding to View DVDs page with
     *         list of DVDs.
     */
    @GetMapping("display-dvds-by-category")
    private ModelAndView getDvdsByCategory(HttpServletRequest request) {

    List<DVD> dvds = new ArrayList<DVD>();
        try {
            dvds = dvdService.getDvdsByCategoryId(Integer.
                parseInt(request.getParameter(Constants.LABEL_CATEGORY)));
        } catch (DVDException e) {
            return new ModelAndView(PAGE_HOME, 
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return new ModelAndView(PAGE_VIEW_DVDS, Constants.LABEL_DVDS, dvds);
    }

    /**
     * Retrieves the dvds present in that name and forwards it to the display
     * page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return ModelAndView object forwarding to View DVDs page with
     *         list of DVDs.
     */
    @GetMapping("display-dvds-by-name")
    private ModelAndView getDvdsByName(HttpServletRequest request) {
        
        List<DVD> dvds = new ArrayList<DVD>();
        try {
            dvds = dvdService.getDvdsByName(request.
                                            getParameter(Constants.LABEL_NAME));
        } catch (DVDException e) {
            return new ModelAndView(PAGE_HOME,
                Constants.LABEL_MESSAGE, e.getMessage());
        }
        return new ModelAndView(PAGE_VIEW_DVDS, Constants.LABEL_DVDS, dvds);
    }

    /**
     * Gets the DVD to be updated and forwards to Update Dvd form page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return ModelAndView object directed to Update Dvd form containing
     *         DVD object to be updated.
     */
    @PostMapping("update-form")
    private ModelAndView updateForm(HttpServletRequest request) {

        DVD dvd = new DVD();
        ModelAndView model = new ModelAndView(PAGE_DVD_FORM);
        try {
            model.addObject(Constants.LABEL_CATEGORIES,
                                        dvdService.getCategories(Boolean.TRUE));
            model.addObject(Constants.LABEL_DVD, dvdService.getDvdById(Integer.
                parseInt(request.getParameter(Constants.LABEL_ID))));
            model.addObject(Constants.LABEL_COMMAND, dvd);
        } catch (DVDException e) {
            model.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return model;
    }

    /**
     * Gets the new values for the DVD to be updated from the request page
     * and invokes the update method in {@code DVDService} and
     * forwards to DVD display page.
     *
     * @param dvd
     *        DVD object with new values.
     * @param response
     *        HttpServletResponse object.
     */
    @PostMapping("update")
    private ModelAndView update(@ModelAttribute("dvd")DVD dvd,
            HttpServletRequest request) {

        String message = new String();
        try {
            dvd.setCategories(getDvdCategories(request.
                                 getParameterValues(Constants.LABEL_CATEGORY)));
            Boolean status = dvdService.updateDvd(dvd);
            if (null == status) {
                message = Constants.MESSAGE_DVD_ALREADY_EXISTS;
            } else if (status) {
                message = Constants.MESSAGE_UPDATE_DVD_SUCCESS;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return new ModelAndView(Constants.LABEL_REDIRECT + URL_DISPLAY);
    }

    /**
     * Gets the DVD id to be deleted from the request and invokes
     * the delete method.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return ModelAndView object forwarded to DVD display page.
     */
    @PostMapping("delete")
    private ModelAndView delete(HttpServletRequest request) {
        String message = new String();
        try {
            if (dvdService.removeDvd(Integer.
                    parseInt(request.getParameter(Constants.LABEL_ID)))) {
                message = Constants.MESSAGE_REMOVE_DVD_SUCCESS;
            } else {
                message = Constants.MESSAGE_REMOVE_DVD_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        return new ModelAndView(Constants.LABEL_REDIRECT + URL_DISPLAY,
            Constants.LABEL_MESSAGE, message);
    }

    /**
     * Gets the DVD id to be restored from the request and invokes
     * the restore method.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return ModelAndView object forwarded to Inactive DVDs display page.
     */
    @PostMapping("restore")
    private ModelAndView restore(HttpServletRequest request) {
        String message = new String();
        try {
            if (dvdService.restoreDvd(Integer.parseInt(request.
                    getParameter(Constants.LABEL_ID)))) {
                message = Constants.MESSAGE_RESTORE_DVD_SUCCESS;
            } else {
                message = Constants.MESSAGE_RESTORE_DVD_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.setAttribute(Constants.LABEL_STATUS, Constants.LABEL_INACTIVE);
        return new ModelAndView(Constants.LABEL_REDIRECT + URL_RESTORE_DISPLAY,
            Constants.LABEL_MESSAGE, message);
    }

    /**
     * Retrieves the categories list and forwards to the Display page.
     *
     * @return ModelAndView object with Category list forwarded to Diplay page.
     */
    @GetMapping("categories")
    private ModelAndView getCategories() {
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = dvdService.getCategories(Constants.ACTIVE);
        } catch (DVDException e) {
            Logger.error(Constants.LOG_RETRIEVE_CATEGORIES_EXCEPTION);
        }
        return new ModelAndView(PAGE_VIEW_DVDS, Constants.LABEL_CATEGORIES, categories);
    }

    /**
     * Converts the string list of Category Ids into a list of Category objects.
     *
     * @param categoryIds
     *        Array of strings containing selected categoryIds
     *
     * @return List of {@code Category} objects.
     */
    private List<Category> getDvdCategories(String[] categoryIds)
            throws DVDException {
    
        List<Integer>ids = new LinkedList<Integer>();
        for (String id: categoryIds) {
            ids.add(Integer.parseInt(id));
        }
        return dvdService.getCategoriesByIds(ids);
    }
}
