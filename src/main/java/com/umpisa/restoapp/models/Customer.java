package com.umpisa.restoapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name="customer_id")
    Long Id;

    @Column(nullable = false, name="customer_name")
    String name;

    @Column(nullable = false, name="phone_number", unique = true)
    String phoneNumber;

    @Column(nullable = false, name="customer_email", unique = true)
    String email;

    @OneToOne(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    Reservation reservation;

}
