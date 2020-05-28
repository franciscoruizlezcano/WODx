package com.ls.wod.controller;

import com.ls.wod.domain.Typeuser;
import com.ls.wod.service.TypeuserService;
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
@RequestMapping("/typeuser")
public class TypeuserController {

    @Autowired
    TypeuserService typeuserService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Typeuser> typeuserList = (List<Typeuser>) typeuserService.findAll();
        model.addAttribute(typeuserList);
        return "layout/typeuser/index";
    }

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog){
        model.addAttribute("typeuser", new Typeuser());
        
        return "layout/typeuser/create";
    }

    @PostMapping
    public String save(@Valid Typeuser typeuser, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/create";
        } else {
            typeuserService.save(typeuser);
            response = "redirect:/typeuser";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Typeuser typeuser = typeuserService.findById(new Typeuser(id));
        model.addAttribute(typeuser);
        return "layout/typeuser/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Typeuser typeuser = typeuserService.findById(new Typeuser(id));
        model.addAttribute(typeuser);
        return "layout/typeuser/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid Typeuser typeuser, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/edit/" + typeuser.getIdTypeUser();
        } else {
            typeuserService.save(typeuser);
            response = "redirect:/typeuser";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Typeuser typeuser = typeuserService.findById(new Typeuser(id));
        typeuserService.delete(typeuser);
        return "redirect:/typeuser";
    }

}
