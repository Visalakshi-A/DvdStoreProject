package com.ideas2it.dvdstore.model;

import java.sql.Date;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.DVD;

/**
 * Orders model contains the id of the customer and DVD object purchased
 * in that order and date of purchase.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.model.DVD
 */
public class Order {

    private Integer id;
    private Integer customerId;
    private Address address;
    private DVD dvd;
    private Date orderDate;
    private Boolean status;

    public Order() {
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public DVD getDvd() {
        return dvd;
    }

    public void setDvd(DVD dvd) {
        this.dvd = dvd;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Overriding method to perform display operation in the specified format.
     *
     * @return customerDetails
     *         string containing all the class field's values.
     */
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append(id).append(customerId)
                    .append(dvd.getName()).append(Constants.SPACE)
                    .append(dvd.getPrice()).append(Constants.SPACE)
                    .append(orderDate);
        return orderDetails.toString();
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
        Order order = (Order)object;
        return (this.id == order.getId());
    }
}
