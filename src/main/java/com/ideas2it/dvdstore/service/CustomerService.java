package com.ideas2it.dvdstore.service;

import java.util.List;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.model.User;

/**
 * Contains all the methods for the Customer related operations that 
 * can be performed.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Address
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.Customer
 * @see com.ideas2it.dvdstore.model.DVD
 */
public interface CustomerService {

    /**
     * Invokes the Dao method to add the customer details and returns
     * the status of the operation.
     *
     * @param customer
     *        Customer object of the user to be registered.
     *
     * @return Boolean value of the status of the operation.
     */
    Boolean registerCustomer(Customer customer) throws DVDException;

    /**
     * Gets the customer object and sets the value to be updated to the customer.
     * Invokes the DAO method to update the customer details.
     *
     * @param customer
     *        Customer object with old values.
     *
     * @return Boolean value of the status of the operation.
     */
    Boolean updateCustomer(Customer customer) throws DVDException;

    /**
     * Invokes the DAO method to get the customer details using the
     * contact number.
     *
     * @param userId
     *        Integer value of the customer user Id.
     *
     * @return Customer object containing all the details.
     */
    Customer getCustomerByUserId(Integer userId)throws DVDException;

    /**
     * Invokes the DAO method to get the customer details using the Id.
     *
     * @param customerId
     *        Integer value of the customer id.
     *
     * @return Customer object containing all the details.
     */
    Customer getCustomerById(Integer id) throws DVDException;

    /**
     * Invokes the purchase method of {@code OrderService} and
     * returns the status of the operation.
     *
     * @param ids
     *        List of Integer value of the DVD ids to be purchased.
     * @param customerId
     *        Integer value of id of the Customer performing purchase operation.
     * @param address
     *        Address object of the customer address.
     *
     * @return returns the status of the operation as a String.
     */
    Boolean purchase(List<DVD> dvds, Integer customerId, Address address)
        throws DVDException;

    /**
     * Invokes the cancelOrder method of {@code OrderService} and returns the 
     * status of the operation.
     *
     * @param id
     *        Integer value of the Order Id.
     *
     * @return returns the value returned by the method.
     */
    Boolean cancelOrder(Integer id) throws DVDException;

    /**
     * Invokes the DAO method to get all the customer details.
     *
     * @return List of customer objects containing all the details.
     */
    List<Customer> getAllCustomers() throws DVDException;

    /**
     * Invokes the {@code DVDStoreService} to get the DVDs for the Ids passed.
     *
     * @param dvdIds
     *        Integer list of value of the Dvd ids to be retrieved.
     *
     * @return List of DVD objects returned by method.
     */
    List<DVD> getDvdsByIds(List<Integer> dvdId) throws DVDException;

    /**
     * Invokes the {@code DVDStoreService} to get all the DVDs.
     *
     * @return List returned by method.
     */
    List<DVD> getAllDvds() throws DVDException;

    /**
     * Invokes the {@code DVDStoreService} to get the DVD list for the 
     * passed DVD name.
     *
     * @param dvdName
     *        String value of the Dvd name to be retrieved.
     *
     * @return List returned by method.
     */
    List<DVD> getDvdsByName(String dvdName) throws DVDException;

    /**
     * Invokes the {@code CategoryService} to get the DVD list for the 
     * Category Id.
     *
     * @param categoryId
     *        Integer value of the Id passed.
     *
     * @return returns the value returned by that method
     */
    List<DVD> getDvdsByCategory(Integer categoryId) throws DVDException;

    /**
     * Invokes the {@code CategoryService} method to retrieve categories list.
     *
     * @return List of categories returned by the method.
     */
    List<Category> getCategories() throws DVDException;
}
