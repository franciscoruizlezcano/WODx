package com.ls.wod.controller;

import com.ls.wod.domain.Exercise;
import com.ls.wod.domain.Typeexercise;
import com.ls.wod.service.ExerciseService;
import com.ls.wod.service.TypeexerciseService;
import com.ls.wod.service.UploadFileService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author francisco
 */
@Slf4j
@Controller
@RequestMapping("/exercise")
public class ExerciseController {

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    TypeexerciseService typeexerciseService;
    
    @Autowired
    UploadFileService uploadFileService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Exercise> exerciseList = (List<Exercise>) exerciseService.findAll();
        model.addAttribute(exerciseList);
        return "layout/exercise/index";
    }

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Typeexercise> typeexerciseList = (List<Typeexercise>) typeexerciseService.findAll();
        model.addAttribute(typeexerciseList);
        model.addAttribute("exercise", new Exercise());
        return "layout/exercise/create";
    }

    @PostMapping
    public String save(@Valid Exercise exercise, @RequestParam("file") MultipartFile file, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/exercise?error";
        } else {
            exercise.setUbication(uploadFileService.save(file));
            exerciseService.save(exercise);
            response = "redirect:/exercise?success";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Exercise exercise = exerciseService.findById(new Exercise(id));
        model.addAttribute(exercise);
        return "layout/exercise/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {

        Exercise exercise = exerciseService.findById(new Exercise(id));
        model.addAttribute(exercise);
        return "layout/exercise/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid Exercise exercise, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/exercise?error";
        } else {
            exerciseService.save(exercise);
            response = "redirect:/exercise?success";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        String response;
        try{
            Exercise exercise = exerciseService.findById(new Exercise(id));
            uploadFileService.delete(exercise.getUbication());
            exerciseService.delete(exercise);
            response = "redirect:/exercise?success";
        }catch (Exception e){
            log.info(e.toString());
            response = "redirect:/exercise?error";
        }
        return response;
    }

}
