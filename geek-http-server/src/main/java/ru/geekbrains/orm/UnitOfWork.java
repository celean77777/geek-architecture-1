package ru.geekbrains.orm;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private final Connection connection;

    private final List<Product> newProducts = new ArrayList<>();
    private final List<Product> updateProducts = new ArrayList<>();
    private final List<Product> deleteProducts = new ArrayList<>();

    public UnitOfWork(Connection connection) {
        this.connection = connection;
    }

    public void registerNew(Product product){
        this.newProducts.add(product);
    }

    public void registerUpdate(Product product){
        this.updateProducts.add(product);
    }

    public void registerDelete(Product product){
        this.deleteProducts.add(product);
    }

    public void comit(){
        insert();
        update();
        delete();
    }

    public void insert(){

    }

    public void update(){

    }

    public void delete(){

    }


}
