// IMPORTS PROGRAM
package com.chi.springecommerce.model;

// IMPORTS JAKARTA PERSISTENCE
import jakarta.persistence.*;


//IMPORTS LOMBOK
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// IMPORTS JAVA.UTIL
import java.util.Arrays;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer amount;
    private Integer price;
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", user=" + user +
                '}';
    }
}
