package com.ideas2it.dvdstore.service.impl;

import java.sql.Date;
import java.util.List;

import com.ideas2it.dvdstore.dao.DVDDao;
import com.ideas2it.dvdstore.dao.impl.DVDDaoImpl;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.DVDService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;

/**
 * Modifies the data from the Controller layer and sends it to the DAO layer
 * and returns the status of the operation performed in the DAO.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.dao.DVDDao
 * @see com.ideas2it.dvdstore.dao.impl.DVDDaoImpl
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.service.CategoryService
 * @see com.ideas2it.dvdstore.service.DVDService
 * @see com.ideas2it.dvdstore.service.impl.CategoryServiceImpl
 */
public class DVDServiceImpl implements DVDService {

    private DVDDao dvdDao = new DVDDaoImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    /** @{inheritDoc} */
    public Boolean checkAlreadyExistsDvd(DVD dvd) throws DVDException {
        DVD oldDvd = dvdDao.searchDvd(dvd);
        if (null != oldDvd) {
            return oldDvd.getStatus();
        }
        return null;
    }

    /** @{inheritDoc} */
    public Boolean addNewDvd(DVD dvd) throws DVDException {
        dvd.setStatus(Boolean.TRUE);
        return (dvdDao.insertDvd(dvd));
    }

    /** @{inheritDoc} */
    public List<DVD> getAllDvds(Boolean status) throws DVDException {
        return dvdDao.retrieveDvds(status);
    }

    /** @{inheritDoc} */
    public List<DVD> getDvdsByCategoryId(Integer categoryId) throws DVDException {
        return categoryService.getDvdsByCategoryId(categoryId);
    }

    /** @{inheritDoc} */
    public List<DVD> getDvdsByName(String dvdName) throws DVDException {
        return dvdDao.getDvdsByName(dvdName);
    }

    /** @{inheritDoc} */
    public Boolean updateDvd(DVD dvd) throws DVDException {
        dvd.setStatus(Boolean.TRUE);
        DVD existingDvd = dvdDao.searchDvd(dvd);
        if (null != existingDvd) {
            if (dvd.getId() != existingDvd.getId()) {
                return null;
            }
        }
        return dvdDao.updateDvd(dvd);
    }

    /** @{inheritDoc} */
    public Boolean removeDvd(Integer dvdId) throws DVDException {
        return dvdDao.deleteDvd(dvdId);
    }

    /** @{inheritDoc} */
    public Boolean restoreDvd(Integer dvdId) throws DVDException {
        return dvdDao.restoreDvd(dvdId);
    }

    /** @{inheritDoc} */
    public DVD getDvdById(Integer dvdId) throws DVDException {
        return dvdDao.getDvdById(dvdId);
    }

    /** @{inheritDoc} */
    public List<DVD> getDvdsByIds(List<Integer> dvdIds) throws DVDException {
        return dvdDao.getDvdsByIds(dvdIds);
    }

    /** @{inheritDoc} */
    public List<Category> getCategories(Boolean status) throws DVDException {
        return categoryService.getCategories(status);
    }

    /** @{inheritDoc} */
    public List<Category> getCategoriesByIds(List<Integer> ids)
            throws DVDException {
        return categoryService.getCategoriesByIds(ids);
    }

    /** @{inheritDoc} */
    public Boolean checkAlreadyExistsDvdId(Integer id, Boolean status)
            throws DVDException {
        DVD dvd = dvdDao.getDvdById(id);
        if (status == dvd.getStatus()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /** @{inheritDoc} */
    public Boolean checkQuantity(Integer dvdId) throws DVDException {
        DVD dvd = dvdDao.getDvdById(dvdId);
        Integer quantity = dvd.getQuantity();
        return (0 != quantity);
    }

    /** @{inheritDoc} */
    public Boolean updateQuantity(Integer dvdId) throws DVDException {
        DVD dvd = dvdDao.getDvdById(dvdId);
        dvd.setQuantity(dvd.getQuantity() - 1);
        return dvdDao.updateDvd(dvd);
    }
}











