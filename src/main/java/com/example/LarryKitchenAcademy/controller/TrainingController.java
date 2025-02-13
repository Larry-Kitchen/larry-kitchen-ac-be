package com.example.LarryKitchenAcademy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LarryKitchenAcademy.dto.DashboardDto;
import com.example.LarryKitchenAcademy.dto.RequestTrainingDto;
import com.example.LarryKitchenAcademy.dto.RespondRequestDto;
import com.example.LarryKitchenAcademy.service.TrainingService;
import com.example.LarryKitchenAcademy.utils.ApiResponse;

@RestController
@RequestMapping("/api/training")
@CrossOrigin(origins = "*")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;
    @GetMapping("/training-list")
    public ResponseEntity<ApiResponse<List<DashboardDto>>> getTrainingList(){
        List<DashboardDto> data = trainingService.getTrainingList();
        ApiResponse<List<DashboardDto>> response = new ApiResponse<>("Data Training retrieved successfully!",data);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/request-training")
    public ResponseEntity<ApiResponse<String>> requestTraining(@RequestBody RequestTrainingDto requestTraining){
        ApiResponse<String> response = trainingService.requestTraining(requestTraining);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/respond-request")
    public ResponseEntity<ApiResponse<String>> respondTraining(@RequestBody RespondRequestDto respondRequest){
        ApiResponse<String> response = trainingService.respondRequest(respondRequest);
        return ResponseEntity.ok(response);
    }
}
