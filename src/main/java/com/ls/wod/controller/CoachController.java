package com.ls.wod.controller;

import com.ls.wod.domain.Coach;
import com.ls.wod.domain.Country;
import com.ls.wod.domain.Typeuser;
import com.ls.wod.domain.User;
import com.ls.wod.service.CoachService;
import com.ls.wod.service.CountryService;
import com.ls.wod.service.EmailService;
import com.ls.wod.service.TypeuserService;
import com.ls.wod.service.UserService;
import com.ls.wod.util.Mail;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author francisco
 */
@Slf4j
@Controller
@RequestMapping("/coach")
public class CoachController {

    @Autowired
    CoachService coachService;

    @Autowired
    UserService userService;
    
    @Autowired
    EmailService emailService;

    @Autowired
    TypeuserService typeuserService;

    @Autowired
    CountryService countryService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        User user = userService.findByUsername(userLog.getUsername());
        List<Coach> coachList;
        boolean company = false;
        if (user.getCompany() != null && user.getCompany().getIdCompany() != null) {
            coachList = (List<Coach>) coachService.findByCompany(user.getCompany());
            company = true;
        } else {
            coachList = (List<Coach>) coachService.findAll();
        }
        model.addAttribute(company);
        model.addAttribute(coachList);
        return "layout/coach/index";
    }

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Country> countryList = (List<Country>) countryService.findAll();
        User user = userService.findByUsername(userLog.getUsername());
        Coach coach = new Coach();
        
        coach.setUser(new User());
        coach.getUser().setCreationdate(new Date());
        coach.getUser().setCompany(user.getCompany());
        coach.getUser().setStatus(true);
        coach.getUser().setTypeuser(typeuserService.findById(new Typeuser(3)));

        model.addAttribute(countryList);
        model.addAttribute(coach);

        return "layout/coach/create";
    }

    @PostMapping
    public String save(@Valid Coach coach, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/create";
        } else {
            coachService.save(coach, false);
            
            Mail mail = new Mail();
            Map model = new HashMap();
            
            mail.setTo(coach.getUser().getPerson().getEmail());
            mail.setSubject("WOD");
            mail.setTemplate("mail/welcome");
            
            model.put("name", coach.getUser().getPerson().getName());
            model.put("lastname", coach.getUser().getPerson().getLastname());
            model.put("id", coach.getUser().getIdUser());
            
            mail.setModel(model);
            
            emailService.sendMessageWithThymeleafTemplate(mail);
            
            
            response = "redirect:/coach";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Coach coach = coachService.findById(new Coach(id));
        model.addAttribute(coach);
        return "layout/coach/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Coach coach = coachService.findById(new Coach(id));
        model.addAttribute(coach);
        return "layout/coach/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid Coach coach, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/edit/" + coach.getIdCoach();
        } else {
            coachService.save(coach, false);
            response = "redirect:/coach";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        String response = "redirect:/coach";
        try {
            Coach coach = coachService.findById(new Coach(id));
            coachService.delete(coach);
        } catch (Exception e) {
            response = "redirect:/coach?error";
        }

        return response;
    }

}
