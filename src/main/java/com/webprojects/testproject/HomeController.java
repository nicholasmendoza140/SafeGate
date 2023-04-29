package com.webprojects.testproject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.webprojects.testproject.models.Login;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private LoginRepository loginRepository;

    @GetMapping("/")
    public String home(Model model) {
        List<Login> logins = loginRepository.findAll();
        model.addAttribute("logins", logins);
        return "home";
    }
}
