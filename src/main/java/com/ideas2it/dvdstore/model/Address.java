package com.ideas2it.dvdstore.model;

/**
 * Contains the address attributes like address line, city, state, country
 * and zip code.
 *
 * @author Visalakshi
 *
 */
public class Address {

    private Integer id;
    private Integer customerId;
    private String addressLine;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Overriding method for displaying the object from other class
     * in the specified format.
     * Uses {@code StringBuilder} to append different fields.
     *
     * @return addressDetails
     *         string containing all the class field's values.
     */
    public String toString() {
        StringBuilder addressDetails = new StringBuilder();
        addressDetails
            .append(addressLine)
            .append(", ").append(city)
            .append(", ").append(state)
            .append(", ").append(country)
            .append(", ").append(zipCode);
        return addressDetails.toString();
    }

    /**
     * Overrides the equals function to compare ids of two object
     * Initially checks if both object has the same reference
     * and compares the id of the two objects.
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
        Address address = (Address)object;
        return (this.id == address.getId());
    }
}
