package com.ls.wod.controller;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.User;
import com.ls.wod.service.CompanyService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author francisco
 */

@Slf4j
@Controller
@RequestMapping("/company")
public class CompanyController {
    
    @Autowired
    CompanyService companyService;
    
    @Autowired
    UserService userService;
    
    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        User user = userService.findByUsername(userLog.getUsername());
        List<Company> companyList = null;
        if(user.getCompany() != null && user.getCompany().getIdCompany() != null){
            companyList.add(user.getCompany());
        }else{
            companyList = (List<Company>) companyService.findAll();
        }
        
        model.addAttribute(companyList);
        return "layout/company/index";
    }
    
    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        model.addAttribute("company", new Company());
        return "layout/company/create";
    }

    @PostMapping
    public String save(@Valid Company company, Model model, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/company?error";
        } else {
            companyService.save(company);
            response = "redirect:/company?success";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Company company = companyService.findById(new Company(id));
        model.addAttribute(company);
        return "layout/company/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Company company = companyService.findById(new Company(id));
        model.addAttribute(company);
        return "layout/company/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid Company company, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/company?error";
        } else {
            companyService.save(company);
            response = "redirect:/company?success";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        String response;
        try {
            Company company = companyService.findById(new Company(id));
            companyService.delete(company);
            response = "redirect:/company?success";
        }catch (Exception e){
            log.info(e.toString());
            response = "redirect:/company?error";
        }
        return response;
    }

}
