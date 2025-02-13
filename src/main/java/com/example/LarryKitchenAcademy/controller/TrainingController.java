package com.example.LarryKitchenAcademy.controller;

import com.example.LarryKitchenAcademy.dto.DashboardDto;
import com.example.LarryKitchenAcademy.dto.RequestTrainingDto;
import com.example.LarryKitchenAcademy.dto.RespondRequestDto;
import com.example.LarryKitchenAcademy.service.TrainingService;
import com.example.LarryKitchenAcademy.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/training")
@CrossOrigin(origins = "*")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;
    
    @GetMapping("/training-list")
    public ResponseEntity<ApiResponse<List<DashboardDto>>> getTrainingList(){
        return ResponseEntity.ok(trainingService.getTrainingList());
    }

    @PostMapping("/request-training")
    public ResponseEntity<ApiResponse<String>> requestTraining(@RequestBody RequestTrainingDto requestTraining){
        ApiResponse<String> response = trainingService.requestTraining(requestTraining);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/respond-request")
    public ResponseEntity<ApiResponse<String>> respondTraining(@RequestBody RespondRequestDto respondRequest){
        ApiResponse<String> response = trainingService.respondRequest(respondRequest);
        return ResponseEntity.ok(response);
    }
}