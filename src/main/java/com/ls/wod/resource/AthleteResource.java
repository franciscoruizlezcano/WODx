package com.ls.wod.resource;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.User;
import com.ls.wod.dto.AthleteDTO;
import com.ls.wod.service.AthleteService;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */

@RestController
@RequestMapping("/api/athlete")
public class AthleteResource{

    @Autowired
    AthleteService athleteService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/{id}")
    public AthleteDTO findById(@PathVariable Integer id) {
        return convertToDto(athleteService.findById(new Athlete(id)));
    }
    
    @GetMapping("/user/{id}")
    public AthleteDTO findByUser(@PathVariable Integer id) {
        return convertToDto(athleteService.findByUser(new User(id)));
    }

    @GetMapping
    public List<AthleteDTO> findAll() {
        List<Athlete> athleteList = (List<Athlete>) athleteService.findAll();
        return athleteList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/count")
    public Map<String, Long> count() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("count", athleteService.count());
        return map;
    }

    @PutMapping
    public AthleteDTO update(@RequestBody AthleteDTO t) {
        Athlete athlete = athleteService.findById(new Athlete(t.getIdAthlete()));
        return convertToDto(athleteService.save(athlete));
    }


    private AthleteDTO convertToDto(Athlete athlete) {
        return modelMapper.map(athlete, AthleteDTO.class);
    }

    private Athlete convertToEntity(AthleteDTO athleteDTO) throws ParseException {
        return modelMapper.map(athleteDTO, Athlete.class);
    }

}
