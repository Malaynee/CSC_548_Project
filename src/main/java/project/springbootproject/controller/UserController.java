package project.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.springbootproject.model.User;
import project.springbootproject.model.UserStorage;
import jakarta.servlet.http.HttpSession;

/**
 * Controller for user authentication (login and registration)
 * Uses traditional MVC approach with form submissions
 */
@Controller
public class UserController {

    private final UserStorage userStorage = new UserStorage();

    //show login page
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    // Handle login form submission
    @PostMapping("/login")
    public String loginUser(
            @RequestParam String username, 
            @RequestParam String password, 
            HttpSession session,
            Model model) {
        
        User user = userStorage.authenticate(username, password);
        
        if (user != null) {
            // Login successful - store username in session
            session.setAttribute("username", username);
            model.addAttribute("username", username);
            return "redirect:/"; // Redirect to home page
        } else {
            // Login failed
            model.addAttribute("error", "Invalid username or password.");
            return "login"; // Stay on login page with error
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
            model.addAttribute("error", "That username is already taken.");
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

    // handles logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // clear session
        return "redirect:/"; // redirect to home
    }

}
