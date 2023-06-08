package com.webprojects.testproject;

import com.webprojects.testproject.models.Login;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


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

    @RequestMapping(value="/loginDelete/{id}")
    public String loginDelete(@PathVariable("id") int id, Model model) throws SQLException {
        /*Connection connection;
        connection = DBConnection.getConnection();

        String deleteQuery = "DELETE FROM login WHERE id = "+id+"";
        PreparedStatement statement = connection.prepareStatement(deleteQuery);
        statement.executeUpdate();*/
        loginRepository.deleteById(id);
        List<Login> logins = loginRepository.findAll();
        model.addAttribute("logins", logins);
        return "redirect:/";
    }

    @RequestMapping(value="/viewLogin/{id}")
    public String viewLogin(@PathVariable("id") int id, Model model) {
        Optional<Login> login = loginRepository.findById(id);
        Login login1 = login.get();
        System.out.println(login);
        System.out.println(login1.getName());
        model.addAttribute("login", login1);
        return "viewLogin";
    }
}
