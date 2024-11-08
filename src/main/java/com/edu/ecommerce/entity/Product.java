package com.edu.ecommerce.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name", unique = true)
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "image")
    String image;

    @Column(name = "price")
    @Min(value = 0, message = "Price must be greater than 0")
    double price;

    @Column(name = "quantity")
    int quantity;

    @ManyToOne(fetch = FetchType.EAGER) // Or EAGER if you prefer eager loading
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(mappedBy = "product")
    List<Cart> carts;

    @OneToMany(mappedBy = "product")
    List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    List<Review> reviews;


}
