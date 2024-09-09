package com.umpisa.restoapp.utils;

import com.umpisa.restoapp.models.Customer;
import com.umpisa.restoapp.service.CustomerService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;

@Component
public class NotificationJob {

    CustomerService customerService;

    @Scheduled(cron = "0 */5 * * * *")
    public void sendNotification() {
        Collection<Customer> notifications = customerService.viewAllReservations();
        for (Customer customer : notifications) {
            // Send notification to customer
            ZonedDateTime dateTimeReserve = ZonedDateTime.of(customer.getReservation().getReservationDate(), ZoneId.systemDefault());
            long dateTimeReserveMillis = dateTimeReserve.toInstant().toEpochMilli();
            long currentTimeMillis = ZonedDateTime.now().toInstant().toEpochMilli();
            long reserveTime = dateTimeReserveMillis - currentTimeMillis;
            if (reserveTime <= 14400000) { // 4 hours or less
                // Send notification via sms channel or email
                System.out.println("Good day, You have upcoming reservation!");
            }
        }
    }
}
