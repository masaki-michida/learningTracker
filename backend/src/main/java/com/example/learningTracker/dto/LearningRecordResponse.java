package com.example.learningTracker.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class LearningRecordResponse {
    private Long id;
    private String title;
    private String content;
    private Integer studyMinutes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}