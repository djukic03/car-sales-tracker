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
    
    
    GET_ALL,
    ////////////////
    GET_ALL_USERS,
    GET_ALL_CARS,
    GET_ALL_CUSTOMERS,
    GET_ALL_INVOICES,
    /////////////////
    GET_ALL_ORDERED,
    
    
    GET_BY_CONDITION,
    /////////////////////
    SEARCH_USERS,
    SEARCH_CARS,
    SEARCH_CUSTOMERS,
    ////////////////////
    
    
    INSERT_ROW,
    //////////////////////////////////////////
    INSERT_CUSTOMER,
    INSERT_CAR,
    INSERT_INVOICE,
    INSERT_INVOICE_ITEM,
    //////////////////////////////////////////
    INSERT_ROW_AND_GET_ID,
    
    
    DELETE_ROW,
    //////////////////////////////////////////////
    DELETE_CAR,
    DELETE_CUSTOMER,
    ////////////////////////////////////////////////
    
    
    UPDATE_ROW,
    //////////////////////////////////////////////
    UPDATE_CUSTOMER,
    UPDATE_CAR,
    //////////////////////////////////////////////
    
    GET_ALL_CAR_BRANDS,
    CLOSE_CON
}
