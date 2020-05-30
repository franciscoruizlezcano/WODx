package com.ls.wod.resource;

import com.ls.wod.domain.Traininglevel;
import com.ls.wod.dto.TraininglevelDTO;
import com.ls.wod.service.TraininglevelService;
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
@RequestMapping("/api/traininglevel")
public class TrainiglevelResource {

    @Autowired
    private TraininglevelService traininglevelService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public TraininglevelDTO findById(@PathVariable Integer id) {
        return convertToDto(traininglevelService.findById(new Traininglevel(id)));
    }

    @GetMapping
    public List<TraininglevelDTO> findAll() {
        List<Traininglevel> traininglevelList = (List<Traininglevel>) traininglevelService.findAll();
        return traininglevelList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/count")
    public Map<String, Long> count() {
        Map<String, Long> response = new HashMap<>();
        response.put("count", traininglevelService.count());
        return response;
    }

    private TraininglevelDTO convertToDto(Traininglevel traininglevel) {
        TraininglevelDTO traininglevelDTO = modelMapper.map(traininglevel, TraininglevelDTO.class);
        return traininglevelDTO;
    }
}
