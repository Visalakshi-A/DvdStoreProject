package com.ideas2it.dvdstore.common;

import java.sql.Date;

/**
 * Contains all the variables having constant values
 * and all the string values used in DVD Store.
 *
 * @author Visalakshi
 */
public class Constants {

    public static final int DEFAULT_INT = 0;

    public static final int MIN_QUANTITY = 0;

    public static final int MAX_LENGTH = 50;

    public static final Boolean ACTIVE = Boolean.TRUE;

    public static final Boolean INACTIVE = Boolean.FALSE;

    public static final char NO = 'n';

    public static final char YES = 'y';

    public static final double DEFAULT_DOUBLE = 0.0;

    public static final double MAX_PRICE = 250.0;

    public static final double MAX_RATING = 10.0;

    public static final double MIN_PRICE = 25.0;

    public static final double MIN_RATING = 0.0;

    public static final String SPACE = " ";

    public static final String LABEL_ADD_ADDRESS = "add-address";

    public static final String LABEL_ADD_ADDRESS_FORM = "add-address-form";

    public static final String LABEL_ADDRESS = "address";

    public static final String LABEL_ADDRESS_LINE = "addressLine";

    public static final String LABEL_AGO = "ago";

    public static final String LABEL_BACK = "back";

    public static final String LABEL_CANCEL = "cancel";

    public static final String LABEL_CATEGORIES = "categories";

    public static final String LABEL_CATEGORY = "category";
    
    public static final String LABEL_CATEGORY_FILTER = "categoryStatusFilter";

    public static final String LABEL_CATEGORY_ID = "categoryId";

    public static final String LABEL_CATEGORY_MENU = "category-menu";

    public static final String LABEL_COMMAND = "command";

    public static final String LABEL_VALUE = "value";

    public static final String LABEL_CITY = "city";

    public static final String LABEL_CONTACT_NUMBER = "contactNumber";

    public static final String LABEL_COUNTRY = "country";

    public static final String LABEL_CUSTOMER = "customer";

    public static final String LABEL_CUSTOMERS = "customers";

    public static final String LABEL_CUSTOMER_ADDRESS = "customerAddress";

    public static final String LABEL_CUSTOMER_PROFILE = "customer-profile";

    public static final String LABEL_DAYS = "Days ";

    public static final String LABEL_DELETE = "delete";

    public static final String LABEL_DELETE_ADDRESS = "delete-address";

    public static final String LABEL_DELIVERED = "delivered";

    public static final String LABEL_DISPLAY = "display";

    public static final String LABEL_DISPLAY_CUSTOMERS = "display-customers";

    public static final String LABEL_DISPLAY_DVDS = "display-dvds";

    public static final String LABEL_DISPLAY_DVDS_BY_CATEGORY = "display-dvds-by-category";

    public static final String LABEL_DISPLAY_DVDS_BY_NAME = "display-dvds-by-name";

    public static final String LABEL_DISPLAY_ORDERS = "display-orders";
    
    public static final String LABEL_DVD_FILTER = "dvdStatusFilter";

    public static final String LABEL_DVD = "dvd";

    public static final String LABEL_DVD_ID = "dvdId";

    public static final String LABEL_DVDS = "dvds";

    public static final String LABEL_EMAIL = "email";

    public static final String LABEL_FORM = "form";

    public static final String LABEL_FORWARD = "forward";

    public static final String LABEL_FULL_HD = "Full HD";

    public static final String LABEL_HD = "HD";

    public static final String LABEL_HOME = "home";

    public static final String LABEL_ID = "id";

    public static final String LABEL_INACTIVE = "inactive";

    public static final String LABEL_INSERT = "insert";

    public static final String LABEL_INSERT_FORM = "insert-form";

    public static final String LABEL_LANGUAGE = "language";

    public static final String LABEL_LOGIN = "login";

    public static final String LABEL_LOGIN_FORM = "login-form";

    public static final String LABEL_LOGOUT = "logout";

    public static final String LABEL_MESSAGE = "message";

    public static final String LABEL_MONTHS = "Months ";

    public static final String LABEL_NAME = "name";

    public static final String LABEL_NORMAL = "Normal";

    public static final String LABEL_OPEN = "open";

    public static final String LABEL_OPERATION = "operation";

    public static final String LABEL_ORDERS = "orders";

    public static final String LABEL_PASSWORD = "password";

    public static final String LABEL_PRICE = "price";

    public static final String LABEL_PURCHASE = "purchase";

    public static final String LABEL_PURCHASE_FORM = "purchase-form";    

    public static final String LABEL_QUANTITY = "quantity";

    public static final String LABEL_RATING = "rating";

    public static final String LABEL_REDIRECT = "redirect:";

    public static final String LABEL_REGISTER = "register";

    public static final String LABEL_REGISTER_FORM = "register-form";

    public static final String LABEL_RELEASE_DATE = "releaseDate";

    public static final String LABEL_RELOAD = "reload";

    public static final String LABEL_REMOVE_DVD = "remove-dvd";

    public static final String LABEL_RESTORE = "restore";

    public static final String LABEL_RESTORE_DISPLAY = "restore-display";

    public static final String LABEL_SEARCH = "search";

    public static final String LABEL_SELECTED_CATEGORY = "selectedCategory";

    public static final String LABEL_SELECTED_DVDS = "selectedDvds";

    public static final String LABEL_SIGNUP = "sign-up";

    public static final String LABEL_SIGNUP_FORM = "sign-up-form";

    public static final String LABEL_STATE = "state";

    public static final String LABEL_STATUS = "status";

    public static final String LABEL_TYPE = "type";

    public static final String LABEL_UPDATE = "update";

    public static final String LABEL_UPDATE_FORM = "update-form";

    public static final String LABEL_UPDATE_ADDRESS = "update-address";

    public static final String LABEL_UPDATE_ADDRESS_FORM = "update-address-form";

    public static final String LABEL_UPDATE_DETAILS_FORM = "update-details-form";

    public static final String LABEL_USER = "user";

    public static final String LABEL_USERID = "userId";

    public static final String LABEL_USER_NAME = "userName";

    public static final String LABEL_VIEW_CUSTOMER_ADDRESS = "view-customer-address";

    public static final String LABEL_VIEW_CUSTOMER_ORDERS = "view-customer-orders";

    public static final String LABEL_YEAR = "Year ";

    public static final String LABEL_ZIP_CODE = "zipCode";

    public static final String LOG_ADD_CUSTOMER_EXCEPTION =
        "Customer could not be added. ";

    public static final String LOG_ADD_DVD_EXCEPTION = "Dvd could not be added. ";

    public static final String LOG_ADD_ORDER_EXCEPTION = "Order could not be added. ";

    public static final String LOG_CATEGORY_ADDED = "Category added Id-";

    public static final String LOG_CATEGORY_MAPPING_REMOVED  =
        "A category and mapping removed Id-";

    public static final String LOG_CATEGORY_MAPPING_RESTORED =
        "A category and mapping restored Id-";

    public static final String LOG_CATEGORY_REMOVED = "Category removed Id-";

    public static final String LOG_CATEGORY_RESTORED = "Category restored Id-";

    public static final String LOG_CATEGORY_UPDATED = "Category updated Id-";

    public static final String LOG_CUSTOMER_ADDED = "Customer added Id-";

    public static final String LOG_CUSTOMER_UPDATED =
        "Customer details updated Id-";

    public static final String LOG_DELETE_ORDER_EXCEPTION =
        " Problem occured while deleting Order Id-";

    public static final String LOG_DVD_ADDED = "DVD added to DB Id-";

    public static final String LOG_DVD_CATEGORY_REMOVED =
        "-DvdId removed from Category Id-";

    public static final String LOG_DVD_CATEGORY_UPDATED  =
        "DVD categories updated Id-";

    public static final String LOG_DVD_NOT_FOUND =
        " DVD could not be found Id-";

    public static final String LOG_DVD_PURCHASED  =
        "[Id] DVD purchase by customer Id-";

    public static final String LOG_DVD_REMOVED = "DVD removed Id-";

    public static final String LOG_DVD_RESTORED = "DVD restored Id-";

    public static final String LOG_DVD_UPDATED = "DVD updated Id-";

    public static final String LOG_LOGGED_IN = "Logged in by User - ";

    public static final String LOG_PROBLEM_HASHING_PASSWORD =
        " Problem occured while Hashing password. ";

    public static final String LOG_REMOVE_DVD_CATEGORY_EXCEPTION =
        "-DvdId could not be removed from Category Id-";

    public static final String LOG_REMOVE_DVD_EXCEPTION  =
        "DVD could not be removed Id-";

    public static final String LOG_RESTORE_DVD_EXCEPTION =
        "DVD could not be restored Id-";

    public static final String LOG_RETRIEVE_CATEGORIES_EXCEPTION =
        " Categories could not be retrieved. ";

    public static final String LOG_RETRIEVE_CUSTOMER_EXCEPTION =
        " Customer details could not be retrieved. Id-";

    public static final String LOG_RETRIEVE_CUSTOMERS_EXCEPTION =
        " Customers could not be retrieved. ";

    public static final String LOG_RETRIEVE_DVD_EXCEPTION =
        " DVD could not be retrieved. ";

    public static final String LOG_RETRIEVE_DVDS_EXCEPTION =
        " DVDs could not be retrieved. ";

    public static final String LOG_RETRIEVE_ORDERS_EXCEPTION =
        "Orders could not be retrieved. ";

    public static final String LOG_LOGIN_USER_EXCEPTION =
        " User details could not be retrieved. Name-";

    public static final String LOG_SET_ORDER_STATUS_EXCEPTION =
        " Could not modify order Status. ";

    public static final String LOG_SIGNUP_EXCEPTION = "Could not perform Sign Up";

    public static final String LOG_UPDATE_DVD_EXCEPTION =
        "DVD could not be updated Id-";

    public static final String LOG_USER_SIGNUP = "Signed in by User - ";

    public static final String LOG_USER_SIGNUP_EXCEPTION = "Sign Up failed.. ";

    public static final String MESSAGE_ADD_ADDRESS_FAILURE =
        " Address not added. Please enter different values";

    public static final String MESSAGE_ADD_ADDRESS_SUCCESS =
        " Address added successfully!!";

    public static final String MESSAGE_ADD_CATEGORY_EXCEPTION =
        " Category may be already present. Try restoring the category";

    public static final String MESSAGE_ADD_CATEGORY_FAILURE =
        " Category not added. Please enter different values";

    public static final String MESSAGE_ADD_CATEGORY_SUCCESS =
        " Category added successfully!!";

    public static final String MESSAGE_ADD_CUSTOMER_EXCEPTION =
        " Could not register customer details. Please try again later.";

    public static final String MESSAGE_ADD_DVD_EXCEPTION =
        " DVD details could not be added. Please try again later.";

    public static final String MESSAGE_ADD_DVD_FAILURE  =
        " DVD already exists!! Please enter different values.";

    public static final String MESSAGE_ADD_DVD_SUCCESS =
        " DVD added to Store Successfully!!";

    public static final String MESSAGE_CANCEL_ORDER_FAILURE =
        "Problem occured while cancelling Order.";

    public static final String MESSAGE_CANCEL_ORDER_SUCCESS =
        "Order cancellation was Successful!!";

    public static final String MESSAGE_CHOOSE_CATEGORY =
        "Choose any category";

    public static final String MESSAGE_CATEGORY_ALREADY_EXISTS =
        " Category already exists in Store.";

    public static final String MESSAGE_CATEGORY_NOT_FOUND =
        " Category not found";

    public static final String MESSAGE_CATEGORY_USED_BY_DVD =
        "Some DVDs use this category. Do you want to remove (y/n)";

    public static final String MESSAGE_CHOOSE_DVD_ID =
        "Choose any DVD Id ";

    public static final String MESSAGE_CHOOSE_DVD_TYPE =
        "Choose type: 1-Normal 2-HD 3-FULL HD";

    public static final String MESSAGE_CONTINUE =
        "Do you want to continue (y/n) ";

    public static final String MESSAGE_CUSTOMER_ALREADY_EXISTS =
        "Email-Id or Contact Number is already registered. Please use different details.";

    public static final String MESSAGE_DATE_IN_FUTURE =
        "Given date is in Future. Please enter correct date.";

    public static final String MESSAGE_DELETE_ADDRESS_FAILURE =
        " Address could not be deleted. Please try again later";

    public static final String MESSAGE_DELETE_ADDRESS_SUCCESS =
        " Address deleted successfully!!";

    public static final String MESSAGE_DVD_ALREADY_EXISTS =
        " DVD already exists in the Store.";

    public static final String MESSAGE_DVD_NOT_FOUND =
        " DVD could not be found.";

    public static final String MESSAGE_DVD_OUT_OF_STOCK =
        " DVD is out of Stock.";

    public static final String MESSAGE_EMPTY_LIST = "List is empty.";

    public static final String MESSAGE_ENTER_ADDRESS = "Enter address: ";

    public static final String MESSAGE_ENTER_CATEGORY = "Enter new Category: ";

    public static final String MESSAGE_ENTER_CONTACT_NUMBER =
        "Enter Contact Number: ";

    public static final String MESSAGE_ENTER_CUSTOMER_NAME =
        "Enter Customer Name: ";

    public static final String MESSAGE_ENTER_DAY = "Enter Day[1-31]: ";

    public static final String MESSAGE_ENTER_DVD_NAME = "Enter DVD name: ";

    public static final String MESSAGE_ENTER_EMAIL_ID = "Enter email-id: ";

    public static final String MESSAGE_ENTER_LANGUAGE = "Enter language: ";

    public static final String MESSAGE_ENTER_MONTH = "Enter Month[1-12]: ";

    public static final String MESSAGE_ENTER_PRICE = "Enter DVD price: ";

    public static final String MESSAGE_ENTER_QUANTITY = "Enter quantity: ";

    public static final String MESSAGE_ENTER_RATING = "Enter rating: ";

    public static final String MESSAGE_ENTER_YEAR = "Enter Year: ";

    public static final String MESSAGE_FIELD_NAME  =
        "Choose the field name to update";

    public static final String MESSAGE_INVALID_ADDRESS =
        "Please enter a valid address.";

    public static final String MESSAGE_INVALID_CATEGORY_ID =
        "Please choose correct Category Index.";

    public static final String MESSAGE_INVALID_CATEGORY_VALUE =
        "Please enter correct value for Category.";

    public static final String MESSAGE_INVALID_CHOICE =
        "Please choose the valid option.";

    public static final String MESSAGE_INVALID_CONTACT_NUMBER =
        "Contact Number field is not valid.";

    public static final String MESSAGE_INVALID_CUSTOMER_NAME =
        "Customer Name field is empty or too long.";

    public static final String MESSAGE_INVALID_DATE =
        "Please enter a valid Date.";

    public static final String MESSAGE_INVALID_DVD_ID =
        "Please choose a valid Dvd Id.";

    public static final String MESSAGE_INVALID_EMAIL_ID =
        "Please enter a valid e-mail id.";

    public static final String MESSAGE_INVALID_EXIT =
        "Please choose 'y' or 'n' ";

    public static final String MESSAGE_INVALID_INTEGER =
        "Please enter a Number.";

    public static final String MESSAGE_INVALID_LANGUAGE =
        "DVD language field is empty or too long.";

    public static final String MESSAGE_INVALID_NAME =
        "DVD name field is empty or too long.";

    public static final String MESSAGE_INVALID_PRICE =
        "Price must be between the range 25.0 to 250.0";

    public static final String MESSAGE_INVALID_QUANTITY =
        "DVD quantity field should be greater than 0";

    public static final String MESSAGE_INVALID_RATING =
        "Rating field should be between 1-10";

    public static final String MESSAGE_INVALID_TYPE =
        "Please choose types 1,2 or 3";

    public static final String MESSAGE_LOGIN_FIRST =
        "Please login first";

    public static final String MESSAGE_LOGIN_SUCCESS =
        "Logged in successfully!!\nWelcome ";

    public static final String MESSAGE_LOGOUT_SUCCESSFUL =
        "Logged out successfully!!";

    public static final String MESSAGE_NO_CONNECTION_ESTABLISHED =
        "Problem connecting to Server. Please try again later.";

    public static final String MESSAGE_NO_TRANSACTION =
        "\nNo transactions made till now.";

    public static final String MESSAGE_OPTIONS =
        "\n\nPlease choose any option to perform on the DVD Store:\n";

    public static final String MESSAGE_PAGE_NOT_AVAILABLE =
        "Sorry!! Web page not available";

    public static final String MESSAGE_PROBLEM_PURCHASING =
        "Could not purchase DVD. Please try again later.";

    public static final String MESSAGE_PROBLEM_LOGIN_USER =
        " Could not perform login operation. Please try again later.";

    public static final String MESSAGE_PURCHASE_DVD =
        "Do you want to purchase DVD (y/n) ";

    public static final String MESSAGE_PURCHASE_DVD_FAILURE =
        " Could not perform purchase operation.";

    public static final String MESSAGE_PURCHASE_DVD_SUCCESS =
        " DVD purchased successfully!!";

    public static final String MESSAGE_REGISTER_CUSTOMER_FAILURE =
        " Customer cannot be registered.";

    public static final String MESSAGE_REGISTER_CUSTOMER_SUCCESS =
        " Customer registration successful!!\n Welcome ";

    public static final String MESSAGE_REMOVE_CATEGORY_EXCEPTION =
        " Category could not be removed or restored. Please try again later.";

    public static final String MESSAGE_REMOVE_CATEGORY_FAILURE =
        " Category not removed.";

    public static final String MESSAGE_REMOVE_CATEGORY_SUCCESS =
        " Category removed Successfully!!";

    public static final String MESSAGE_REMOVE_DVD_CATEGORY_FAILURE =
        " DVD could not be removed from Category";

    public static final String MESSAGE_REMOVE_DVD_CATEGORY_SUCCESS =
        " DVD removed from Category Successfully!!";

    public static final String MESSAGE_REMOVE_DVD_CATEGORY_EXCEPTION =
        " DVD could not be removed from category";

    public static final String MESSAGE_REMOVE_DVD_EXCEPTION =
        " Problem while removing DVD. Please try again later.";

    public static final String MESSAGE_REMOVE_DVD_FAILURE =
        " DVD does not exist in the Store.";

    public static final String MESSAGE_REMOVE_DVD_SUCCESS =
        " DVD removed Successfully!!";

    public static final String MESSAGE_RESTORE_CATEGORY =
        " Category has been removed. Try restoring the Category.";

    public static final String MESSAGE_RESTORE_DVD =
        " DVD has been removed. Try restoring the DVD.";

    public static final String MESSAGE_RESTORE_CATEGORY_FAILURE =
        " Category cannot be restored.";

    public static final String MESSAGE_RESTORE_CATEGORY_SUCCESS =
        " Category restored Successfully!!";

    public static final String MESSAGE_RESTORE_DVD_EXCEPTION =
        " Problem while restoring DVD. Please try again later.";

    public static final String MESSAGE_RESTORE_DVD_FAILURE =
        " Dvd cannot be restored.";

    public static final String MESSAGE_RESTORE_DVD_SUCCESS =
        " Dvd restored Successfully!!";

    public static final String MESSAGE_RETRIEVE_CATEGORIES_EXCEPTION =
        "Categories could not be retrieved. Please try again later.";

    public static final String MESSAGE_RETRIEVE_CATEGORY_EXCEPTION =
        "Category could not be retrieved. Please try again later.";

    public static final String MESSAGE_RETRIEVE_CUSTOMER_EXCEPTION =
        " details could not be retrieved. Please try again later.";

    public static final String MESSAGE_RETRIEVE_CUSTOMER_FAILURE =
        "Your details could not be retrieved. Please try again later.";

    public static final String MESSAGE_RETRIEVE_CUSTOMERS_EXCEPTION =
        "Customers details could not be retrieved. Please try again later.";

    public static final String MESSAGE_RETRIEVE_ORDERS_EXCEPTION =
        " Orders could not be retrieved.";

    public static final String MESSAGE_RETRIEVE_DVD_CATEGORIES_EXCEPTION =
        "Categories of the DVD could not be retrieved.";

    public static final String MESSAGE_RETRIEVE_DVDS_EXCEPTION =
        " DVDs could not be retrieved. Please try again later.";

    public static final String MESSAGE_RETRY_LOGIN =
        "Given user name or password is wrong. Please try again.";

    public static final String MESSAGE_SET_ORDER_STATUS_EXCEPTION =
        " Could not perform the operation. Please try again later. ";

    public static final String MESSAGE_SIGNUP_EXCEPTION = 
        " Could not Sign In. Please try again later.";

    public static final String MESSAGE_TITLE = "\n DVD Store ";

    public static final String MESSAGE_UNAUTHORIZED_ACCESS =
        "Unauthorized access";

    public static final String MESSAGE_UNAUTHORIZED_ACCESS_BY_CUSTOMER =
        "Unauthorized access by customer - ";

    public static final String MESSAGE_UNAUTHORIZED_ACCESS_BY_ADMIN =
        "Unauthorized access by Admin";

    public static final String MESSAGE_UPDATE_ADDRESS_FAILURE =
        " Address could not be updated. Please try again later";

    public static final String MESSAGE_UPDATE_ADDRESS_SUCCESS =
        " Address updated successfully!!";

    public static final String MESSAGE_UPDATE_CATEGORY_EXCEPTION =
        "Problem occured while updating category. Please try again later.";

    public static final String MESSAGE_UPDATE_CATEGORY_FAILURE =
        " Category could not be updated. Please try again later.";

    public static final String MESSAGE_UPDATE_CATEGORY_SUCCESS =
        " Category updated successfully!!";

    public static final String MESSAGE_UPDATE_CUSTOMER_EXCEPTION =
        "Problem occured while updating customer. Please try again later.";

    public static final String MESSAGE_UPDATE_CUSTOMER_FAILURE =
        " details could not be updated. Please try again later.";

    public static final String MESSAGE_UPDATE_DVD_EXCEPTION =
        "Problem while updating DVD. Please try again later.";

    public static final String MESSAGE_UPDATE_CUSTOMER_SUCCESS =
        " Your details were updated successfully!!";

    public static final String MESSAGE_UPDATE_DVD_FAILURE =
        " DVD not updated!! Please enter correct values.";

    public static final String MESSAGE_UPDATE_DVD_SUCCESS =
        " DVD update Successfull!!";

    public static final String MESSAGE_USER_NAME_ALREADY_EXISTS =
        " Entered User name already exists. Please enter a different name. ";

    public static final String MESSAGE_USER_SIGNUP_EXCEPTION =
        " Could not Sign Up. Please try again later. ";
}
