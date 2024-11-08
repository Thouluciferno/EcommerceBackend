package com.edu.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "OrderDetailResponseOrder")
public class OrderDetail {

    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;

    @Column(name = "quantity")
    @Min(value = 1, message = "Quantity must be greater than 0")
    int quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;
}
