package com.ls.wod.controller;

import com.ls.wod.domain.Session;
import com.ls.wod.service.SessionService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author francisco
 */

@Slf4j
@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    SessionService sessionService;

    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        List<Session> sessionList = (List<Session>) sessionService.findAll();
        model.addAttribute(sessionList);
        return "layout/session/index";
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        String response;
        try{
            Session session = sessionService.findById(new Session(id));
            sessionService.delete(session);
            response = "redirect:/session?success";
        }catch (Exception e){
            log.info(e.toString());
            response = "redirect:/session?error";
        }
        return response;
    }
}
