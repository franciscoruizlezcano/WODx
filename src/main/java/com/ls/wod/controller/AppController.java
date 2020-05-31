package com.ls.wod.controller;

import com.ls.wod.domain.*;
import com.ls.wod.service.*;
import com.ls.wod.util.Mail;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
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
    AthleteService athleteService;

    @Autowired
    CoachService coachService;

    @Autowired
    TraininglevelService traininglevelService;

    @Autowired
    TaskService taskService;

    @Autowired
    PersonService personService;

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        User user = userService.findByUsername(userLog.getUsername());

        long countAthlete;
        long countCoach;
        long countTraininglevel;
        long countTask;

        if (user.getCompany() != null){
            countAthlete = athleteService.countByCompany(user.getCompany());
            countCoach = coachService.countByCompany(user.getCompany());
            countTraininglevel = traininglevelService.countByCompany(user.getCompany());
        }else{
            countAthlete = athleteService.count();
            countCoach = coachService.count();
            countTraininglevel = traininglevelService.count();
        }

        countTask = taskService.countByUser(user);

        List<Task> taskList = taskService.findByUser(user);

        Task task = new Task();
        task.setUser(user);

        model.addAttribute("countAthlete", countAthlete);
        model.addAttribute("countCoach", countCoach);
        model.addAttribute("countTraininglevel", countTraininglevel);
        model.addAttribute("countTask", countTask);
        model.addAttribute(task);
        model.addAttribute(taskList);
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

    @PostMapping("/task")
    public String save(@Valid Task task, Errors errors) {
        String response;
        if (errors.hasErrors()){
            response = "redirect:/?error";
            log.info(errors.toString());
        }else{
            taskService.save(task);
            response = "redirect:/";
        }
        return response;
    }

    @PostMapping("/task/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        String response;
        try {
            Task task = taskService.findById(new Task(id));
            taskService.delete(task);
            response = "redirect:/?success";
        } catch (Exception e) {
            response = "redirect:/?error";
        }
        return response;
    }
}
