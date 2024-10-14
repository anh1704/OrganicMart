package vn.hoidanit.laptopshop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column(name = "description", columnDefinition = "VARCHAR(1000)")
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String imagePath;


}
