package ru.geekbrains.orm;

import java.sql.Connection;
import java.util.Optional;

public class ProductRepository {
    private final Connection connection;
    private final ProductMapper productMapper;
    private UnitOfWork unitOfWork;

    public ProductRepository(Connection connection){
        this.connection = connection;
        this.productMapper = new ProductMapper(connection);
        this.unitOfWork = new UnitOfWork(productMapper);
    }

    public Optional<Product> findById(Long id){
        return productMapper.findProductById(id);
    }

    public void beginTransaction(){
        this.unitOfWork = new UnitOfWork(productMapper);
    }

    public void insert(Product product){
        unitOfWork.registerNew(product);
    }

    public void update(Product product){
        unitOfWork.registerUpdate(product);
    }

    public void delete(Product product){
        unitOfWork.registerDelete(product);
    }

    public void commitTransaction(){
        unitOfWork.comit();
    }
}
