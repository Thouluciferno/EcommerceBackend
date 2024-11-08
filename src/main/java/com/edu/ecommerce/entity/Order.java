package com.edu.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    date

    @Column(name = "order_date")
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP) // or DATE, depending on your needs
    private Date createdAt ;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP) // or DATE, depending on your needs
    private Date updatedAt;

    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP) // or DATE, depending on your needs
    private Date deletedAt;



    @OneToMany
    @JoinColumn(name = "order_id")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
