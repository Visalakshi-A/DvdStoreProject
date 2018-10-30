package com.ideas2it.dvdstore.dao.impl;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.hibernate.Filter;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.CategoryDao;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.session.DVDStoreSessionFactory;

/**
 * Accesses a Database that stores the objects of class {@code Category}
 * to perform manipulations related to DVD Categories on the Database.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.dao.CategoryDao
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.logging.Logger
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.session.DVDStoreSessionFactory
 */
public class CategoryDaoImpl implements CategoryDao {

    private DVDStoreSessionFactory factory
                                        = DVDStoreSessionFactory.getInstance();
    
    /** 
     * @{inheritDoc} 
     */
    public Boolean addCategory(Category category) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            session.save(category);
            transaction.commit();
            Logger.info(Constants.LOG_CATEGORY_ADDED + category.getId());
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            StringBuilder exceptionMessage = new StringBuilder();
            exceptionMessage.append(category)
                .append(Constants.MESSAGE_ADD_CATEGORY_EXCEPTION);
            Logger.error(exceptionMessage.toString(), e);
            throw new DVDException(exceptionMessage.toString());
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public List<Category> retrieveCategories(Boolean status) 
            throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> query = builder.createQuery(Category.class);
            Root<Category> root = query.from(Category.class);
            query.select(root);
            query.where(builder.equal(root.get(Constants.LABEL_STATUS), status));
            return session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            Logger.error(Constants.LOG_RETRIEVE_CATEGORIES_EXCEPTION, e);
            throw new DVDException(Constants.
                                           MESSAGE_RETRIEVE_CATEGORY_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean updateCategory(Category category) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            session.update(category);
            transaction.commit();
            Logger.info(Constants.LOG_CATEGORY_UPDATED + category.getId());
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            StringBuilder exceptionMessage = new StringBuilder();
            exceptionMessage.append(category)
                .append(Constants.MESSAGE_UPDATE_CATEGORY_EXCEPTION);
            Logger.error(exceptionMessage.toString(), e);
            throw new DVDException(exceptionMessage.toString());
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean setCategoryStatus(Integer categoryId, Boolean status)
            throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            Category category = session.get(Category.class, categoryId);
            category.setStatus(status);
            transaction.commit();
            if (status) {
                Logger.info(Constants.LOG_CATEGORY_RESTORED + categoryId);
            } else {
                Logger.info(Constants.LOG_CATEGORY_REMOVED + categoryId);
            }
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.error(Constants.MESSAGE_REMOVE_CATEGORY_EXCEPTION, e);
            throw new DVDException(Constants.MESSAGE_REMOVE_CATEGORY_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Category getCategoryByValue(String categoryValue) throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Category> query = builder.createQuery(Category.class);
            Root<Category> root = query.from(Category.class);
            query.select(root).where(builder.
                      equal(root.get(Constants.LABEL_VALUE), categoryValue));
            return session.createQuery(query).uniqueResult();
        } catch (HibernateException e) {
            Logger.error(Constants.MESSAGE_CATEGORY_NOT_FOUND, e);
            throw new DVDException(Constants.MESSAGE_CATEGORY_NOT_FOUND);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Category getCategoryById(Integer categoryId) throws DVDException {

        Session session = null;
        try {
            session = factory.getSession();
            Filter filter = session.enableFilter(Constants.LABEL_DVD_FILTER);
            filter.setParameter(Constants.LABEL_STATUS, Constants.ACTIVE);
            List<DVD> dvds = new LinkedList<DVD>();
            Category category = session.get(Category.class, categoryId);
            for (DVD dvd: category.getDvds()) {
                dvds.add(dvd);
            }
            session.disableFilter(Constants.LABEL_DVD_FILTER);
            category.setDvds(dvds);
            return category;
        } catch (HibernateException e) {
            Logger.error(Constants.LOG_RETRIEVE_CATEGORIES_EXCEPTION, e);
            throw new DVDException(Constants.MESSAGE_RETRIEVE_CATEGORY_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public List<Category> getCategoriesByIds(List<Integer> ids)
            throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(Category.class);
            Root<Category> root = query.from(Category.class);
            Expression<Integer> id = root.get(Constants.LABEL_ID);
            query.select(root).where(id.in(ids));
            return session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            Logger.error(Constants.LOG_RETRIEVE_CATEGORIES_EXCEPTION);
            throw new DVDException(Constants.MESSAGE_RETRIEVE_CATEGORIES_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean removeDvdFromCategory(Integer dvdId, Integer categoryId)
            throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            Category category = session.get(Category.class, categoryId);
            Boolean flag = Boolean.FALSE;
            for (DVD dvd: category.getDvds()) {
                if (dvdId == dvd.getId()) {
                    flag = Boolean.TRUE;
                    category.getDvds().remove(dvd);
                    break;
                }
            }
            if (flag) {
                transaction.commit();
                Logger.info(dvdId + Constants.LOG_DVD_CATEGORY_REMOVED 
                                                                  + categoryId);
            }
            return flag;
        } catch (HibernateException e) {
            Logger.error(dvdId + Constants.LOG_REMOVE_DVD_CATEGORY_EXCEPTION
                                                               + categoryId, e);
            throw new DVDException(Constants.MESSAGE_REMOVE_DVD_CATEGORY_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }
}









