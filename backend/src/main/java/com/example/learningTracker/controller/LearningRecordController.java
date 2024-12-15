package com.example.learningTracker.controller;

import com.example.learningTracker.model.LearningRecord;
import com.example.learningTracker.repository.LearningRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/learning-records")
public class LearningRecordController {
    
    @Autowired
    private LearningRecordRepository learningRecordRepository;
    
    // 学習記録の作成
    @PostMapping
    public ResponseEntity<LearningRecord> create(@RequestBody LearningRecord learningRecord) {
        LearningRecord saved = learningRecordRepository.save(learningRecord);
        return ResponseEntity.ok(saved);
    }

    // 全ての学習記録を取得
    @GetMapping
    public ResponseEntity<List<LearningRecord>> getAllRecords() {
        List<LearningRecord> records = learningRecordRepository.findAll();
        return ResponseEntity.ok(records);
    }

    // IDによる学習記録の取得
    @GetMapping("/{id}")
    public ResponseEntity<LearningRecord> getById(@PathVariable Long id) {
        return learningRecordRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 学習記録の更新
    @PutMapping("/{id}")
    public ResponseEntity<LearningRecord> update(@PathVariable Long id, @RequestBody LearningRecord learningRecord) {
        if (!learningRecordRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        learningRecord.setId(id);
        return ResponseEntity.ok(learningRecordRepository.save(learningRecord));
    }

    // 学習記録の削除
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!learningRecordRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        learningRecordRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 