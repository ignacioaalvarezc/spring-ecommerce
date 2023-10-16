package com.chi.springecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sale_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private Date creation_date;
    private Date reception_date;
    private double total;

    // RELATIONS
    @ManyToOne
    private User user;
    @OneToOne(mappedBy = "order")
    private OrderDetail orderDetail;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", creationDate=" + creation_date +
                ", receptionDate=" + reception_date +
                '}';
    }
}
