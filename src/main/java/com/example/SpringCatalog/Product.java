package com.example.SpringCatalog;

public class Product {
    String id;
    String name;
    String category;
    int price;
    String description;

    public Product(String id, String name, String category, int price, String description) {
        this.id=id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.description=description;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
