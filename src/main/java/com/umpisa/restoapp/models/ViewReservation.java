package com.umpisa.restoapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ViewReservation {

    Long id;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm")
    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm")
    LocalDateTime reservationDate;

    int numberOfGuests;

//    Collection<NotificationType> notificationType;
}
