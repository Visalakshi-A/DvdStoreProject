package com.ideas2it.dvdstore.dao;

import java.util.List;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.User;

/**
 * Accesses the Database that stores the Customer information and 
 * performs manipulations on the Customer details.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Customer
 */
public interface CustomerDao {

    /**
     * Performs the insert operation on the CUSTOMER table with
     * the values passed in the customer object.
     *
     * @param customer
     *        Customer object to be inserted.
     *
     * @return returns true on success and false on failure.
     */
    Boolean addNewCustomer(Customer customer) throws DVDException;

    /**
     * Updates the particular record of CUSTOMER table using the Customer Id.
     *
     * @param customer
     *        Customer object containing the values to be updated.
     *
     * @return returns true on success and false on failure.
     */
    Boolean updateCustomerDetails(Customer customer) throws DVDException;

    /**
     * Checks if the entered customer details is already present and 
     * returns true if already present.
     *
     * @param customer
     *        Customer object to be checked.
     *
     * @return returns true if customer details is already present else false.
     */
    Boolean checkAlreadyExists(Customer customer) throws DVDException;

    /**
     * Gets the customer details from the DB using contact number.
     *
     * @param userId
     *        Integer value of the customer user Id.
     *
     * @return customer
     *         Customer object containing the details.
     */
    Customer getCustomerByUserId(Integer userId) throws DVDException;

    /**
     * Gets the customer details from the DB using id.
     *
     * @param id
     *        Integer value of the customer id.
     *
     * @return customer
     *         Customer object containing the details.
     */
    Customer getCustomerById(Integer id) throws DVDException;

    /**
     * Retrieves all the customer details from Customer model and returns it.
     *
     * @return customers
     *         List of customer objects contains the DVD orders.
     */
    List<Customer> retrieveCustomers() throws DVDException;
}
