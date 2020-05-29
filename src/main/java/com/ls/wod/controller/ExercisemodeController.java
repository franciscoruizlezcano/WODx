package com.ls.wod.controller;

import com.ls.wod.domain.Exercisemode;
import com.ls.wod.service.ExercisemodeService;
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
@RequestMapping("/exercisemode")
public class ExercisemodeController {
    
    @Autowired
    ExercisemodeService exercisemodeService;
    
    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Exercisemode> exercisemodeList = (List<Exercisemode>) exercisemodeService.findAll();
        
        model.addAttribute(exercisemodeList);
        
        return "layout/exercisemode/index";
    }
    
    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog){
        model.addAttribute("exercisemode", new Exercisemode());
        
        return "layout/exercisemode/create";
    }

    @PostMapping
    public String save(@Valid Exercisemode exercisemode, Model model, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/exercisemode?error";
        } else {
            exercisemodeService.save(exercisemode);
            response = "redirect:/exercisemode?success";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Exercisemode exercisemode = exercisemodeService.findById(new Exercisemode(id));
        model.addAttribute(exercisemode);
        return "layout/exercisemode/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Exercisemode exercisemode = exercisemodeService.findById(new Exercisemode(id));
        model.addAttribute(exercisemode);
        return "layout/exercisemode/edit";
    }

    @PostMapping("/edit")
    public String update( @Valid Exercisemode exercisemode, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/exercisemode?error";
        } else {
            exercisemodeService.save(exercisemode);
            response = "redirect:/exercisemode?success";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        String response;
        try{
            Exercisemode exercisemode = exercisemodeService.findById(new Exercisemode(id));
            exercisemodeService.delete(exercisemode);
            response = "redirect:/exercisemode?success";
        }catch (Exception e){
            log.info(e.toString());
            response = "redirect:/exercisemode?error";
        }
        return response;
    }
}
