/**
 * Provides a class that stores properties of a dvd kept in a Dvd store.
 * Serves as a model for real time object DVD
 */
package com.ideas2it.dvdstore.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.utils.DateUtils;

/**
 * A class {@code DVD} to store the properties of the Dvd
 * name of dvd, language, type of dvd, number of dvds available
 * and rating of the DVD.
 * Uses getters and setters for all the members.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.model.Category
 * @see com.ideas2it.dvdstore.utils.DateUtils
 */
public class DVD {
    private Integer id;
    private String name;
    private String language;
    private String type;
    private Integer quantity;
    private Double price;
    private Double rating;
    private Date releaseDate;
    private Boolean status;
    private List<Category> categories = new ArrayList<Category>();

    public DVD() {
    }

    public DVD(String name, String language, String type, Integer quantity,
             Double price, Double rating, Date releaseDate, Boolean status) {
        this.name = name;
        this.language = language;
        this.type = type;
        this.quantity = quantity;
        this.rating = rating;
        this.price = price;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Category> getCategories() {
        return categories;
    } 

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * Overriding method for displaying the object from other class
     * in the specified format.
     * Uses {@code StringBuilder} to append different fields.
     *
     * @return dvdDetails
     *         String containing all the class field's values.
     */
    public String toString() {
        StringBuilder dvdDetails = new StringBuilder();
        dvdDetails.append(id)
            .append(Constants.SPACE).append(name)
            .append(Constants.SPACE).append(language)
            .append(Constants.SPACE).append(type)
            .append(Constants.SPACE).append(quantity)
            .append(Constants.SPACE).append(price)
            .append(Constants.SPACE).append(rating)
            .append(Constants.SPACE)
            .append(Constants.SPACE);
        for (Category category: categories) {
            //if (category.getStatus()) {
                dvdDetails.append(category.toString());
            //}
        }
        return dvdDetails.toString();
    }

    /**
     * Overrides the equals function to compare certain fields of two object.
     * Initially checks if both object has the same reference and
     * Checks for the fields name, language and type.
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
        DVD dvd = (DVD) object;
        /*return (this.name.equalsIgnoreCase(dvd.getName()) 
            && this.language.equalsIgnoreCase(dvd.getLanguage())
            && this.type.equalsIgnoreCase(dvd.getType()));*/
        return (this.id == dvd.getId());
    }
}












