package com.ideas2it.dvdstore.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.ideas2it.dvdstore.dao.OrderDao;
import com.ideas2it.dvdstore.dao.impl.OrderDaoImpl;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.service.DVDService;
import com.ideas2it.dvdstore.service.impl.DVDServiceImpl;
import com.ideas2it.dvdstore.service.OrderService;

/**
 * Modifies the data from the Controller layer and sends it to the DAO layer
 * and returns the status of the operation performed in the DAO.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.dao.OrderDao
 * @see com.ideas2it.dvdstore.dao.impl.OrderDaoImpl
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Address
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.model.Order
 * @see com.ideas2it.dvdstore.service.DVDService
 * @see com.ideas2it.dvdstore.service.impl.DVDServiceImpl
 * @see com.ideas2it.dvdstore.service.OrderService
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    private DVDService dvdService = new DVDServiceImpl();

    /** @{inheritDoc} */
    public Boolean purchaseDvds(List<DVD> dvds, Integer customerId,
            Address address) throws DVDException {
        Integer count = 0;
        for (DVD dvd: dvds) {
            Order order = new Order();
            order.setCustomerId(customerId);
            order.setAddress(address);
            order.setDvd(dvd);
            order.setOrderDate(Date.valueOf(LocalDate.now()));
            order.setStatus(Boolean.TRUE);
            if (orderDao.addOrder(order)) {
                dvd.setQuantity(dvd.getQuantity()-1);
                dvdService.updateDvd(dvd);
            }
            count++;
        }
        return (count == dvds.size());
    }

    /** @{inheritDoc} */
    public Boolean cancelOrder(Integer id) throws DVDException {
        return orderDao.deleteOrder(id);
    }

    /** @{inheritDoc} */
    public Boolean setOrderDelivered(Integer id) throws DVDException {
        return orderDao.setOrderInactive(id);
    }
    
    /** @{inheritDoc} */
    public List<Order> getOrders() throws DVDException {
        return orderDao.retrieveOrders();
    }
}
