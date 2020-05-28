package com.ls.wod.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author francisco
 */

@Controller
@RequestMapping("/help")
public class HelpController {
    
    @GetMapping
    public String index(Model model, @AuthenticationPrincipal org.springframework.security.core.userdetails.User userLog) {
        return "layout/help/index";
    }

}
