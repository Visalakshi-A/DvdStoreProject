package com.ideas2it.dvdstore.service.impl;

import java.util.ArrayList;
import java.util.List;	

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.dao.CategoryDao;
import com.ideas2it.dvdstore.dao.impl.CategoryDaoImpl;
import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.DVD;
import com.ideas2it.dvdstore.service.CategoryService;

/**
 * Modifies the data from the Controller layer and sends it to the DAO layer
 * and returns the status of the operation performed in the DAO.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.dao.Constants
 * @see com.ideas2it.dvdstore.dao.CategoryDao
 * @see com.ideas2it.dvdstore.dao.impl.CategoryDaoImpl
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.DVD
 * @see com.ideas2it.dvdstore.service.CategoryService
 */
public class CategoryServiceImpl implements CategoryService{

    private CategoryDao categoryDao = new CategoryDaoImpl();

    /** @{inheritDoc} */
    public Boolean checkAlreadyExists(String category) throws DVDException {
        Category newCategory = categoryDao.getCategoryByValue(category);
        if (null != newCategory) {
            return newCategory.getStatus();
        }
        return null;
    }

    /** @{inheritDoc} */
    public Boolean addCategory(String category) throws DVDException {
        Category newCategory = new Category(category, Constants.ACTIVE);
        return categoryDao.addCategory(newCategory);
    }

    /** @{inheritDoc} */
    public List<Category> getCategories(Boolean status) throws DVDException {
        return categoryDao.retrieveCategories(status);
    }

    /** @{inheritDoc} */
    public Boolean updateCategory(Category category) throws DVDException {
        Category existingCategory = 
                            categoryDao.getCategoryByValue(category.getValue());
        if (null != existingCategory) {
            if (category.getId() != existingCategory.getId()) {
                return null;
            }
        }
        return categoryDao.updateCategory(category);
    }

    /** @{inheritDoc} */
    public Boolean removeCategory(Integer categoryId) throws DVDException {
        return categoryDao.setCategoryStatus(categoryId, Constants.INACTIVE);
    }

    /** @{inheritDoc} */
    public Boolean restoreCategory(Integer categoryId) throws DVDException {
        return categoryDao.setCategoryStatus(categoryId, Constants.ACTIVE);
    }

    /** @{inheritDoc} */
    public Category getCategoryById(Integer categoryId) throws DVDException {
        return categoryDao.getCategoryById(categoryId);
    }

    /** @{inheritDoc} */
    public List<Category> getCategoriesByIds(List<Integer> ids) 
            throws DVDException {
        return categoryDao.getCategoriesByIds(ids);
    }

    /** @{inheritDoc} */
    public List<DVD> getDvdsByCategoryId(Integer categoryId) throws DVDException {
        return categoryDao.getCategoryById(categoryId).getDvds();
    }

    /** @{inheritDoc} */
    public Boolean removeDvdFromCategory(Integer dvdId, Integer categoryId)
            throws DVDException {
        return categoryDao.removeDvdFromCategory(dvdId, categoryId);
    }
}










