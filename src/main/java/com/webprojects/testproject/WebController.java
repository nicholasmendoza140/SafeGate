package com.webprojects.testproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {
    @RequestMapping(value="/addLogin", method= RequestMethod.GET)
    public String addLogin() {
        return "addLogin";
    }
}
