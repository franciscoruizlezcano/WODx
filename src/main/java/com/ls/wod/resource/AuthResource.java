package com.ls.wod.resource;

import com.ls.wod.domain.User;
import com.ls.wod.service.AuthService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author francisco
 */

@RestController
@RequestMapping("/api/auth")
public class AuthResource{
    
    @Autowired
    AuthService service;
    
    public HashMap<String, String> login(@RequestBody User user) {
        String jwt = service.login(user.getUsername(), user.getPassword());
        HashMap<String, String> map = new HashMap<>();
        map.put("message", jwt);
        return map;
    }

    @PostMapping("/logout/{id}")
    public HashMap<String, String> logout(@RequestBody Integer id) {
        service.logout(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "User logout");
        return map;
    }     
}
