package com.example.feedbacksystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    // Show admin login form
    @GetMapping("/admin")
    public String adminLoginForm() {
        return "admin_login";
    }

    // Handle admin login
    @PostMapping("/admin")
    public String adminLogin(@RequestParam String username, 
                             @RequestParam String password, 
                             Model model) {
        // Hardcoded credentials
        if(username.equals("admin") && password.equals("admin123")) {
            return "redirect:/admin/feedbacks";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "admin_login";
        }
    }
}
