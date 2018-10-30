package com.ideas2it.dvdstore.service;

import java.util.List;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.DVD;

/**
 * Contains all the methods for the Category related operations that 
 * can be performed.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.DVD
 */
public interface CategoryService {

    /**
     * Invokes the DAO method to check if the Category already exists in store.
     *
     * @param category
     *        String value of category to be checked.
     *
     * @return returns the Boolean value returned by DAO method.
     */
    Boolean checkAlreadyExists(String category) throws DVDException;

    /**
     * Invokes the DAO method to add the category and returns value
     * returned by DAO method.
     *
     * @param category
     *        String value of category to be added.
     *
     * @return String value of the status of the operation.
     */
    Boolean addCategory(String Category) throws DVDException;

    /**
     * Invokes the {@code CategoryDao} method to get the categories
     * and returns the list returned by the method.
     *
     * @param status
     *        Boolean status of the categories to be retrieved.
     *
     * @return Linked list of categories returned by the method.
     */
    List<Category> getCategories(Boolean status) throws DVDException;

    /**
     * Invokes the DAO method to update the category and returns
     * the value returned by the method.
     *
     * @param category
     *        Category object to be updated.
     *
     * @return returns the status of the operation.
     */
    Boolean updateCategory(Category category) throws DVDException;

    /**
     * Invokes the DAO method to remove Category and mapping or 
     * method to remove only category.
     *
     * @param categoryId
     *        Integer value of category Id to be deleted.
     *
     * @return returns the status of the operation.
     */
    Boolean removeCategory(Integer CategoryId) throws DVDException;

    /**
     * Invokes the DAO method to restore Category and mapping or 
     * method to restore only category.
     *
     * @param categoryId
     *        Integer value of the category Id to be restored.
     *
     * @return returns the status of the operation.
     */
    Boolean restoreCategory(Integer categoryId) throws DVDException;

    /**
     * Invokes the {@code CategoryDao} method to get the object of given
     * category Id and returns the string value.
     * 
     * @param categoryId
     *        Integer value of the category id.
     *
     * @return Category object returned by the method.
     */
    Category getCategoryById(Integer categoryId) throws DVDException;

    /**
     * Invokes the DAO method to get the Category objects 
     * for the given Category Ids and returns it.
     *
     * @param ids
     *        List of Integer value of category ids to be retrieved.
     *
     * @return List of Categories returned by the method.
     */
    List<Category> getCategoriesByIds(List<Integer> ids) throws DVDException;

    /**
     * Invokes the {@code CategoryDao} to get the Category using Id
     * and returns the DVD list in it.
     *
     * @param categoryId
     *        Integer value of the Id passed.
     *
     * @return returns the value returned by that method
     */
    List<DVD> getDvdsByCategoryId(Integer categoryId) throws DVDException;

    /**
     * Invokes the DAO method to remove DVD-Category mapping.
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
