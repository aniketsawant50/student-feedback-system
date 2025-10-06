package com.example.feedbacksystem.repository;

import com.example.feedbacksystem.model.Feedback;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    boolean existsByEmailAndCourseName(String email, String courseName);

    public List<Feedback> findByEmail(String email);
}
