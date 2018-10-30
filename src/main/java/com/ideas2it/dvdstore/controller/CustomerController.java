package com.ideas2it.dvdstore.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.impl.CustomerServiceImpl;

/**
 * Performs all operations of getting input from the user to
 * perform all the Customer related operations and displaying the result.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Address
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.Customer
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.logging.Logger
 * @see com.ideas2it.dvdstore.service.CustomerService
 * @see com.ideas2it.dvdstore.service.impl.CustomerServiceImpl
 */
@Controller
@RequestMapping("customer")
public class CustomerController {

    private static final String PAGE_ADDRESS_FORM = "AddressForm";

    private static final String PAGE_CUSTOMER_ADDRESS = "ViewCustomerAddress";

    private static final String PAGE_CUSTOMER_PROFILE = "CustomerProfile";

    private static final String PAGE_CUSTOMER_FORM = "CustomerForm";

    private static final String PAGE_CUSTOMER_HOME = "CustomerHome";

    private static final String PAGE_CUSTOMER_ORDERS = "ViewCustomerOrders";

    private static final String PAGE_PURCHASE_FORM = "PurchaseForm";

    private static final String PAGE_VIEW_CUSTOMERS = "ViewCustomers";

    private static final String URL_REGISTER_FORM = "register-form";

    private static final String URL_PROFILE = "profile";

    private CustomerService customerService = new CustomerServiceImpl();

    /**
     * Redirects to Update Details form.
     *
     * @return returns ModelAndView object with empty customer object.
     */
    @GetMapping("register-form")
    private ModelAndView registerForm() {
        return new ModelAndView(PAGE_CUSTOMER_FORM,
            Constants.LABEL_COMMAND, new Customer());
    }

    /**
     * Gets the new User details to register the user to use the DVD Store.
     * Gets all the details of the customer and creates a customer object
     * storing the details and sends to Service to register the customer.
     *
     * @param customer
     *        Customer object from the submitted form.
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns string value of redirection url.
     */
    @PostMapping("register")
    private String register(@ModelAttribute()Customer customer,
            HttpServletRequest request) {

        String message = new String();
        HttpSession session = request.getSession(Boolean.FALSE);
        User user = (User) session.getAttribute(Constants.LABEL_USER);
        customer.setUser(user);
        try {
            Boolean status = customerService.registerCustomer(customer);
            if (null == status) {
                message = Constants.MESSAGE_CUSTOMER_ALREADY_EXISTS;
            } else if (status) {
                request.setAttribute(Constants.LABEL_MESSAGE,
                                   Constants.MESSAGE_REGISTER_CUSTOMER_SUCCESS);
                return Constants.LABEL_REDIRECT + URL_PROFILE;
            } else {
                message = Constants.MESSAGE_REGISTER_CUSTOMER_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        session.setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_REGISTER_FORM;
    }

    /**
     * Gets the customer details using the Id and forwards to the display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns string value of the Jsp page.
     */
    @GetMapping("profile")
    private String displayProfile(HttpServletRequest request) {

        HttpSession session = request.getSession(Boolean.FALSE);
        User user = (User) session.getAttribute(Constants.LABEL_USER);
        try {
            Customer customer = customerService.getCustomerByUserId(user.getId());
            if (null == customer) {
                session.setAttribute(Constants.LABEL_MESSAGE,
                                   Constants.MESSAGE_RETRIEVE_CUSTOMER_FAILURE);
            } else {
                session.setAttribute(Constants.LABEL_CUSTOMER, customer);
                request.setAttribute(Constants.LABEL_RELOAD, Boolean.FALSE);
            }
        } catch (DVDException e) {
            session.setAttribute(Constants.LABEL_MESSAGE, e.getMessage());
        }
        request.setAttribute(Constants.LABEL_RELOAD, Boolean.TRUE);
        return PAGE_CUSTOMER_PROFILE;
    }

    /**
     * Gets all the DVDs and forwards the Display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns ModelAndView object.
     */
    @GetMapping("/")
    private ModelAndView displayDvds(HttpServletRequest request) {

        ModelAndView model = new ModelAndView(PAGE_CUSTOMER_HOME);
        HttpSession session = request.getSession(Boolean.FALSE);
        session.removeAttribute(Constants.LABEL_CUSTOMER);
        try {
            model.addObject(Constants.LABEL_DVDS, customerService.getAllDvds());
            model.addObject(Constants.LABEL_CATEGORIES,
                customerService.getCategories());
        } catch (DVDException e) {
            session.setAttribute(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return model;
    }

    /**
     * Gets all the DVDs in the selected category and forwards the Display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns ModelAndView object.
     */
    @PostMapping("dvds-by-category")
    private ModelAndView displayDvdsByCategory(HttpServletRequest request) {

        ModelAndView model = new ModelAndView(PAGE_CUSTOMER_HOME);
        try {
            model.addObject(Constants.LABEL_DVDS,
                customerService.getDvdsByCategory(Integer.
                parseInt(request.getParameter(Constants.LABEL_CATEGORY))));
            model.addObject(Constants.LABEL_CATEGORIES,
                customerService.getCategories());
        } catch (DVDException e) {
            request.setAttribute(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return model;
    }

    /**
     * Gets all the DVDs having the name entered in the form and
     * forwards the Display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns ModelAndView object.
     */
    @PostMapping("dvds-by-name")
    private ModelAndView displayDvdsByName(HttpServletRequest request) {

        ModelAndView model = new ModelAndView(PAGE_CUSTOMER_HOME);
        try {
            model.addObject(Constants.LABEL_DVDS, customerService.
                getDvdsByName(request.getParameter(Constants.LABEL_NAME)));
            model.addObject(Constants.LABEL_CATEGORIES,
                customerService.getCategories());
        } catch (DVDException e) {
            request.setAttribute(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return model;
    }

    /**
     * Redirects to Update Details form.
     *
     * @return returns ModelAndView object.
     */
    @GetMapping("update-details-form")
    private String updateCustomerForm() {
        return PAGE_CUSTOMER_FORM;
    }

    /**
     * Gets the new values of the customer, modifies the old customer object
     * and invokes the update method.
     *
     * @param customer
     *        Customer object from submitted form.
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns string value of the Jsp page.
     */
    @PostMapping("update")
    private String update(@ModelAttribute()Customer customer,
            HttpServletRequest request) {

        String message = new String();
        Customer oldCustomer = (Customer) request.getSession().
            getAttribute(Constants.LABEL_CUSTOMER);
        oldCustomer.setName(customer.getName());
        oldCustomer.setEmail(customer.getEmail());
        oldCustomer.setContactNumber(customer.getContactNumber());
        try {
            if (customerService.updateCustomer(oldCustomer)) {
                message = Constants.MESSAGE_UPDATE_CUSTOMER_SUCCESS;
            } else {
                message = Constants.MESSAGE_UPDATE_CUSTOMER_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_PROFILE;
    }

    /**
     * Redirects to Update Details form.
     *
     * @return returns ModelAndView object.
     */
    @GetMapping("add-address-form")
    private ModelAndView addAddressForm() {
        return new ModelAndView(PAGE_ADDRESS_FORM,
            Constants.LABEL_COMMAND, new Address());
    }

    /**
     * Gets the new address from the form, adds the address to the customer
     * object and updates it.
     *
     * @param address
     *        Address object from the submitted form.
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns string value of the Jsp page.
     */
    @PostMapping("add-address")
    private String addAddress(@ModelAttribute()Address address,
            HttpServletRequest request) {

        String message = new String();
        Customer customer = (Customer) request.getSession(Boolean.FALSE).
                                         getAttribute(Constants.LABEL_CUSTOMER);
        address.setCustomerId(customer.getId());
        customer.getAddress().add(address);
        try {
            if (customerService.updateCustomer(customer)) {
                message = Constants.MESSAGE_ADD_ADDRESS_SUCCESS;
            } else {
                message = Constants.MESSAGE_ADD_ADDRESS_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_PROFILE;
    }

    /**
     * Gets the address to be updated and forwards to the Update address page.
     *
     * @param response
     *        HttpServletResponse object.
     *
     * @return returns ModelAndView object.
     */
    @PostMapping("update-address-form")
    private ModelAndView updateAddressForm(HttpServletRequest request) {

        Customer customer = (Customer) request.getSession(Boolean.FALSE).
            getAttribute(Constants.LABEL_CUSTOMER);
        ModelAndView model = new ModelAndView(PAGE_ADDRESS_FORM);
        Integer addressId = 
                     Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        for (Address address: customer.getAddress()) {
            if (addressId == address.getId()) {
                model.addObject(Constants.LABEL_ADDRESS, address);
                break;
            }
        }
        return model;
    }

    /**
     * Gets the new address values to be updated, updates the customer object
     * and invokes the service method.
     *
     * @param address
     *        Address object from the submitted form.
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns string value of the Jsp page.
     */
    @PostMapping("update-address")
    private String updateAddress(@ModelAttribute()Address newAddress,
            HttpServletRequest request) {

        String message = new String();
        Customer customer = (Customer) request.getSession(Boolean.FALSE).
                                         getAttribute(Constants.LABEL_CUSTOMER);
        Integer id = Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        for (Address address: customer.getAddress()) {
            if (id == address.getId()) {
                customer.getAddress().remove(address);
                break;
            }
        }
        customer.getAddress().add(newAddress);
        try {
            if (customerService.updateCustomer(customer)) {
                message = Constants.MESSAGE_UPDATE_ADDRESS_SUCCESS;
            } else {
                message = Constants.MESSAGE_UPDATE_ADDRESS_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_PROFILE;
    }

    /**
     * Gets the address id to be deleted and removes it from the address list,
     * updates the customer object by invoking the service method.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns ModelAndView object.
     */
    @PostMapping("delete-address")
    private ModelAndView deleteAddress(HttpServletRequest request) {

        String message = new String();
        Customer customer = (Customer) request.getSession(Boolean.FALSE).
                                         getAttribute(Constants.LABEL_CUSTOMER);
        HttpSession session = request.getSession();
        Integer id = Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        if (1 >= customer.getAddress().size()) {
            session.setAttribute(Constants.LABEL_MESSAGE, 
                                      Constants.MESSAGE_DELETE_ADDRESS_FAILURE);
        } else {
            for (Address address: customer.getAddress()) {
                if (id == address.getId()) {
                    customer.getAddress().remove(address);
                    break;
                }
            }
            try {
                if (customerService.updateCustomer(customer)) {
                    message = Constants.MESSAGE_DELETE_ADDRESS_SUCCESS;
                } else {
                    message = Constants.MESSAGE_DELETE_ADDRESS_FAILURE;
                }
            } catch (DVDException e) {
                message = e.getMessage();
            }
            session.setAttribute(Constants.LABEL_MESSAGE, message);
        }
        return new ModelAndView(Constants.LABEL_REDIRECT + URL_PROFILE);
    }

    /**
     * Gets the Order Id to be cancelled and invokes the cancel Order method
     * in CustomerService.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns string value of the Jsp page.
     */
    @PostMapping("cancel-order")
    private String cancelOrder(HttpServletRequest request) {

        String message = new String();
        Integer id = Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        try {
            if (customerService.cancelOrder(id)) {
                message = Constants.MESSAGE_CANCEL_ORDER_SUCCESS;
            } else {
                message = Constants.MESSAGE_CANCEL_ORDER_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + URL_PROFILE;
    }

    /**
     * Gets the dvdIds to be purchased, gets the DVD objects and forwards to 
     * the purchase Form page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns string value of the Jsp page.
     */
    @PostMapping("purchase-form")
    private String purchaseForm(HttpServletRequest request) {

        String[] selectedDvds = request.getParameterValues(Constants.LABEL_ID);
        List<Integer> dvdIds = new LinkedList<Integer>();
        for (String dvdId: selectedDvds) {
            dvdIds.add(Integer.parseInt(dvdId));
        }
        HttpSession session = request.getSession(Boolean.FALSE);
        User user = (User) session.getAttribute(Constants.LABEL_USER);
        try {
            session.setAttribute(Constants.LABEL_CUSTOMER, customerService.
                getCustomerByUserId(user.getId()));
            session.setAttribute(Constants.LABEL_SELECTED_DVDS,
                customerService.getDvdsByIds(dvdIds));
            return PAGE_PURCHASE_FORM;
        } catch (DVDException e) {
            session.setAttribute(Constants.LABEL_MESSAGE, e.getMessage());
            return Constants.LABEL_REDIRECT + "/";
        }
    }

    /**
     * Gets the dvds to be purchased and invokes the purchase method of 
     * {@code CustomerOrderService} and forwards the result of the opertion.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns string value of the Jsp page.
     */
    @PostMapping("purchase")
    private String purchase(HttpServletRequest request) {

        String message = new String();
        Customer customer = (Customer) request.getSession(Boolean.FALSE).
                                         getAttribute(Constants.LABEL_CUSTOMER);
        Integer id = Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        Address selectedAddress = new Address();
        for (Address address: customer.getAddress()) {
            if (id == address.getId()) {
                selectedAddress = address;
            }
        }
        HttpSession session = request.getSession();
        try {
            Boolean status = customerService.purchase((List<DVD>) session.
                getAttribute(Constants.LABEL_SELECTED_DVDS), customer.getId(),
                selectedAddress);
            session.removeAttribute(Constants.LABEL_SELECTED_DVDS);
            if (null == status) {
                message = Constants.MESSAGE_DVD_OUT_OF_STOCK;
            } else if (status) {
                message = Constants.MESSAGE_PURCHASE_DVD_SUCCESS;
            } else {
                message = Constants.MESSAGE_PURCHASE_DVD_FAILURE;
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        request.getSession().setAttribute(Constants.LABEL_MESSAGE, message);
        return Constants.LABEL_REDIRECT + "/";
    }
    
    /**
     * Gets all the customer's details and forwards to the display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns ModelAndView object.
     */
    @GetMapping("display")
    private ModelAndView displayCustomers(HttpServletRequest request) {

        List<Customer> customers = null;
        try {
            customers = customerService.getAllCustomers();
        } catch (DVDException e) {
            request.setAttribute(Constants.LABEL_MESSAGE, e.getMessage());
        }
        return new ModelAndView(PAGE_VIEW_CUSTOMERS, Constants.LABEL_CUSTOMERS,
            customers);
    }

    /**
     * Gets the customer address using the Id and forwards to the display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns ModelAndView object.
     */
    @PostMapping("display-customer-address")
    private ModelAndView viewCustomerAddress(HttpServletRequest request) {

        String message = new String();
        ModelAndView model = new ModelAndView(PAGE_CUSTOMER_ADDRESS);
        Integer id = Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        try {
            Customer customer = customerService.getCustomerById(id);
            if (null == customer) {
                message = Constants.MESSAGE_RETRIEVE_CUSTOMER_FAILURE;
            } else {
                model.addObject(Constants.LABEL_CUSTOMER_ADDRESS,
                    customer.getAddress());
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        model.addObject(Constants.LABEL_MESSAGE, message);
        return model;
    }

    /**
     * Gets the customer orders using the Id and forwards to the display page.
     *
     * @param request
     *        HttpServletRequest object.
     *
     * @return returns ModelAndView object.
     */
    @PostMapping("display-customer-orders")
    private ModelAndView viewCustomerOrders(HttpServletRequest request) {

        String message = new String();
        ModelAndView model = new ModelAndView(PAGE_CUSTOMER_ORDERS);
        Integer id = Integer.parseInt(request.getParameter(Constants.LABEL_ID));
        try {
            Customer customer = customerService.getCustomerById(id);
            if (null == customer) {
                message = Constants.MESSAGE_RETRIEVE_CUSTOMER_FAILURE;
            } else {
                model.addObject(Constants.LABEL_ORDERS, customer.getOrders());
            }
        } catch (DVDException e) {
            message = e.getMessage();
        }
        model.addObject(Constants.LABEL_MESSAGE, message);
        return model;
    }
}










 
