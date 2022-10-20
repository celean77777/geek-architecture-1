package ru.geekbrains.orm;

public class Product {
    private Long id;
    private String name;
    private int cost;


    public Product(Long id, String name, int coast) {
        this.id = id;
        this.name = name;
        this.cost = coast;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
