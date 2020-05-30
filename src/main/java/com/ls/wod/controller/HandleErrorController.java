package com.ls.wod.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author francisco
 */

@Controller
public class HandleErrorController implements ErrorController {

    public HandleErrorController() {}

    private final String ERROR_PATH = "/error";

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        String response;
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            switch (statusCode) {
                case 400:
                    response =  "redirect:/400";
                    break;
                case 401:
                    response =  "redirect:/401";
                    break;
                case 403:
                    response =  "redirect:/403";
                    break;
                case 404:
                    response =  "redirect:/404";
                    break;
                case 500:
                    response =  "redirect:/500";
                    break;
                case 503:
                    response =  "redirect:/503";
                    break;
                default:
                    response =  "error";
            }
        }else{
            response =  "/error";
        }
        
        return response;
    }

    @Override
    public String getErrorPath() {
        return this.ERROR_PATH;
    }

}
