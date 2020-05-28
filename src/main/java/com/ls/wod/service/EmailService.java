package com.ls.wod.service;

import com.ls.wod.util.Mail;

/**
 *
 * @author francisco
 */

public interface EmailService {
    boolean sendSimpleMailMessage(Mail mail);
    
    boolean sendMessageWithAttachment(Mail mail);
    
    boolean sendMessageWithThymeleafTemplate(Mail mail);
}
