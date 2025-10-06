package com.example.feedbacksystem.controller;

import com.example.feedbacksystem.model.Feedback;
import com.example.feedbacksystem.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminFeedbackController {

    @Autowired
    private FeedbackRepository repo;

    // Show all feedbacks for admin
    @GetMapping("/feedbacks")
    public String listFeedbacks(Model model) {
        model.addAttribute("feedbackList", repo.findAll());
        return "admin_feedback_list";
    }

    // Delete a feedback by id
    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/admin/feedbacks";
    }
}
