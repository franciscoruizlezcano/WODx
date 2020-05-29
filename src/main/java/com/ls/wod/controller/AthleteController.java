package com.ls.wod.controller;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.Country;
import com.ls.wod.domain.Traininglevel;
import com.ls.wod.domain.Typeuser;
import com.ls.wod.domain.User;
import com.ls.wod.service.AthleteService;
import com.ls.wod.service.CountryService;
import com.ls.wod.service.EmailService;
import com.ls.wod.service.TraininglevelService;
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
@RequestMapping("/athlete")
public class AthleteController {

    @Autowired
    AthleteService athleteService;

    @Autowired
    TraininglevelService traininglevelService;

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
        List<Athlete> athleteList;
        if (user.getCompany() != null && user.getCompany().getIdCompany() != null) {
            athleteList = (List<Athlete>) athleteService.findByCompany(user.getCompany());
        } else {
            athleteList = (List<Athlete>) athleteService.findAll();
        }
        model.addAttribute(athleteList);
        return "layout/athlete/index";
    }

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        User user = userService.findByUsername(userLog.getUsername());
        List<Country> countryList = (List<Country>) countryService.findAll();
        
        List<Traininglevel> traininglevelList = (List<Traininglevel>) traininglevelService.findByCompany(user.getCompany());
        traininglevelList.add(0, new Traininglevel());
        
        Athlete athlete = new Athlete();
        
        athlete.setUser(new User());
        athlete.getUser().setCreationdate(new Date());
        athlete.getUser().setCompany(user.getCompany());
        athlete.getUser().setStatus(true);
        athlete.getUser().setTypeuser(typeuserService.findById(new Typeuser(4)));

        model.addAttribute(traininglevelList);
        model.addAttribute(countryList);
        model.addAttribute(athlete);

        return "layout/athlete/create";
    }

    @PostMapping
    public String save(@Valid Athlete athlete, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/athlete?error";
        } else {
            athlete = athleteService.save(athlete, false);
            
            Mail mail = new Mail();
            Map model = new HashMap();
            
            mail.setTo(athlete.getUser().getPerson().getEmail());
            mail.setSubject("WOD");
            mail.setTemplate("mail/welcome");
            
            model.put("name", athlete.getUser().getPerson().getName());
            model.put("lastname", athlete.getUser().getPerson().getLastname());
            model.put("id", athlete.getUser().getIdUser());
            
            mail.setModel(model);
            
            emailService.sendMessageWithThymeleafTemplate(mail);
            
            response = "redirect:/athlete?success";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Athlete athlete = athleteService.findById(new Athlete(id));
        model.addAttribute(athlete);
        return "layout/athlete/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Athlete athlete = athleteService.findById(new Athlete(id));
        model.addAttribute(athlete);
        return "layout/athlete/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid Athlete athlete, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/athlete?error";
        } else {
            athleteService.save(athlete, false);
            response = "redirect:/athlete?success";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        String response;
        try {
            Athlete athlete = athleteService.findById(new Athlete(id));
            athleteService.delete(athlete);
            response = "redirect:/athlete?success";
        } catch (Exception e) {
            response = "redirect:/athlete?error";
        }
        return response;
    }
}
