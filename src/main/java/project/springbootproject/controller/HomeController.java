package project.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

/**
 * Controller for the home page
 * Handles requests to "/" and returns the index.html template
 */
@Controller
public class HomeController {
    
    /**
     * Handles GET requests to the root URL "/"
     * Returns the home page (index.html from templates folder)
     * Passes session data to the view for personalization
     */
    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // Get username from session if logged in
        String username = (String) session.getAttribute("username");
        
        if (username != null) {
            model.addAttribute("username", username);
        }
        
        return "index";
    }
}
