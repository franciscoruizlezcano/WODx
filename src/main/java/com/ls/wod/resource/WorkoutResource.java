package com.ls.wod.resource;

import com.ls.wod.domain.*;
import com.ls.wod.dto.WorkoutDTO;
import com.ls.wod.service.WorkoutAthleteService;
import com.ls.wod.service.WorkoutService;
import com.ls.wod.service.WorkoutTraininglevelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/workout")
public class WorkoutResource {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    private WorkoutAthleteService workoutAthleteService;

    @Autowired
    private WorkoutTraininglevelService workoutTraininglevelService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/{id}")
    public WorkoutDTO findById(@PathVariable Integer id) {
        return convertToDto(workoutService.findById(new Workout(id)));
    }

    @GetMapping
    public List<WorkoutDTO> findAll() {
        List<Workout> workoutList = (List<Workout>) workoutService.findAll();
        return workoutList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/athlete/{id}")
    public List<WorkoutDTO> findByAthlete(@PathVariable Integer id) {
        List<WorkoutAthlete> workoutAthleteList = (List<WorkoutAthlete>) workoutAthleteService.findByAthlete(new Athlete(id));
        List<Workout> workoutList = new ArrayList<>();
        for (WorkoutAthlete workoutAthlete: workoutAthleteList){
            workoutList.add(workoutAthlete.getWorkout());
        }
        return workoutList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/athlete/day/{id}")
    public List<WorkoutDTO> findByAthleteAndDay(@PathVariable Integer id) {
        List<WorkoutAthlete> workoutAthleteList = (List<WorkoutAthlete>) workoutAthleteService.findByAthleteAndDay(new Athlete(id));
        List<Workout> workoutList = new ArrayList<>();
        for (WorkoutAthlete workoutAthlete: workoutAthleteList){
            workoutList.add(workoutAthlete.getWorkout());
        }
        return workoutList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/traininglevel/{id}")
    public List<WorkoutDTO> findByTraininglevel(@PathVariable Integer id) {
        List<WorkoutTraininglevel> workoutTraininglevelList = (List<WorkoutTraininglevel>) workoutTraininglevelService.findByTraininglevel(new Traininglevel(id));
        List<Workout> workoutList = new ArrayList<>();
        for (WorkoutTraininglevel workoutTraininglevel: workoutTraininglevelList){
            workoutList.add(workoutTraininglevel.getWorkout());
        }
        return workoutList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/traininglevel/day/{id}")
    public List<WorkoutDTO> findByTraininglevelAndDay(@PathVariable Integer id) {
        List<WorkoutTraininglevel> workoutTraininglevelList = (List<WorkoutTraininglevel>) workoutTraininglevelService.findByTraininglevelAndDay(new Traininglevel(id));
        List<Workout> workoutList = new ArrayList<>();
        for (WorkoutTraininglevel workoutTraininglevel: workoutTraininglevelList){
            workoutList.add(workoutTraininglevel.getWorkout());
        }
        return workoutList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/count")
    public Map<String, Long> count() {
        Map<String, Long> response = new HashMap<>();
        response.put("count", workoutService.count());
        return response;
    }

    private WorkoutDTO convertToDto(Workout workout) {
        WorkoutDTO workoutDTO = modelMapper.map(workout, WorkoutDTO.class);
        return workoutDTO;
    }
}
