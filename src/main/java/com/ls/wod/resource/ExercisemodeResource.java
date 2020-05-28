package com.ls.wod.resource;

import com.ls.wod.domain.Exercisemode;
import com.ls.wod.resource.util.ReadResource;
import com.ls.wod.service.ExercisemodeService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */

@RestController
@RequestMapping("/api/exercisemode")
public class ExercisemodeResource implements ReadResource<Exercisemode, Integer> {

    @Autowired
    private ExercisemodeService service;

    @Override
    @GetMapping
    public Iterable findAll() {
        return service.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Exercisemode findById(@PathVariable Integer id) {
        return service.findById(new Exercisemode(id));
    }

    @Override
    @GetMapping("/count")
    public HashMap<String, Long> count() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("count", service.count());
        return map;
    }

}
