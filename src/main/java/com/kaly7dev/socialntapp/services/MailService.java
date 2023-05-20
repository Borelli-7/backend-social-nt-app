package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.model.NotificationEmail;

public interface MailService {
    void sendMail(NotificationEmail notificationEmail);
}
