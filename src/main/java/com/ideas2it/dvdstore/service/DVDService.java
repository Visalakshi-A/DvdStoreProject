package com.ideas2it.dvdstore.service;

import java.util.List;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.DVD;

/**
 * Contains all the methods for the DVD related operations that 
 * can be performed.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.model.DVD
 */
public interface DVDService {

    /**
     * Invokes the DAO method to check if the DVD already exists in store.
     *
     * @param dvd
     *        DVD object to be checked.
     *
     * @return returns the Boolean value returned by DAO method.
     */
    Boolean checkAlreadyExistsDvd(DVD dvd) throws DVDException;

    /**
     * Invokes addNewDvd function of {@code DVDStoreDaoImpl} by passing the
     * new dvd as the parameter.
     * Then adds the DVD to the category.
     *
     * @param dvd
     *        DVD object to be added into the Database.
     *
     * @return Boolean status of the operation performed.
     */
    Boolean addNewDvd(DVD dvd) throws DVDException;

    /**
     * Invokes retrieveDvds function of {@code DVDStoreDaoImpl}
     * and for each DVD, category is retrieved and added to DVD category list.
     *
     * @param status
     *        Boolean status of the dvds to be retrieved.
     *
     * @return List returned by Dao method.
     */
    List<DVD> getAllDvds(Boolean status) throws DVDException;

    /**
     * Invokes the {@code CategoryService} to get the DVD list for the 
     * Category Id.
     *
     * @param categoryId
     *        Integer value of the Id passed.
     *
     * @return returns the value returned by that method
     */
    List<DVD> getDvdsByCategoryId(Integer categoryId) throws DVDException;

    /**
     * Invokes the DAO method to get the dvds list having the dvdName
     * and appends the dvd details into the string.
     *
     * @param dvdName
     *        String value of the Dvd name to be retrieved.
     *
     * @return List returned by Dao method.
     */
    List<DVD> getDvdsByName(String dvdName) throws DVDException;

    /**
     * Sets the new value to be updated to the DVD and then invokes
     * the update method in DAO.
     *
     * @param dvd
     *        DVD object to be updated.
     * @param fieldName
     *        Integer value for name of the field needs update.
     * @param fieldValue
     *        String value for new value for the field.
     *
     * @return output
     *         Boolean status of the operation performed.
     */
    Boolean updateDvd(DVD dvd) throws DVDException;

    /**
     * Removes the DVD assigned to the category and removes the DVD from Store.
     * Invokes removeDvd function of {@code DVDStoreDaoImpl} by passing the
     * dvd as the parameter.
     *
     * @param dvdIs
     *        Integer value of the Dvd Id.
     *
     * @return Boolean status of the operation performed.
     */
    Boolean removeDvd(Integer dvdId) throws DVDException;

    /**
     * Invokes the DAO method to restore a deleted category from Store.
     *
     * @param dvdId
     *        Integer value of the dvd Id to be restored.
     *
     * @return returns the status of the operation.
     */
    Boolean restoreDvd(Integer dvdId) throws DVDException;

    /**
     * Invokes the DAO method to get the DVD by id.
     *
     * @param dvdId
     *        Id of the DVD object to be retrieved.
     *
     * @return returns the searched object.
     */
    DVD getDvdById(Integer dvdId) throws DVDException;

    /**
     * Invokes the DAO method to get the DVD objects for the given
     * DVD Ids and returns it.
     *
     * @param ids
     *        List of Integer value of DVD ids to be retrieved.
     *
     * @return List of DVD objects returned by the method.
     */
    List<DVD> getDvdsByIds(List<Integer> ids) throws DVDException;

    /**
     * Invokes the getCategories method of {@code CategoryServie}
     * and returns the list returned by the method.
     *
     * @param status
     *        Boolean status of the categories to be checked.
     *
     * @return returns the linked list returned by the getCategories method.
     */
    List<Category> getCategories(Boolean status) throws DVDException;

    /**
     * Invokes the {@CategoryService} method to get the Category objects 
     * for the given Category Ids and returns it.
     *
     * @param ids
     *        List of Integer value of category ids to be retrieved.
     *
     * @return List of Categories returned by the method.
     */
    List<Category> getCategoriesByIds(List<Integer> ids) throws DVDException;

    /**
     * Validates the DVD id.
     *
     * @param id
     *        Integer value to be validated.
     * @param status
     *        Boolean status of the dvds to be retrieved.
     *
     * @return returns true when all constraints are satisfied else false.
     */
    Boolean checkAlreadyExistsDvdId(Integer id, Boolean status)
        throws DVDException;
}
