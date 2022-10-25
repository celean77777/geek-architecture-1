package ru.geekbrains.orm;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private final ProductMapper productMapper;

    private final List<Product> newProducts = new ArrayList<>();
    private final List<Product> updateProducts = new ArrayList<>();
    private final List<Product> deleteProducts = new ArrayList<>();

    public UnitOfWork(ProductMapper productMapper) {
        this.productMapper = productMapper;
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
        clear();
    }

    public void insert(){
        this.newProducts.forEach(productMapper::insert);
    }

    public void update(){
        this.updateProducts.forEach(productMapper::update);
    }

    public void delete(){
        this.deleteProducts.forEach(productMapper::delete);
    }

    public void clear(){
        this.deleteProducts.clear();
        this.updateProducts.clear();
        this.newProducts.clear();
    }


}
