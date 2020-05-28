package com.ls.wod.controller;

import com.ls.wod.domain.Typeworkout;
import com.ls.wod.service.TypeworkoutService;
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
@RequestMapping("/typeworkout")
public class TypeworkoutController {

    @Autowired
    TypeworkoutService typeworkoutService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Typeworkout> typeworkoutList = (List<Typeworkout>) typeworkoutService.findAll();
        model.addAttribute(typeworkoutList);
        return "layout/typeworkout/index";
    }

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        model.addAttribute("typeworkout", new Typeworkout());
        
        return "layout/typeworkout/create";
    }

    @PostMapping
    public String save(@Valid Typeworkout typeworkout, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/create";
        } else {
            typeworkoutService.save(typeworkout);
            response = "redirect:/typeworkout";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Typeworkout typeworkout = typeworkoutService.findById(new Typeworkout(id));
        model.addAttribute(typeworkout);
        return "layout/typeworkout/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Typeworkout typeworkout = typeworkoutService.findById(new Typeworkout(id));
        model.addAttribute(typeworkout);
        return "layout/typeworkout/edit";
    }

    @PostMapping("/edit")
    public String update( @Valid Typeworkout typeworkout, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/edit/" + typeworkout.getIdTypeWorkout();
        } else {
            typeworkoutService.save(typeworkout);
            response = "redirect:/typeworkout";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Typeworkout typeworkout = typeworkoutService.findById(new Typeworkout(id));
        typeworkoutService.delete(typeworkout);
        return "redirect:/typeworkout";
    }
}
