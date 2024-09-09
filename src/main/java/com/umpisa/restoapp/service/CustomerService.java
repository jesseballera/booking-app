package com.umpisa.restoapp.service;


import com.umpisa.restoapp.models.AddReservation;
import com.umpisa.restoapp.models.Customer;
import com.umpisa.restoapp.models.UpdateReservation;

import java.util.Collection;

public interface CustomerService {

    void addReservation(AddReservation reservation);
    void updateReservation(UpdateReservation reservation, Long reservationId);
    void cancelReservation(Long reservationId);
    Customer viewCustomerById(Long customerId);
    Collection<Customer> viewAllReservations();
}
