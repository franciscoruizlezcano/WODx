package com.ls.wod.controller;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Country;
import com.ls.wod.domain.Typeuser;
import com.ls.wod.domain.User;
import com.ls.wod.service.CompanyService;
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
@RequestMapping("/company/user")
public class CompanyUserController {

    @Autowired
    TypeuserService typeuserService;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CountryService countryService;

    @Autowired
    EmailService emailService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        User user = userService.findByUsername(userLog.getUsername());
        List<User> userList = null;
        if (user.getCompany() != null && user.getCompany().getIdCompany() != null) {
            userList = (List<User>) userService.findByCompanyAndTypeUser(user.getCompany(), user.getTypeuser());
        } else {
            userList = (List<User>) userService.findByTypeUser(typeuserService.findById(new Typeuser(2)));
        }
        model.addAttribute(userList);
        return "layout/company_user/index";
    }

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Country> countryList = (List<Country>) countryService.findAll();
        List<Company> companyList = (List<Company>) companyService.findAll();

        User user = new User();

        user.setCreationdate(new Date());
        user.setCompany(null);
        user.setStatus(true);
        user.setTypeuser(typeuserService.findById(new Typeuser(2)));

        model.addAttribute(countryList);
        model.addAttribute(companyList);
        model.addAttribute(user);

        return "layout/company_user/create";
    }

    @PostMapping
    public String save(@Valid User user, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/company/user?error";
        } else {
            userService.save(user, false);

            Mail mail = new Mail();
            Map model = new HashMap();

            mail.setTo(user.getPerson().getEmail());
            mail.setSubject("WOD");
            mail.setTemplate("mail/welcome");

            model.put("name", user.getPerson().getName());
            model.put("lastname", user.getPerson().getLastname());
            model.put("id", user.getIdUser());

            mail.setModel(model);

            emailService.sendMessageWithThymeleafTemplate(mail);

            response = "redirect:/company/user";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        User user = userService.findById(new User(id));
        model.addAttribute(user);
        return "layout/company_user/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        List<Country> countryList = (List<Country>) countryService.findAll();
        List<Company> companyList = (List<Company>) companyService.findAll();

        User user = userService.findById(new User(id));

        user.setUpdateddate(new Date());

        model.addAttribute(user);
        model.addAttribute(countryList);
        model.addAttribute(companyList);

        return "layout/company_user/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid User user, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/company/user?error";
        } else {
            userService.save(user, false);
            response = "redirect:/company/user";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        String response = "redirect:/company/user";
        User user = userService.findById(new User(id));

        if (!user.getUsername().equals(userLog.getUsername())) {
            userService.delete(user);
        } else {
            response = "redirect:/company/user?error";
        }

        return response;
    }

}
