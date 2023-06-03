package com.webprojects.testproject;

import com.webprojects.testproject.models.Login;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Controller
public class WebController {

    @Autowired
    private LoginRepository loginRepository;
    @RequestMapping(value="/addLogin", method= RequestMethod.GET)
    public String addLogin() {
        return "addLogin";
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public String newLogin(@RequestParam(value = "action", required = true) String action,
                           @RequestParam(value = "name", required = true) String name,
                           @RequestParam(value = "username", required = true) String username,
                           @RequestParam(value = "password", required = true) String password,
                           Model model, HttpServletRequest request) throws SQLException {

        if (action.equals("NEW LOGIN")) {
            Connection connection;
            connection = DBConnection.getConnection();

            String message = "";

            if (name.isEmpty() || username.isEmpty() || password.isEmpty()) {
                message = "Fields are empty";
                model.addAttribute("message", message);
                return "addLogin";
            }

            String insertQuery = "INSERT INTO login(name, username, password) VALUES ('"+name+"','"+username+"','"+password+"')";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            System.out.println(insertQuery);
            statement.executeUpdate();
        }
        List<Login> logins = loginRepository.findAll();
        model.addAttribute("logins", logins);
        return "home";
    }
}
