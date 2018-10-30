package com.ideas2it.dvdstore.model;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.DVD;

/**
 * A class {@code Category} that stores the id and DVD Category
 * that can be associated with {@code DVD} objects.
 * Uses getters and setters for all fields.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.model.DVD
 */
public class Category {
    private Integer id;
    private String value;
    private Boolean status;
    private List<DVD> dvds = new ArrayList<DVD>();

    public Category() {
    }

    public Category(String value, Boolean status) {
        this.value = value;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<DVD> getDvds() {
        return dvds;
    }

    public void setDvds(List<DVD> dvds){
        this.dvds = dvds;
    }

    /**
     * Overriding method for displaying the object of the class
     * in the specified format.
     * Uses {@code StringBuilder} to append different fields.
     *
     * @return categoryInfo
     *         String containing all the class field's values.
     */
    public String toString() {
        StringBuilder categoryInfo = new StringBuilder();
        categoryInfo.append(Constants.SPACE).append(this.value);
        return categoryInfo.toString();
    }

    /**
     * Overrides the equals function to compare ids of two objects.
     * Initially checks if both object has the same reference and
     *
     * @param object
     *        Instance of any class can be passed.
     *
     * @return boolean
     *         returns true if both objects have same values in the specified
     *         fields otherwise returns false.
     */
    public boolean equals(Object object) {
        if (this == object) {
            return Boolean.TRUE;
        }
        if (null == object || this.getClass() != object.getClass()) {
            return Boolean.FALSE;
        }
        Category value = (Category) object;
        return (this.id == value.getId());
    }

}
