package com.example.learningTracker.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Getter
@Setter
public class LearningRecordRequest {
    @NotBlank(message = "タイトルは必須です")
    private String title;
    
    @NotBlank(message = "内容は必須です")
    private String content;
    
    @Min(value = 1, message = "学習時間は1分以上で入力してください")
    private Integer studyMinutes;
}