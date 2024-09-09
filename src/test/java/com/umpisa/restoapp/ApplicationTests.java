package com.umpisa.restoapp;

import com.umpisa.restoapp.models.Customer;
import com.umpisa.restoapp.models.NotificationType;
import com.umpisa.restoapp.models.Reservation;
import com.umpisa.restoapp.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
//@SpringBootTest
@DataJpaTest
class ApplicationTests {

	    @Autowired
		CustomerRepository customerRepository;


    @Test
    public void testGetAllCustomers() throws Exception {
        testAddReservation();
        List<Customer> customers = customerRepository.findAll(); //<1>
        assertNotNull(customers);
    }
    @Test
    public void testGetCustomerById() throws Exception {
        testAddReservation();
        Customer customer = customerRepository.findById(1L).get(); //<1>
        assertNotNull(customer);
    }

    @Test
    public void testAddReservation() throws Exception {

        Customer customer = Customer.builder()
                .name("John Doe")
                .phoneNumber("091234567")
                .email("user101@gmail.com")
                .build();

        Reservation reservation = Reservation.builder()
                .reservationDate(LocalDateTime.now())
                .numberOfGuests(100)
                .notificationType(Arrays.asList(NotificationType.EMAIL, NotificationType.SMS))
                .build();

        customer.setReservation(reservation);
        reservation.setCustomer(customer);

        customerRepository.save(customer);
        log.info("Reservation created successfully!. Please check your email/sms for confirmation");
        assertNotNull(customer);
    }

    @Test
    public void testDeleteReservation() throws Exception {
        testAddReservation();
        customerRepository.deleteById(1L);
        List<Customer> customers = customerRepository.findAll(); //<1>
        log.info("Reservation deleted successfully!. Please check your email/sms for confirmation");
        assertNotNull(customers);
    }

    @Test
    public void testUpdateReservation() throws Exception {
        testAddReservation();
        Customer customer = customerRepository.findById(1L).get(); //<1>

        Reservation reservation = Reservation.builder()
                .reservationDate(LocalDateTime.now())
                .numberOfGuests(100)
//                .notificationType(Arrays.asList(NotificationType.EMAIL, NotificationType.SMS))
                .build();

        customer.setReservation(reservation);
        reservation.setCustomer(customer);
        customerRepository.save(customer);
        log.info("Reservation updated successfully!. Please check your email/sms for confirmation");
        assertNotNull(customer);
    }

}
