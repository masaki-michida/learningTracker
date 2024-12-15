package com.example.learningTracker.repository;

import com.example.learningTracker.model.LearningRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LearningRecordRepository extends JpaRepository<LearningRecord, Long> {
} 