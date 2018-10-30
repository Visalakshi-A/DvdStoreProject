package com.ideas2it.dvdstore.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Filter;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.DVDDao;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.logging.Logger;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.session.DVDStoreSessionFactory;

/**
 * Accesses a Database that stores the objects of class {@code Dvd}
 * to perform manipulations related to DVD on the Database.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.dao.DVDDao
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.logging.Logger
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.session.DVDStoreSessionFactory
 */
public class DVDDaoImpl implements DVDDao{

    private DVDStoreSessionFactory factory 
                                         = DVDStoreSessionFactory.getInstance();

    /** @{inheritDoc} */
    public Boolean insertDvd(DVD dvd) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            session.save(dvd);
            transaction.commit();
            Logger.info(Constants.LOG_DVD_ADDED + dvd.getId());
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            StringBuilder exceptionMessage = new StringBuilder();
            exceptionMessage.append(dvd.getName())
                            .append(Constants.MESSAGE_ADD_DVD_EXCEPTION);
            Logger.error(Constants.LOG_ADD_DVD_EXCEPTION, e);
            throw new DVDException(exceptionMessage.toString());
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public List<DVD> retrieveDvds(Boolean status) throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            Filter filter = session.enableFilter(Constants.LABEL_CATEGORY_FILTER);
            filter.setParameter(Constants.LABEL_STATUS, Constants.ACTIVE);
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DVD> query = builder.createQuery(DVD.class);
            Root<DVD> root = query.from(DVD.class);
            query.select(root).where(builder.
                               equal(root.get(Constants.LABEL_STATUS), status));
            List<DVD> dvds = session.createQuery(query).getResultList();
            session.disableFilter(Constants.LABEL_CATEGORY_FILTER);
            return dvds;
        } catch (HibernateException e) {
            Logger.error(Constants.MESSAGE_RETRIEVE_DVDS_EXCEPTION, e);
            throw new DVDException(Constants.MESSAGE_RETRIEVE_DVDS_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean updateDvd(DVD dvd) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            session.update(dvd);
            transaction.commit();
            Logger.info(Constants.LOG_DVD_UPDATED + dvd.getId());
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            StringBuilder exceptionMessage = new StringBuilder();
            exceptionMessage.append(dvd.getName())
                            .append(Constants.MESSAGE_UPDATE_DVD_EXCEPTION);
            Logger.error(Constants.LOG_UPDATE_DVD_EXCEPTION + dvd.getId(), e);
            throw new DVDException(exceptionMessage.toString());
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean deleteDvd(Integer dvdId) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            DVD dvd = (DVD) session.get(DVD.class, dvdId);
            dvd.setStatus(Constants.INACTIVE);
            session.update(dvd);
            transaction.commit();
            Logger.info(Constants.LOG_DVD_REMOVED + dvdId);
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.error(Constants.LOG_REMOVE_DVD_EXCEPTION + dvdId, e);
            throw new DVDException(Constants.MESSAGE_REMOVE_DVD_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public Boolean restoreDvd(Integer dvdId) throws DVDException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.getSession();
            transaction = session.beginTransaction();
            DVD dvd = session.get(DVD.class, dvdId);
            dvd.setStatus(Constants.ACTIVE);
            session.update(dvd);
            transaction.commit();
            Logger.info(Constants.LOG_DVD_RESTORED + dvdId);
            return Boolean.TRUE;
        } catch (HibernateException e) {
            if (null != transaction) {
                transaction.rollback();
            }
            Logger.error(Constants.LOG_RESTORE_DVD_EXCEPTION + dvdId, e);
            throw new DVDException(Constants.MESSAGE_RESTORE_DVD_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public DVD searchDvd(DVD dvd) throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DVD> query = builder.createQuery(DVD.class);
            Root<DVD> root = query.from(DVD.class);

            Predicate[] predicates = new Predicate[3];
            predicates[0] = builder.equal(root.get(Constants.LABEL_NAME),
                                                                 dvd.getName());
            predicates[1] = builder.equal(root.get(Constants.LABEL_LANGUAGE),
                                                             dvd.getLanguage());
            predicates[2] = builder.equal(root.get(Constants.LABEL_TYPE),
                                                                 dvd.getType());

            query.select(root).where(predicates);
            return (DVD) session.createQuery(query).uniqueResult();
        } catch (HibernateException e) {
            Logger.error(Constants.MESSAGE_DVD_NOT_FOUND, e);
            throw new DVDException(Constants.MESSAGE_DVD_NOT_FOUND);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public List<DVD> getDvdsByName(String dvdName) throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            Filter filter = session.enableFilter(Constants.LABEL_CATEGORY_FILTER);
            filter.setParameter(Constants.LABEL_STATUS, Constants.ACTIVE);
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DVD> query = builder.createQuery(DVD.class);
            Root<DVD> root = query.from(DVD.class);
            query.select(root).where(builder.
                                equal(root.get(Constants.LABEL_NAME), dvdName));
            List<DVD> dvds = session.createQuery(query).getResultList();
            session.disableFilter(Constants.LABEL_CATEGORY_FILTER);
            return dvds;
        } catch (HibernateException e) {
            Logger.error(Constants.MESSAGE_RETRIEVE_DVDS_EXCEPTION, e);
            throw new DVDException(Constants.MESSAGE_RETRIEVE_DVDS_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public DVD getDvdById(Integer dvdId) throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            Filter filter = session.enableFilter(Constants.LABEL_CATEGORY_FILTER);
            filter.setParameter(Constants.LABEL_STATUS, Constants.ACTIVE);
            DVD dvd = session.get(DVD.class, dvdId);
            session.disableFilter(Constants.LABEL_CATEGORY_FILTER);
            return dvd;
        } catch (HibernateException e) {
            Logger.error(Constants.LOG_DVD_NOT_FOUND + dvdId, e);
            throw new DVDException(Constants.MESSAGE_DVD_NOT_FOUND);
        } finally {
            factory.closeSession(session);
        }
    }

    /** @{inheritDoc} */
    public List<DVD> getDvdsByIds(List<Integer> selectedIds) throws DVDException {
        Session session = null;
        try {
            session = factory.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery query = builder.createQuery(DVD.class);
            Root<DVD> root = query.from(DVD.class);
            Expression<Integer> id = root.get(Constants.LABEL_ID);
            query.select(root).where(id.in(selectedIds));
            return session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            Logger.error(Constants.LOG_RETRIEVE_DVDS_EXCEPTION);
            throw new DVDException(Constants.MESSAGE_RETRIEVE_DVDS_EXCEPTION);
        } finally {
            factory.closeSession(session);
        }
    }
}










