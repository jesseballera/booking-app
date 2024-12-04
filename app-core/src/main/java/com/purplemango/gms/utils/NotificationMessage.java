package com.purplemango.gms.utils;

import org.springframework.stereotype.Component;

@Component
public class NotificationMessage {

    public void sendNotification() {
        System.out.println("Sending SMS notification");
    }
//    public void sendEmailNotification() {
//        System.out.println("Sending Email notification");
//    }
}
