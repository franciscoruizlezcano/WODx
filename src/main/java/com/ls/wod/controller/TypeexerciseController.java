package com.ls.wod.controller;

import com.ls.wod.domain.Typeexercise;
import com.ls.wod.service.TypeexerciseService;
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
@RequestMapping("/typeexercise")
public class TypeexerciseController {

    @Autowired
    TypeexerciseService typeexerciseService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Typeexercise> typeexerciseList = (List<Typeexercise>) typeexerciseService.findAll();
        model.addAttribute(typeexerciseList);
        return "layout/typeexercise/index";
    }

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog){
        model.addAttribute("typeexercise", new Typeexercise());
        
        return "layout/typeexercise/create";
    }

    @PostMapping
    public String save(@Valid Typeexercise typeexercise, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/create";
        } else {
            typeexerciseService.save(typeexercise);
            response = "redirect:/typeexercise";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Typeexercise typeexercise = typeexerciseService.findById(new Typeexercise(id));
        model.addAttribute(typeexercise);
        return "layout/typeexercise/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Typeexercise typeexercise = typeexerciseService.findById(new Typeexercise(id));
        model.addAttribute(typeexercise);
        return "layout/typeexercise/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid Typeexercise typeexercise, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/edit/" + typeexercise.getIdTypeExercise();
        } else {
            typeexerciseService.save(typeexercise);
            response = "redirect:/typeexercise";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Typeexercise typeexercise = typeexerciseService.findById(new Typeexercise(id));
        typeexerciseService.delete(typeexercise);
        return "redirect:/typeexercise";
    }

}
