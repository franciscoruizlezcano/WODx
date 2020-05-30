package com.ls.wod.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ls.wod.domain.*;
import com.ls.wod.service.*;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
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
        workout.setDate(new Date());
        workout.setEndingdate(new Date());
        workout.setCoach(coach);
        
        model.addAttribute(exerciseList);
        model.addAttribute(traininglevelList);
        model.addAttribute(athleteList);
        model.addAttribute(exercisemodeList);
        model.addAttribute(timeunitList);
        model.addAttribute(workout);
        
        return "layout/workout/create";
    }

    @PostMapping
    public String save(@Valid Workout workout,  String athleteJson, String traininglevelJson,  String workoutExerciseJson, Errors errors) {
        String response;
        if (errors.hasErrors()) {
            log.info(errors.toString());
            response = "redirect:/workout?error";
        } else {
            workoutService.save(workout);

            Gson gson = new Gson();

            workout.setWorkoutAthleteList(new ArrayList<WorkoutAthlete>());
            workout.setWorkoutTraininglevelList(new ArrayList<WorkoutTraininglevel>());
            workout.setWorkoutExerciseList(new ArrayList<WorkoutExercise>());

            Type workoutExerciseListType = new TypeToken<ArrayList<WorkoutExercise>>(){}.getType();
            ArrayList<WorkoutExercise> workoutExerciseList = gson.fromJson(workoutExerciseJson, workoutExerciseListType);

            for (WorkoutExercise workoutexercise: workoutExerciseList){
                if (workoutexercise.getTimeunit().getIdTimeUnit() == 0){
                    workoutexercise.setTimeunit(null);
                }
                workoutexercise.setWorkout(workout);
                workout.getWorkoutExerciseList().add(workoutexercise);
            }

            Type athleteListType = new TypeToken<ArrayList<Athlete>>(){}.getType();
            ArrayList<Athlete> athleteList = gson.fromJson(athleteJson, athleteListType);

            for (Athlete athlete: athleteList){
                WorkoutAthlete workoutAthlete = new WorkoutAthlete();

                workoutAthlete.setAthlete(athlete);
                workoutAthlete.setWorkout(workout);

                workout.getWorkoutAthleteList().add(workoutAthlete);
            }

            Type traininglevelListType = new TypeToken<ArrayList<Traininglevel>>(){}.getType();
            ArrayList<Traininglevel> traininglevelList = gson.fromJson(traininglevelJson, traininglevelListType);

            for (Traininglevel traininglevel: traininglevelList) {
                WorkoutTraininglevel workoutTraininglevel = new WorkoutTraininglevel();

                workoutTraininglevel.setTraininglevel(traininglevel);
                workoutTraininglevel.setWorkout(workout);

                workout.getWorkoutTraininglevelList().add(workoutTraininglevel);
            }

            workoutService.save(workout);

            response = "redirect:/workout?success";
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
            response = "redirect:/workout?error";
        } else {
            workoutService.save(workout);
            response = "redirect:/workout?success";
        }
        return response;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        String response;
        try{
            Workout workout = workoutService.findById(new Workout(id));
            workoutService.delete(workout);
            response = "redirect:/workout?success";
        }catch (Exception e){
            log.info(e.toString());
            response = "redirect:/workout?error";
        }
        return response;
    }

}
