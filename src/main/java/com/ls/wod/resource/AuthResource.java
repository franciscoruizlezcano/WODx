package com.ls.wod.resource;

import com.ls.wod.domain.User;
import com.ls.wod.dto.AuthDTO;
import com.ls.wod.dto.UserDTO;
import com.ls.wod.service.AuthService;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author francisco
 */

@RestController
@RequestMapping("/api/auth")
public class AuthResource{
    
    @Autowired
    AuthService authService;

    @PostMapping
    public HashMap<String, String> login(@RequestBody AuthDTO user) {
        String jwt = authService.login(user.getUsername(), user.getPassword());
        HashMap<String, String> map = new HashMap<>();
        map.put("message", jwt);
        return map;
    }

    @PostMapping("/logout/{id}")
    public HashMap<String, String> logout(@PathVariable Integer id) {
        authService.logout(id);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "User logout");
        return map;
    }     
}
