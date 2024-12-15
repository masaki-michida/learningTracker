package com.example.learningTracker.controller;

import com.example.learningTracker.model.LearningRecord;
import com.example.learningTracker.service.LearningRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/learning-records")
public class LearningRecordController {
    
    @Autowired
    private LearningRecordService learningRecordService;
    
    // 学習記録の作成
    @PostMapping
    public ResponseEntity<LearningRecord> create(@RequestBody LearningRecord learningRecord) {
        LearningRecord saved = learningRecordService.createRecord(learningRecord);
        return ResponseEntity.ok(saved);
    }

    // 全ての学習記録を取得
    @GetMapping
    public ResponseEntity<List<LearningRecord>> getAllRecords() {
        List<LearningRecord> records = learningRecordService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    // IDによる学習記録の取得
    @GetMapping("/{id}")
    public ResponseEntity<LearningRecord> getById(@PathVariable Long id) {
        try {
            LearningRecord record = learningRecordService.getRecordById(id);
            return ResponseEntity.ok(record);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // 学習記録の削除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            learningRecordService.deleteRecord(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 