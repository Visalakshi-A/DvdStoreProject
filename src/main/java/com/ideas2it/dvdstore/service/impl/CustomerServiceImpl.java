package com.ideas2it.dvdstore.service.impl;

import java.util.List;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.CustomerDao;
import com.ideas2it.dvdstore.dao.impl.CustomerDaoImpl;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.CustomerService;   
import com.ideas2it.dvdstore.service.OrderService;
import com.ideas2it.dvdstore.service.DVDService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdstore.service.impl.OrderServiceImpl;
import com.ideas2it.dvdstore.service.impl.DVDServiceImpl;

/**
 * Modifies the data from the Controller layer and sends it to the DAO layer
 * and returns the status of the operation performed in the DAO.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.dao.CustomerDao
 * @see com.ideas2it.dvdstore.dao.impl.CustomerDaoImpl
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Address
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.Customer
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.service.CategoryService
 * @see com.ideas2it.dvdstore.service.CustomerService
 * @see com.ideas2it.dvdstore.service.DVDService
 * @see com.ideas2it.dvdstore.service.impl.CategoryService
 * @see com.ideas2it.dvdstore.service.impl.DVDService
 */
public class CustomerServiceImpl implements CustomerService {

    CategoryService categoryService = new CategoryServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    DVDService dvdService = new DVDServiceImpl();
    CustomerDao customerDao = new CustomerDaoImpl();

    /** @{inheritDoc} */
    public Boolean registerCustomer(Customer customer) throws DVDException {
        if (customerDao.checkAlreadyExists(customer)) {
            return null;
        }
        customer.setStatus(Constants.ACTIVE);
        return customerDao.addNewCustomer(customer);
    }

    /** @{inheritDoc} */
    public Customer getCustomerByUserId(Integer userId)
            throws DVDException {
        return customerDao.getCustomerByUserId(userId);
    }

    /** @{inheritDoc} */
    public Customer getCustomerById(Integer customerId) throws DVDException {
        return customerDao.getCustomerById(customerId);
    }

    /** @{inheritDoc} */
    public Boolean updateCustomer(Customer customer) throws DVDException {
        return customerDao.updateCustomerDetails(customer);
    }

    /** @{inheritDoc} */
    public Boolean purchase(List<DVD> dvds, Integer customerId,
            Address address) throws DVDException {
        return orderService.purchaseDvds(dvds, customerId, address);
    }

    /** @{inheritDoc} */
    public Boolean cancelOrder(Integer id) throws DVDException {
        return orderService.cancelOrder(id);
    }

    /** @{inheritDoc */
    public List<Customer> getAllCustomers() throws DVDException {
        return customerDao.retrieveCustomers();
    }

    /** @{inheritDoc} */
    public List<DVD> getDvdsByIds(List<Integer> dvdIds) throws DVDException {
        return dvdService.getDvdsByIds(dvdIds);
    }

    /** @{inheritDoc} */
    public List<DVD> getAllDvds() throws DVDException {
        return dvdService.getAllDvds(Constants.ACTIVE);
    }

    /** @{inheritDoc} */
    public List<DVD> getDvdsByName(String dvdName) throws DVDException {
        return dvdService.getDvdsByName(dvdName);
    }

    /** @{inheritDoc} */
    public List<DVD> getDvdsByCategory(Integer categoryId) throws DVDException {
        return categoryService.getDvdsByCategoryId(categoryId);
    }

    /** @{inheritDoc} */
    public List<Category> getCategories() throws DVDException {
        return categoryService.getCategories(Constants.ACTIVE);
    }
}
