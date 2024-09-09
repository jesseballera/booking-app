package com.umpisa.restoapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ViewCustomer {

    @JsonProperty("customerId")
    Long id;

    @JsonProperty("customerName")
    String name;

    @JsonProperty("mobileNumber")
    String phoneNumber;

    String email;

    ViewReservation reservation;

}
