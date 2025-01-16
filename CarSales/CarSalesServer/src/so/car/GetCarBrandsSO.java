/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.car;

import java.util.List;
import so.AbstractSO;

/**
 *
 * @author user
 */
public class GetCarBrandsSO extends AbstractSO{
    List<String> brands;

    @Override
    protected void validate(Object o) throws Exception {
    }

    @Override
    protected void execute(Object o) throws Exception {
        brands = dbBroker.getAllCarBrands();
    }

    @Override
    protected void commit() {
    }

    @Override
    protected void rollback() {
    }

    public List<String> getBrands() {
        return brands;
    }
}
