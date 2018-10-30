package com.ideas2it.dvdstore.dao;

import java.util.List;

import com.ideas2it.dvdstore.exception.DVDException;
import com.ideas2it.dvdstore.model.DVD;

/**
 * Accesses a Database that stores the objects of class {@code Dvd}
 * to perform manipulations related to DVD table on the Database
 * 
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.exception.DVDException
 * @see com.ideas2it.dvdstore.model.DVD
 */
public interface DVDDao {

    /**
     * Gets all the field values for the {@code DVD} through parameters
     * and adds to the Database if it does not already exists.
     *
     * @param dvd
     *        DVD object to be added into the Database.
     *
     * @return boolean true for success otherwise false.
     */
    Boolean insertDvd(DVD dvd) throws DVDException;

    /**
     * Updates the corressponding DVD record in the DB by updating all
     * the fields of the DVD.
     *
     * @param dvd
     *        DVD object to be updated.
     *
     * @return boolean true for success otherwise false.
     */
    Boolean updateDvd(DVD dvd) throws DVDException;

    /**
     * Gets all the field values required to find unique entry in the Database
     * and removes the DVD from the Database.
     *
     * @param dvdIs
     *        Integer value of the Dvd Id.
     *
     * @return retrurns true for success otherwise false.
     */
    Boolean deleteDvd(Integer dvdId) throws DVDException;

    /**
     * Adds all the DVDs in the Database into a Linked list
     * and returns it.
     *
     * @param status
     *        status of the dvds to be retrieved.
     *
     * @return dvds
     *         DVD objects of all the DVDs in the Database.
     */
    List<DVD> retrieveDvds(Boolean status) throws DVDException;

    /**
     * Restores the inactive dvds by setting the status to active.
     *
     * @param dvdId
     *        Integer value of the dvd Id to be restored.
     *
     * @return returns true on Success else false.
     */
    Boolean restoreDvd(Integer dvdId) throws DVDException;

    /**
     * Gets the {@code DVD} object of the DVD from the Database using its
     * name, language and type.
     *
     * @param dvd
     *        DVD object with unique properties to be searched.
     *
     * @return dvd
     *         DVD object from store.
     */
    DVD searchDvd(DVD dvd) throws DVDException;

    /**
     * Retrieves all the DVDs present in the DB with the given name
     * adds into a list and returns the list.
     *
     * @param dvdName
     *        String value of the Dvd name to be retrieved.
     *
     * @return dvds
     *         List of dvds retrieved.
     */
    List<DVD> getDvdsByName(String name) throws DVDException;

    /**
     * Checks if the dvd is already present in the Database
     * Retrieves all the dvds having the same name, language and category.
     * If there is atleast 1 record returns the DVD otherwise returns null.
     * 
     * @param dvd
     *        DVD object that needs to be checked for duplicate.
     *
     * @return dvd
     *         DVD object containing the values in the Database.
     */
    DVD getDvdById(Integer dvdId) throws DVDException;

    /**
     * Searches for DVD objects for the given DVD Ids and returns it.
     *
     * @param ids
     *        List of Integer value of DVD ids to be retrieved.
     *
     * @return List of DVD objects having the passed id.
     */
    List<DVD> getDvdsByIds(List<Integer> ids) throws DVDException;
}
