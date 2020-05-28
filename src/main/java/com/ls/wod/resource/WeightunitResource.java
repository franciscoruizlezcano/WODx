package com.ls.wod.resource;

import com.ls.wod.domain.Weightunit;
import com.ls.wod.resource.util.ReadResource;
import com.ls.wod.service.WeightunitService;
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
@RequestMapping("/api/weightunit")
public class WeightunitResource implements ReadResource<Weightunit, Short> {

    @Autowired
    private WeightunitService service;

    @GetMapping
    @Override
    public Iterable findAll() {
        return service.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Weightunit findById(@PathVariable Short id) {
        return service.findById(new Weightunit(id));
    }

    @Override
    @GetMapping("/count")
    public HashMap<String, Long> count() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("count", service.count());
        return map;
    }
}
