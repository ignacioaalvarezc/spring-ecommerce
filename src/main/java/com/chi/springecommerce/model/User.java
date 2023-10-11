package com.chi.springecommerce.model;

import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String last_name;
    private String address;
    private String phone_number;
    private String type;

    @OneToMany(mappedBy = "user")
    private List<Product> products;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    public User(int id, String username, String email,
                String password, String name, String last_name,
                String address, String phone_number, String type) {
    }
}
