package com.purplemango.gms.models.reservation;

import com.purplemango.gms.models.core.enums.NotificationType;
import com.purplemango.gms.models.clientdata.Customer;
import lombok.*;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;

@Builder
@Document(collection = "reservations")
public record Reservation(
        Long id,
        LocalDateTime reservationDate,
        int numberOfGuests,
        Collection<NotificationType> notificationType,
        Customer customer) { }
