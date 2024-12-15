package com.example.learningTracker.service;

import com.example.learningTracker.model.LearningRecord;
import com.example.learningTracker.repository.LearningRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LearningRecordService {
    @Autowired
    private LearningRecordRepository learningRecordRepository;
    
    public LearningRecord createRecord(LearningRecord record) {
        return learningRecordRepository.save(record);
    }
    
    public List<LearningRecord> getAllRecords() {
        return learningRecordRepository.findAll();
    }
    
    public LearningRecord getRecordById(Long id) {
        return learningRecordRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("記録が見つかりません"));
    }
    
    public void deleteRecord(Long id) {
        if (!learningRecordRepository.existsById(id)) {
            throw new RuntimeException("記録が見つかりません");
        }
        learningRecordRepository.deleteById(id);
    }
} 