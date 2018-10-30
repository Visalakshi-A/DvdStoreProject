package com.ideas2it.dvdstore.dao;

import java.util.List;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Order;

/**
 * Accesses the Database that stores the Customer information and 
 * performs manipulations on the Customer details.
 *
 * @author Visalakshi
 */
public interface OrderDao {

    /**
     * Inserts the order object into the DVD Store.
     *
     * @param order
     *        Order object to be inserted.
     *
     * @return return true on success and false on failure.
     */
    Boolean addOrder(Order order) throws DVDException;

    /**
     * Deletes the order object having the id passed from the DVD Store.
     *
     * @param id
     *        Integer value of the order id to be deleted.
     *
     * @return returns true on success.
     */
    Boolean deleteOrder(Integer id) throws DVDException;

    /**
     * Sets the order status to be false.
     *
     * @param id
     *        Integer value of the order id to be modified.
     *
     * @return returns true on success.
     */
    Boolean setOrderInactive(Integer id) throws DVDException;

    /**
     * Retrieves all the orders from the store and returns as a list.
     *
     * @return orders
     *         List of all the orders.
     */
    List<Order> retrieveOrders() throws DVDException;
}
