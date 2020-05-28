package com.ls.wod.util;

import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 *
 * @author francisco
 */

@Data
public class Mail {
    
    private String to;
    private String subject;
    private String content;
    private String attachment;
    private String template;
    private Map<String, Object> model;
    
    
    public Mail() {
    }

    public Mail(String to, String subject, String content) {
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    public Mail(String to, String subject, String content, String attachment) {
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.attachment = attachment;
    }

    public Mail(String to, String subject, String content, String attachment, String template, Map<String, Object> model) {
        this.to = to;
        this.subject = subject;
        this.content = content;
        this.attachment = attachment;
        this.template = template;
        this.model = model;
    }

    
}
