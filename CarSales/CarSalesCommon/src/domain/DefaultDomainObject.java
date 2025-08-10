/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 *
 * @author user
 */
public abstract class DefaultDomainObject implements Serializable{
    protected String searchCondition;
    protected String searchConditionValue;
    
    public abstract List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException;

    public abstract String getGetAllQuery();

    public abstract String getGetAllOrderedQuery();

    public abstract String getGetByConditionQuery();

    public abstract String getInsertQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    public void setSearchConditionValue(String searchConditionValue) {
        this.searchConditionValue = searchConditionValue;
    }
    
}
