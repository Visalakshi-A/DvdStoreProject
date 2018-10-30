package com.ideas2it.dvdstore.model;

import com.ideas2it.dvdstore.common.enums.UserRole;
/**
 * Contains the user logging details id, name, password and role.
 *
 * @author Visalakshi
 */
public class User {

    private Integer id;
    private String name;
    private String password;
    private UserRole role;

    public User() {}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    /**
     * Overriding method for displaying the object from other class
     * in the specified format.
     * Uses {@code StringBuilder} to append different fields.
     *
     * @return userDetails
     *         string containing all the class field's values.
     */
    public String toString() {
        StringBuilder userDetails = new StringBuilder();
        userDetails
            .append(id)
            .append(", ").append(name)
            .append(", ").append(password)
            .append(", ").append(role);
        return userDetails.toString();
    }
}
