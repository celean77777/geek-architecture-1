package ru.geekbrains.orm;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductMapper {
    private final Connection connection;
    private final PreparedStatement selectProduct;

    private final Map<Long, Product> identityMap = new HashMap<>();

    public ProductMapper(Connection connection) {
        this.connection = connection;
        try {
            this.selectProduct = connection.prepareStatement("select id, productName, cost from products where id = ?;");
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public Optional<Product> findProductById(Long id){
        Product product = identityMap.get(id);
        if (product!=null){
            return Optional.of(product);
        }
        try {
            selectProduct.setLong(1, id);
            ResultSet rs = selectProduct.executeQuery();
            if (rs.next()){
                product = new Product(rs.getLong(1), rs.getString(2), rs.getInt(3));
                identityMap.put(id, product);
                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

        return Optional.empty();

    }

    public void update(Product product){

    }

    public void insert(Product product){

    }

    public void delete(Product product){

    }
}
