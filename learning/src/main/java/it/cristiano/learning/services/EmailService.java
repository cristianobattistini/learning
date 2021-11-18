package it.cristiano.learning.services;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import it.cristiano.learning.utils.email.EmailSender;

@Service
public class EmailService implements Predicate<String>, EmailSender {

    private final JavaMailSender javaMailSender;
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    public EmailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

   
    @Override
    public boolean test(String t) {
        Pattern pattern = Pattern.compile("^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$");
        Matcher matcher= pattern.matcher(t);
        return matcher.find();
    }

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("prova@learning.com");
            javaMailSender.send(message);
        } catch (MessagingException m) {
            LOGGER.error("failed to send email to", m);
            throw new IllegalStateException("Failed to send email");
        }        
    }
    
}
