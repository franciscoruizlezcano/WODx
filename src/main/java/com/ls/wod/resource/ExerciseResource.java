package com.ls.wod.resource;

import com.ls.wod.domain.Exercise;
import com.ls.wod.dto.ExerciseDTO;
import com.ls.wod.service.ExerciseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/exercise")
public class ExerciseResource{

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ExerciseDTO findById(@PathVariable Integer id) {
        return convertToDto(exerciseService.findById(new Exercise(id)));
    }

    @GetMapping
    public List<ExerciseDTO> findAll() {
        List<Exercise> exerciseList = (List<Exercise>) exerciseService.findAll();
        return exerciseList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/count")
    public Map<String, Long> count() {
        Map<String, Long> response = new HashMap<>();
        response.put("count", exerciseService.count());
        return response;
    }

    private ExerciseDTO convertToDto(Exercise exercise) {
        ExerciseDTO exerciseDTO = modelMapper.map(exercise, ExerciseDTO.class);
        return exerciseDTO;
    }
}
