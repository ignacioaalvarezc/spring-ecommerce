package com.chi.springecommerce.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private String image;
    private double price;
    private Integer amount;

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
