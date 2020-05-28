package com.ls.wod.resource;

import com.ls.wod.resource.util.ReadResource;
import com.ls.wod.domain.Country;
import com.ls.wod.service.CountryService;
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
@RequestMapping("/api/country")
public class CountryResource implements ReadResource<Country, Integer>{

    @Autowired
    private CountryService service;

    @Override
    @GetMapping
    public Iterable findAll() {
        return service.findAll();
    }

    @Override
    @GetMapping("/{id}")
    public Country findById(@PathVariable Integer id) {
        return service.findById(new Country(id));
    }

    @Override
    @GetMapping("/count")
    public HashMap<String, Long> count() {
        HashMap<String, Long> map = new HashMap<>();
        map.put("count",service.count());
        return map;
    }

    @GetMapping("/{phonecode}")
    public Country findByPhoneCode(@PathVariable String phonecode) {
        return service.findByPhoneCode(phonecode);
    }

    @GetMapping("/description/{description}")
    public Iterable findByDescription(@PathVariable String description) {
        return service.findByDescription(description);
    }
}
