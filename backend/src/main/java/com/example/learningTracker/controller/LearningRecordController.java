package com.example.learningTracker.controller;

import com.example.learningTracker.dto.LearningRecordRequest;
import com.example.learningTracker.dto.LearningRecordResponse;
import com.example.learningTracker.model.LearningRecord;
import com.example.learningTracker.model.User;
import com.example.learningTracker.service.LearningRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/learning-records")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class LearningRecordController {
    
    @Autowired
    private LearningRecordService learningRecordService;
    
    // 学習記録の作成
    @PostMapping
    public ResponseEntity<LearningRecordResponse> create(@Valid @RequestBody LearningRecordRequest request, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        // RequestDTOからエンティティに変換
        LearningRecord record = new LearningRecord();//ラーニングモデルクラスをインスタンス化か
        record.setTitle(request.getTitle());//インスタンスにリクエストの
        record.setContent(request.getContent());//インスタンス
        record.setStudyMinutes(request.getStudyMinutes());
        record.setUser(currentUser);
        
        // サービスクラスのcreateRecordメソッドを呼び出して、エンティティを保存
        LearningRecord saved = learningRecordService.createRecord(record);
        
        // エンティティからResponseDTOに変換
        LearningRecordResponse response = convertToResponse(saved);
        return ResponseEntity.ok(response);
    }

    // 全ての学習記録を取得
    @GetMapping
    public ResponseEntity<List<LearningRecordResponse>> getAllRecordsByUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        List<LearningRecord> records = learningRecordService.getAllRecordsByUserId(user.getId());
        List<LearningRecordResponse> responses = records.stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    // IDによる学習記録の取得
    @GetMapping("/{id}")
    public ResponseEntity<LearningRecordResponse> getById(@PathVariable Long id) {
        try {
            LearningRecord record = learningRecordService.getRecordById(id);
            return ResponseEntity.ok(convertToResponse(record));
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

    // エンティティからResponseDTOへの変換メソッド
    private LearningRecordResponse convertToResponse(LearningRecord record) {
        LearningRecordResponse response = new LearningRecordResponse();
        response.setId(record.getId());
        response.setTitle(record.getTitle());
        response.setContent(record.getContent());
        response.setStudyMinutes(record.getStudyMinutes());
        response.setCreatedAt(record.getCreatedAt());
        response.setUpdatedAt(record.getUpdatedAt());
        return response;
    }
} 