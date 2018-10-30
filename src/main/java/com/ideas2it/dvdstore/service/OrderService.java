package com.ideas2it.dvdstore.service;

import java.util.List;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.model.Order;

/**
 * Contains all the methods for the Customer related operations that 
 * can be performed.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Address
 * @see com.ideas2it.dvdstore.model.Customer
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.model.Order
 */
public interface OrderService {

    /**
     * Invokes the DAO method for inserting the Order and
     * returns the status of the operation.
     *
     * @param dvdIds
     *        List of Integer value of the DVD ids to be purchased.
     * @param customerId
     *        Id of the Customer performing purchase operation.
     * @param address
     *        Address object of the customer address.
     *
     * @return returns the status of the operation as a String.
     */
    Boolean purchaseDvds(List<DVD> dvds, Integer customerId, Address address)
        throws DVDException;

    /**
     * Invokes the DAO method to delete the Order.
     *
     * @param id
     *        Integer value of the Order id to be deleted.
     *
     * @return returns the value returned by the DAO method.
     */
    Boolean cancelOrder(Integer id) throws DVDException;

    /**
     * Invokes the DAO method to set the order status.
     *
     * @param id
     *        Integer value of the Order id to be modified.
     *
     * @return returns the value returned by the DAO method.
     */
    Boolean setOrderDelivered(Integer id) throws DVDException;

    /**
     * Invokes the DAO method for retrieving the Order and returns the
     * list returned by the method.
     *
     * @return returns the list returned by the DAO method.
     */
    List<Order> getOrders() throws DVDException;
}
