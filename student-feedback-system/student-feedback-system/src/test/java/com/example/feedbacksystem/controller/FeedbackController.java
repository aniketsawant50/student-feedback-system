package com.example.feedbacksystem.controller;

import com.example.feedbacksystem.model.Feedback;
import com.example.feedbacksystem.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackRepository repo;

    // Show feedback form
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "feedback_form";
    }

    // Handle form submission with duplicate prevention
    @PostMapping("/submit")
    public String submitFeedback(@ModelAttribute Feedback feedback, Model model) {
        // ✅ Check if feedback already exists (same email + course)
        boolean exists = repo.existsByEmailAndCourseName(
                feedback.getEmail(), feedback.getCourseName()
        );

        if (exists) {
            //  Duplicate found — show error message
            model.addAttribute("errorMessage", "⚠️ You have already submitted feedback for this course.");
            model.addAttribute("feedback", new Feedback());
            return "feedback_form";
        }

        //  If new feedback, save it
        repo.save(feedback);

        // Pass the feedback info to thank-you page
        model.addAttribute("feedback", feedback);

        // Show thank-you page directly (not redirect)
        return "thank_you";
    }

    // Thank you page (if directly accessed)
    @GetMapping("/thankyou")
    public String thankYou() {
        return "thank_you";
    }

    // Show all feedbacks (for admin or general view)
    @GetMapping("/all-feedbacks")
    public String listAllFeedbacks(Model model) {
        model.addAttribute("feedbackList", repo.findAll());
        return "feedback_list";
    }
}
