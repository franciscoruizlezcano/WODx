package com.ls.wod.service.imp;

import com.ls.wod.service.EmailService;
import com.ls.wod.util.Mail;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author francisco
 */
@Service("EmailService")
public class EmailServiceImpl implements EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public boolean sendSimpleMailMessage(Mail mail) {
        boolean response = false;
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo(mail.getTo());
            simpleMailMessage.setSubject(mail.getSubject());
            simpleMailMessage.setText(mail.getContent());

            javaMailSender.send(simpleMailMessage);

            response = true;
        } catch (MailException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public boolean sendMessageWithAttachment(Mail mail) {
        boolean response = false;
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(mail.getTo());
            helper.setSubject(mail.getSubject());
            helper.setText(mail.getContent());

            FileSystemResource file = new FileSystemResource(new File(mail.getAttachment()));
            helper.addAttachment("Invoice", file);

            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

    @Override
    public boolean sendMessageWithThymeleafTemplate(Mail mail) {
        boolean response = false;
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process(mail.getTemplate(), context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());

            javaMailSender.send(message);
        } catch (MailException e) {
            e.printStackTrace();
        } catch (MessagingException ex) {
            Logger.getLogger(EmailServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }

}
