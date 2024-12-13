package com.example.learningTracker.controller;

import com.example.learningTracker.model.LearningRecord;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/learning-records")
public class LearningRecordController {
    
    @PostMapping
    public LearningRecord create(@RequestBody LearningRecord learningRecord) {
        return learningRecord;
    }

    @GetMapping
    public String test() {
        return "Learning records endpoint is working!";
    }
} 