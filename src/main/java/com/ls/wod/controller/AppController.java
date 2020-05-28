package com.ls.wod.controller;

import com.ls.wod.domain.Person;
import com.ls.wod.domain.User;
import com.ls.wod.service.EmailService;
import com.ls.wod.service.PersonService;
import com.ls.wod.service.UserService;
import com.ls.wod.util.Mail;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author francisco
 */
@Slf4j
@Controller
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    PersonService personService;

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/password/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        User user = userService.findById(new User(id));
        model.addAttribute(user);
        return "auth/password";
    }

    @PostMapping("/password/{id}")
    public String update(Model model, @Valid String password, @PathVariable("id") Integer id) {
        User user = userService.findById(new User(id));
        user.setUpdateddate(new Date());
        user.setPassword(password);
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/forgot")
    public String forgot() {
        return "auth/forgot";
    }

    @PostMapping("/forgot")
    public String forgot(@Valid String email, HttpServletRequest request) {
        String response;
        Person person = personService.findByEmail(email);

        if (person != null && person.getIdPerson() != null) {
            User user = userService.findByPerson(person);
            Mail mail = new Mail();

            mail.setTo(person.getEmail());
            mail.setSubject("WOD");

            Map model = new HashMap();

            model.put("name", user.getPerson().getName());
            model.put("lastname", user.getPerson().getLastname());
            model.put("id", user.getIdUser());

            mail.setModel(model);

            mail.setTemplate("mail/forgot");

            emailService.sendMessageWithThymeleafTemplate(mail);
            response = "redirect:/forgot?send";
        }else{
            response = "redirect:/forgot?error";
        }
        return response;

    }
}
