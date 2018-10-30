package com.ideas2it.dvdstore.dao;

import java.util.List;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.DVD;

/**
 * Accesses a Database that stores the objects of class {@code Category}
 * to perform manipulations related to DVD Categories on the Database
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.exception.UniqueKeyException
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.DVD
 */
public interface CategoryDao {

    /**
     * Adds the new category value into the DB if it does not exists
     * already and returns the status.
     *
     * @param category
     *        Category object to be added.
     *
     * @return returns true on success otherwise false.
     */
    Boolean addCategory(Category category) throws DVDException;

    /**
     * Retrieves all the entries in the table Category from the 
     * Database and returns it as list of Categories.
     *
     * @param status
     *        status of the categories to be retrieved.
     *
     * @return subCategories
     *         String value of all the sub-categories with index.
     */
    List<Category> retrieveCategories(Boolean status) throws DVDException;

    /**
     * Updates the category passed in the Store.
     *
     * @param category
     *        Category object to be updated.
     *
     * @return returns true on success otherwise false.
     */
    Boolean updateCategory(Category category) throws DVDException;

    /**
     * Removes the category from the DB.
     *
     * @param categoryId
     *        Integer value of category Id to be deleted.
     * @param status
     *        Boolean value of the status to be set.
     *
     * @return returns true on success else false.
     */
    Boolean setCategoryStatus(Integer categoryId, Boolean status)
        throws DVDException;

    /**
     * Getss the Category object of the passed category returns it.
     *
     * @param category
     *        String value to be checked.
     *
     * @return newCategory
     *         Category object.
     */
    Category getCategoryByValue(String category) throws DVDException;

    /**
     * Searches for Category object for the given Category Id and returns it.
     *
     * @param id
     *        Integer value of category id to be retrieved.
     *
     * @return category
     *         Category object having the passed id.
     */
    Category getCategoryById(Integer id) throws DVDException;

    /**
     * Searches for Category objects for the given Category Ids and returns it.
     *
     * @param ids
     *        List of Integer value of category ids to be retrieved.
     *
     * @return List of Category object having the passed id.
     */
    List<Category> getCategoriesByIds(List<Integer> ids) throws DVDException;

    /**
     * Gets the category object using the category Id and removes the DVD
     * from its DVD list containing the DVD Id.
     *
     * @param dvdId
     *        Integer value of the DVD Id.
     * @param categoryId
     *        Integer value of the Category Id.
     *
     * @return returns true on success otherwise false.
     */
    Boolean removeDvdFromCategory(Integer dvdId, Integer categoryId)
        throws DVDException;
}
