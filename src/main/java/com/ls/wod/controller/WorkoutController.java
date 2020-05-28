package com.ls.wod.controller;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.Coach;
import com.ls.wod.domain.Exercise;
import com.ls.wod.domain.Exercisemode;
import com.ls.wod.domain.Timeunit;
import com.ls.wod.domain.Traininglevel;
import com.ls.wod.domain.User;
import com.ls.wod.domain.Workout;
import com.ls.wod.service.AthleteService;
import com.ls.wod.service.CoachService;
import com.ls.wod.service.ExerciseService;
import com.ls.wod.service.ExercisemodeService;
import com.ls.wod.service.TimeunitService;
import com.ls.wod.service.TraininglevelService;
import com.ls.wod.service.UserService;
import com.ls.wod.service.WorkoutService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
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
@Controller
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    WorkoutService workoutService;

    @Autowired
    CoachService coachService;
    
    @Autowired
    AthleteService athleteService;
    
    @Autowired
    ExerciseService exerciseService;
    
    @Autowired
    ExercisemodeService exercisemodeService;
    
    @Autowired
    TimeunitService timeunitService;
    
    @Autowired
    TraininglevelService traininglevelService;

    @Autowired
    UserService userService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        User user = userService.findByUsername(userLog.getUsername());
        List<Workout> workoutList;
        boolean company = false;
        if (user.getCompany() != null && user.getCompany().getIdCompany() != null) {
            workoutList = (List<Workout>) workoutService.findByCompany(user.getCompany());
            company = true;
        } else {
            workoutList = (List<Workout>) workoutService.findAll();
        }
        model.addAttribute(company);
        model.addAttribute(workoutList);
        return "layout/workout/index";
    }

    @GetMapping("/create")
    public String create(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        Workout workout = new Workout();
        User user = userService.findByUsername(userLog.getUsername());
        List<Coach> coachList = null;
        Coach coach = new Coach();
        
        //Find all parameters
        List<Exercise> exerciseList = (List<Exercise>) exerciseService.findAll();
        List<Traininglevel> traininglevelList = (List<Traininglevel>) traininglevelService.findByCompany(user.getCompany());
        List<Athlete> athleteList = (List<Athlete>) athleteService.findByCompany(user.getCompany());
        List<Exercisemode> exercisemodeList = (List<Exercisemode>) exercisemodeService.findAll();
        List<Timeunit> timeunitList =  (List<Timeunit>) timeunitService.findAll();
        
        //I verify the type of user logged in, if it is not a ready coach all
        switch (user.getTypeuser().getIdTypeUser()) {
            case 2:
                coachList = (List<Coach>) coachService.findByCompany(user.getCompany());
                break;
            case 3:
                coach = coachService.findByUser(user);
                break;
            default:
                coachList = (List<Coach>) coachService.findAll();
        }

        if (coachList != null) {
            model.addAttribute(coachList);
        } else if (coach != null) {
            model.addAttribute(coach);
        }
        
        //Set coach null for complete
        workout.setCoach(coach);
        
        workout.setDate(new Date());
        
        model.addAttribute(exerciseList);
        model.addAttribute(traininglevelList);
        model.addAttribute(athleteList);
        model.addAttribute(exercisemodeList);
        model.addAttribute(timeunitList);
        model.addAttribute(workout);
        
        return "layout/workout/create";
    }

    @PostMapping
    public String save(@Valid Workout workout, String athleteGroup, String traininglevelGroup, String workoutExerciseList, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/create";
        } else {
            workoutService.save(workout);
            response = "redirect:/workout";
        }
        return response;
    }

    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Integer id) {
        Workout workout = workoutService.findById(new Workout(id));
        model.addAttribute(workout);
        return "layout/workout/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        Workout workout = workoutService.findById(new Workout(id));
        model.addAttribute(workout);
        return "layout/workout/edit";
    }

    @PostMapping("/edit")
    public String update(@Valid Workout workout, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            response = "redirect:/edit/" + workout.getIdWorkout();
        } else {
            workoutService.save(workout);
            response = "redirect:/workout";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Workout workout = workoutService.findById(new Workout(id));
        workoutService.delete(workout);
        return "redirect:/workout";
    }

}
