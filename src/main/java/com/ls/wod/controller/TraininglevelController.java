package com.ls.wod.controller;

import com.ls.wod.domain.Traininglevel;
import com.ls.wod.domain.User;
import com.ls.wod.service.TraininglevelService;
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
@RequestMapping("/traininglevel")
public class TraininglevelController {

    @Autowired
    TraininglevelService traininglevelService;

    @Autowired
    UserService userService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        String response = "redirect:/403";
        User user = userService.findByUsername(userLog.getUsername());

        if (user.getCompany() != null) {
            List<Traininglevel> traininglevelList = (List<Traininglevel>) traininglevelService.findAll();
            model.addAttribute(traininglevelList);
            response = "layout/traininglevel/index";
        }

        return response;
    }

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        String response = "redirect:/403";
        User user = userService.findByUsername(userLog.getUsername());

        if (user.getCompany() != null) {
            Traininglevel traininglevel = new Traininglevel();
            traininglevel.setCompany(user.getCompany());
            
            model.addAttribute(traininglevel);
            
            response = "layout/traininglevel/create";
        }
        return response;
    }

    @PostMapping
    public String save(@Valid Traininglevel traininglevel, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/create";
        } else {
            traininglevelService.save(traininglevel);
            response = "redirect:/traininglevel";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Traininglevel traininglevel = traininglevelService.findById(new Traininglevel(id));
        model.addAttribute(traininglevel);
        return "layout/traininglevel/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Traininglevel traininglevel = traininglevelService.findById(new Traininglevel(id));
        model.addAttribute(traininglevel);
        return "layout/traininglevel/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid Traininglevel traininglevel, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/edit/" + traininglevel.getIdTrainingLevel();
        } else {
            traininglevelService.save(traininglevel);
            response = "redirect:/traininglevel";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Traininglevel traininglevel = traininglevelService.findById(new Traininglevel(id));
        traininglevelService.delete(traininglevel);
        return "redirect:/traininglevel";
    }

}
