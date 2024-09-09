package com.umpisa.restoapp.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@Table(name = "reservation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, name="reservation_id")
    Long id;

    @Column(nullable = false, name="reservation_date")
    LocalDateTime reservationDate;

    @Column(nullable = false, name="number_of_guests")
    int numberOfGuests;

    @ElementCollection(targetClass=NotificationType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable
    @Column(name = "notification_type", length = 60, nullable = false)
    Collection<NotificationType> notificationType;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Customer customer;
}
