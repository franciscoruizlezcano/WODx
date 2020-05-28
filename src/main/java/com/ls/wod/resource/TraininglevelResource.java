package com.ls.wod.resource;

import com.ls.wod.domain.Company;
import com.ls.wod.domain.Traininglevel;
import com.ls.wod.exception.UnsupportedParameterException;
import com.ls.wod.resource.util.CrudResource;
import com.ls.wod.service.TraininglevelService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */

@RestController
@RequestMapping("/api/traininglevel")
public class TraininglevelResource implements CrudResource<Traininglevel, Integer>{

    @Autowired
    TraininglevelService service;

    @Override
    @GetMapping("/{id}")
    public Traininglevel findById(@PathVariable Integer id) {
        return service.findById(new Traininglevel(id));
    }

    @Override
    @GetMapping
    public Iterable findAll() {
        return service.findAll();
    }
    
    @GetMapping("/company/{id}")
    public List<Traininglevel> findByCompany(@PathVariable Integer id) {
        return service.findByCompany(new Company(id));
    }

    @Override
    @GetMapping("/count")
    public Map<String, Long> count() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("count", service.count());
        return map;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Traininglevel save(@RequestBody Traininglevel t) {
        return service.save(t);
    }

    @Override
    @PutMapping("/{id}")
    public Traininglevel update(@RequestBody Traininglevel t, @PathVariable Integer id) {
        if (t.getIdTrainingLevel() != id) {
            throw new UnsupportedParameterException();
        }
        service.findById(new Traininglevel(id));
        return service.save(t);
    }

    @Override
    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Integer id) {
        Traininglevel athlete = service.findById(new Traininglevel(id));
        service.delete(athlete);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Entity deleted");
        return map;
    }

}
