package com.chi.springecommerce.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private Integer id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String type;
}