package project.springbootproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for the home page
 * Handles requests to "/" and returns the index.html template
 */
@Controller
public class HomeController {
    
    /**
     * Handles GET requests to the root URL "/"
     * Returns the home page (index.html)
     */
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
