// backend/src/main/java/com/example/learningTracker/model/LearningRecord.java

package com.example.learningTracker.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity//データベーステーブルになることを示す
@Getter
@Setter//lombokのアノテーションで、getter, setter, toString, equals, hashCodeメソッドを自動生成
public class LearningRecord {
    @Id//主キー
    @GeneratedValue(strategy = GenerationType.IDENTITY)//ID生成方法を指定今回は自動生成
    private Integer id;
    
    private String title;
    private String content;
    private Integer studyMinutes;
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}