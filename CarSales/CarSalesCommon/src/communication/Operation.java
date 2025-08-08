/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package communication;

/**
 *
 * @author user
 */
public enum Operation {
    LOGIN,
    LOG_OUT,
    
    GET_ALL_USERS,
    GET_ALL_CARS,
    GET_ALL_CUSTOMERS,
    GET_ALL_INVOICES,
    GET_ALL_RESERVATIONS, 
    
    SEARCH_USERS,
    SEARCH_CARS,
    SEARCH_CUSTOMERS,
    SEARCH_INVOICES,
    
    INSERT_USER,
    INSERT_CUSTOMER,
    INSERT_CAR,
    INSERT_INVOICE,
    INSERT_RESERVATION,
    
    DELETE_CAR,
    DELETE_CUSTOMER,
    
    UPDATE_CUSTOMER,
    UPDATE_CAR,
    UPDATE_USER,
    UPDATE_RESERVATION,
    
    GET_ALL_CAR_BRANDS,
    CLOSE_CON,  
}
