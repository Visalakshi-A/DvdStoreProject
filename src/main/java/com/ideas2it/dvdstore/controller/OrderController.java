package com.ideas2it.dvdstore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.service.impl.OrderServiceImpl;
import com.ideas2it.dvdstore.service.OrderService;

/**
 * Performs all operations of getting input from the user to
 * perform all the Customer related operations and displaying the result.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.logging.Logger
 * @see com.ideas2it.dvdstore.model.Order
 * @see com.ideas2it.dvdstore.service.impl.OrderServiceImpl
 * @see com.ideas2it.dvdstore.service.OrderService
 */
@Controller
@RequestMapping("order")
public class OrderController {

    private static final String PAGE_VIEW_ORDERS = "ViewOrders";

    private static final String URL_DISPLAY = "display";

    private OrderService orderService = new OrderServiceImpl();
    
    /**
     * Gets all the order details and forwards to the display page.
     *
     * @return returns ModelAndView object directing to View Orders page
     */
    @GetMapping("display")
    private ModelAndView displayOrders() {

        ModelAndView model = new ModelAndView(PAGE_VIEW_ORDERS);
        try {
            model.addObject(Constants.LABEL_ORDERS, orderService.getOrders());
        } catch (DVDException e) {
            model.addObject(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return model;
    }

    /**
     * Gets the order Id, invokes the service method to set the order to be
     * delivered and forwards to the Display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns String object directing to Display Orders page
     */
    @PostMapping("set-delivered")
    private String setOrderDelivered(HttpServletRequest request) {

        Integer id = Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        try {
            orderService.setOrderDelivered(id);
        } catch (DVDException e) {
            request.getSession().setAttribute(Constants.LABEL_MESSAGE,
                                                                e.getMessage());
        }
        return Constants.LABEL_REDIRECT + URL_DISPLAY;
    }
}
