package com.ideas2it.dvdstore.model;

import java.util.LinkedList;
import java.util.List;

import com.ideas2it.dvdstore.common.Constants;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Order;
import com.ideas2it.dvdstore.model.User;

/**
 * A class {@code Customer} stores all the necessary customer details
 * like Id, Name, Email, Contact Number and Address.
 * Contains all getters and setters, parameterized contructor
 * overrides toString() for display.
 *
 * @author Visalakshi
 *
 * @see com.ideas2it.dvdstore.common.Constants
 * @see com.ideas2it.dvdstore.model.Order
 * @see com.ideas2it.dvdstore.model.Order
 */
public class Customer {
    private Integer id;
    private String name;
    private String email;
    private String contactNumber;
    private List<Address> address = new LinkedList<Address>();
    private Boolean status;
    private User user = new User();
    private List<Order> orders = new LinkedList<Order>();

    public Customer() {
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

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * Overriding method for displaying the object from other class
     * in the specified format.
     * Uses {@code StringBuilder} to append different fields.
     *
     * @return customerDetails
     *         string containing all the class field's values.
     */
    public String toString() {
        StringBuilder customerDetails = new StringBuilder();
        customerDetails
            .append(" Id: ").append(id)
            .append(" Name: ").append(name)
            .append(" Email: ").append(email)
            .append(" Contact Number: ").append(contactNumber)
            .append(" Address: ").append(address);
        for (Order order: orders) {
             customerDetails.append(order.toString());
        }
        for (Address add: address) {
             customerDetails.append(add.toString());
        }
        return customerDetails.toString();
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
        Customer customer = (Customer)object;
        return (this.id == customer.getId());
    }
}
