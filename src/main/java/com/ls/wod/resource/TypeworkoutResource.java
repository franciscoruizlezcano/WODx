package com.ls.wod.resource;

import com.ls.wod.domain.Typeworkout;
import com.ls.wod.resource.util.ReadResource;
import com.ls.wod.service.TypeworkoutService;
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
@RequestMapping("/api/typeworkout")
public class TypeworkoutResource implements ReadResource<Typeworkout, Integer> {

    @Autowired
    private TypeworkoutService service;

    @Override
    @GetMapping
    public Iterable findAll() {
        return service.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Typeworkout findById(@PathVariable Integer id) {
        return service.findById(new Typeworkout(id));
    }

    @Override
    @GetMapping("/count")
    public HashMap<String, Long> count() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("count", service.count());
        return map;
    }
}