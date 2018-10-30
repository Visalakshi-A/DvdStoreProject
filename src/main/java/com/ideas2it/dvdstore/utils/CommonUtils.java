package com.ideas2it.dvdstore.utils;

/**
 * Contains methods to validate user entered input.
 *
 * @version 1
 * @author Visalakshi
 */
public class CommonUtils {

    /**
     * Returns true when the email string is not empty and
     * length is less than maximum length.
     *
     * @param email
     *        String email of the Customer.
     *
     * @return boolean type returns true if value is valid else false.
     */
    public static Boolean validateEmail(String email) {
        return email.matches("[a-zA-Z0-9.]{5,40}[@][a-zA-Z]{2,}[.][a-zA-Z]+");
    }

    /**
     * Returns true when the contact number string is not empty and valid.
     *
     * @param contactNumber
     *        String contact number of the Customer.
     *
     * @return boolean type returns true if value is valid else false.
     */
    public static Boolean validateContactNumber(String contactNumber) {
        return contactNumber.matches("[789]{1}[0-9]{9}");
    }
}
