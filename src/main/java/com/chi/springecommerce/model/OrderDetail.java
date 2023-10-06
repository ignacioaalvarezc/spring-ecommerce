package com.chi.springecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private Integer id;
    private String name;
    private double amount;
    private double price;
    private double total;

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}


