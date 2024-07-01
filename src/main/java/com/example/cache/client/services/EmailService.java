package com.example.cache.client.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Slf4j
@Component
public class EmailService {

    public static void main(String[] args) throws MessagingException {
        sendEmail("mahagirinda@gmail.com", "test", "test isi");
    }

    public static String sendEmail(String to, String subject, String body) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        log.info("Email Properties : {}", props);
        try {
            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("kpei.notification@gmail.com", "hbupbpjjcntysiaa");
                }
            });

            log.info("Email Session : {}", session.toString());
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("kpei.notification@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            log.info("Message : {}", message.toString());
            Transport.send(message);
        } catch (MessagingException e) {
            return "failed " + e.getMessage();
        }

        return "Success";
    }
}