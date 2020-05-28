package com.ls.wod.controller;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Country;
import com.ls.wod.domain.Typeuser;
import com.ls.wod.domain.User;
import com.ls.wod.service.CompanyService;
import com.ls.wod.service.CountryService;
import com.ls.wod.service.TypeuserService;
import com.ls.wod.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author francisco
 */

@Slf4j
@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    TypeuserService typeuserService;
    
    @Autowired
    CompanyService companyService;
    
    @Autowired
    CountryService countryService;
    
    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        User user = userService.findByUsername(userLog.getUsername());
        
        List<Typeuser> typeuserList = (List<Typeuser>) typeuserService.findAll();
        List<Company> companyList = (List<Company>) companyService.findAll();
        List<Country> countryList = (List<Country>) countryService.findAll();
        
        model.addAttribute(user);
        model.addAttribute(typeuserList);
        model.addAttribute(companyList);
        model.addAttribute(countryList);
        
        return "layout/profile/index";
    }

    @PostMapping
    public String update(@Valid User user, Errors errors) {
        String response = null;
        if (errors.hasErrors()) {
            log.info(user.toString());
            log.info(errors.toString());
            response = "redirect:/profile";
        } else {
            userService.save(user, false);
            response = "redirect:/profile";
        }
        return response;
    }
}
