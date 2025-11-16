package project.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class UserController {

    private final UserStorage userStorage = new UserStorage();

    //show login page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    //handle login
    @PostMapping("/login")
    public String loginUser(@RequestBody String username, @RequestParam String password, Model model) {
        
        User user = userStorage.authenticate(username, password);

        if(user != null) {
            //log in successful
            model.addAttribute("username", username);
            return "index";
        } else {
            //login failed
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    //show registration page
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    //handle registration
    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, Model model) {
        
        //check if user already exists
        if (userStorage.userExists(username)) {
            model.Addattribute("error", "That username is already taken.");
            return "register";
        } 

        try {
            userStorage.createUser(new User(username, password));
            model.addAttribute("message", "Registration successful! Please log in.");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", "Error creating user.");
            return "register";
        }
    }
    
    

}
