/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package carsalesapplication.domain;

import java.io.Serializable;
import java.sql.*;
import java.util.List;

/**
 *
 * @author user
 */
public interface DefaultDomainObject extends Serializable{
    
    public String getClassName();
    
    public List<DefaultDomainObject> returnList(ResultSet rs) throws SQLException;
    
    public String getSearchCondition();
    
    public String getSearchConditionValue();

    public String getInsertValues();

    public String getInsertColumns();
    
    public String getDeleteCondition();
    
    public String getDeleteConditionValue();

    public String getUpdateValues();

    public String getUpdateCondition();

    public String getUpdateConditionValue();
}
