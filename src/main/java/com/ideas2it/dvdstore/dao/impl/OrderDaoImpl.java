package com.ideas2it.dvdstore.dao.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.OrderDao;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.session.DVDStoreSessionFactory;

/**
 * Accesses the Database that stores the Customer information and 
 * performs manipulations on the Customer details
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.dao.OrderDao
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.logging.Logger
 * @see com.ideas2it.dvdstore.model.Customer
 * @see com.ideas2it.dvdstore.model.Order
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.session.DVDStoreSessionFactory
 */
public class OrderDaoImpl implements OrderDao {

    private DVDStoreSessionFactory factory
                                        = DVDStoreSessionFactory.getInstance();

    /** @{inheritDoc} */
    public Boolean addOrder(Order order) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            Logger.info(order.getDvd().getId() + Constants.LOG_DVD_PURCHASED 
                                                       + order.getCustomerId());
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.error(Constants.LOG_ADD_ORDER_EXCEPTION, e);
            throw new DVDException(Constants.MESSAGE_PROBLEM_PURCHASING);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean deleteOrder(Integer id) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            session.delete(order);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.error(Constants.LOG_DELETE_ORDER_EXCEPTION);
            throw new DVDException(Constants.MESSAGE_CANCEL_ORDER_FAILURE);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean setOrderInactive(Integer id) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            Order order = session.get(Order.class, id);
            order.setStatus(Boolean.FALSE);
            transaction.commit();
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.error(Constants.LOG_SET_ORDER_STATUS_EXCEPTION);
            throw new DVDException(Constants.MESSAGE_SET_ORDER_STATUS_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public List<Order> retrieveOrders() throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Order> query = builder.createQuery(Order.class);
            Root<Order> root = query.from(Order.class);
            query.select(root);
            List<Order> orders = session.createQuery(query).getResultList();
            return orders;
        } catch (HibernateException e) {
            Logger.error(Constants.LOG_RETRIEVE_ORDERS_EXCEPTION, e);
            throw new DVDException(Constants.MESSAGE_RETRIEVE_ORDERS_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }
}












