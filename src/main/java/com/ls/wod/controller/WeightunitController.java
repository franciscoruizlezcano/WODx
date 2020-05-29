package com.ls.wod.controller;

import com.ls.wod.domain.Weightunit;
import com.ls.wod.service.WeightunitService;
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
@RequestMapping("/weightunit")
public class WeightunitController {
    
    @Autowired
    WeightunitService weightunitService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Weightunit> weightunitList = (List<Weightunit>) weightunitService.findAll();
        model.addAttribute(weightunitList);
        return "layout/weightunit/index";
    }
    
    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        model.addAttribute("weightunit", new Weightunit());
        
        return "layout/weightunit/create";
    }

    @PostMapping
    public String save(@Valid Weightunit weightunit, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/weightunit?error";
        } else {
            weightunitService.save(weightunit);
            response = "redirect:/weightunit?success";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Short id) {
        Weightunit weightunit = weightunitService.findById(new Weightunit(id));
        model.addAttribute(weightunit);
        return "layout/weightunit/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Short id) {
        Weightunit weightunit = weightunitService.findById(new Weightunit(id));
        model.addAttribute(weightunit);
        return "layout/weightunit/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid Weightunit weightunit, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/weightunit?error";
        } else {
            weightunitService.save(weightunit);
            response = "redirect:/weightunit?success";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Short id) {
        String response;
        try{
            Weightunit weightunit = weightunitService.findById(new Weightunit(id));
            weightunitService.delete(weightunit);
            response = "redirect:/weightunit?success";
        }catch (Exception e) {
            log.info(e.toString());
            response = "redirect:/weightunit?error";
        }
        return response;
    }

}
