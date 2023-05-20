package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.exceptions.SocialNtException;
import com.kaly7dev.socialntapp.model.NotificationEmail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {
    private final JavaMailSender mailSender;
    @Override
    @Async
    public void sendMail(NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator= mimeMessage -> {
            MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("socialnotwork@email.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(notificationEmail.getBody());
        };
        try {
            mailSender.send(messagePreparator);
            log.info("Activation Email Sent !");
        } catch (MailException e) {
            log.error("Exception occured when sending mail", e);
            throw new SocialNtException("Exception occured when sending mail to "+ notificationEmail.getRecipient(), e);
        }
    }
}
