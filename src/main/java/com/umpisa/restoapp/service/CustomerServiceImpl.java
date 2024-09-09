package com.umpisa.restoapp.service;

import com.umpisa.restoapp.exceptions.ReservationNotFoundException;
import com.umpisa.restoapp.models.AddReservation;
import com.umpisa.restoapp.models.Customer;
import com.umpisa.restoapp.models.Reservation;
import com.umpisa.restoapp.models.UpdateReservation;
import com.umpisa.restoapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addReservation(AddReservation entity) {

        Customer customer = Customer.builder()
                .name(entity.getName())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .build();

        Reservation reservation = Reservation.builder()
                .reservationDate(entity.getReservationDate())
                .numberOfGuests(entity.getNumberOfGuests())
                .notificationType(entity.getNotificationType())
                .build();

        customer.setReservation(reservation);
        reservation.setCustomer(customer);

        customerRepository.save(customer);

    }

    @Override
    public void updateReservation(UpdateReservation reservation, Long reservationId) {
        Customer entity = viewCustomerById(reservationId);
        if (entity != null) {
            Reservation updateReservation = Reservation.builder()
                    .id(reservationId)
                    .reservationDate(reservation.getReservationDate())
                    .numberOfGuests(reservation.getNumberOfGuests())
                    .build();

            entity.setReservation(updateReservation);
            updateReservation.setCustomer(entity);
            customerRepository.save(entity);
        }
    }

    @Override
    public void cancelReservation(Long reservationId) {
        viewCustomerById(reservationId);
        customerRepository.deleteById(reservationId);
    }

    @Override
    public Customer viewCustomerById(Long customerId) {
        return customerRepository.findById(customerId).orElseThrow(() -> new ReservationNotFoundException("Reservation not found for reservationId: " + customerId));
    }

    @Override
    public Collection<Customer> viewAllReservations() {
        return customerRepository.findAll();
    }
}
