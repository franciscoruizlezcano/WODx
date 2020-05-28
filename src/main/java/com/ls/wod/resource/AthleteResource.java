package com.ls.wod.resource;

import com.ls.wod.domain.Athlete;
import com.ls.wod.domain.User;
import com.ls.wod.exception.UnsupportedParameterException;
import com.ls.wod.resource.util.CrudResource;
import com.ls.wod.service.AthleteService;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@RequestMapping("/api}/athlete")
public class AthleteResource implements CrudResource<Athlete, Integer> {

    @Autowired
    AthleteService service;

    @Override
    @GetMapping("/{id}")
    public Athlete findById(@PathVariable Integer id) {
        return service.findById(new Athlete(id));
    }
    
    @GetMapping("/user}/id")
    public Athlete findByUser(@PathVariable Integer id) {
        User user = new User();
        user.setIdUser(id);
        return service.findByUser(user);
    }

    @Override
    @GetMapping
    public Iterable findAll() {
        return service.findAll();
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
    public Athlete save(@Valid @NotNull @NotEmpty @RequestBody Athlete t) {
        return service.save(t);
    }

    @Override
    @PutMapping("/{id}")
    public Athlete update(@Valid @NotNull @NotEmpty @RequestBody Athlete t, @PathVariable Integer id) {
        if (t.getIdAthlete() != id) {
            throw new UnsupportedParameterException();
        }
        service.findById(new Athlete(id));
        return service.save(t);
    }

    @Override
    @DeleteMapping("/{id}")
    public Map<String, String> delete(@PathVariable Integer id) {
        Athlete athlete = service.findById(new Athlete(id));
        service.delete(athlete);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "Entity deleted");
        return map;
    }

}
