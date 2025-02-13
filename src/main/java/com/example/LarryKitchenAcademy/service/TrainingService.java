package com.example.LarryKitchenAcademy.service;
import com.example.LarryKitchenAcademy.dto.DashboardDto;
import com.example.LarryKitchenAcademy.dto.RequestTrainingDto;
import com.example.LarryKitchenAcademy.dto.RespondRequestDto;
import com.example.LarryKitchenAcademy.entity.Training;
import com.example.LarryKitchenAcademy.repository.TrainingRepository;
import com.example.LarryKitchenAcademy.utils.ApiResponse;
import com.example.LarryKitchenAcademy.utils.TrainingStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;
    
    public List<DashboardDto> getTrainingList(){
        List<Training> trainingList = trainingRepository.findAll();
        List<DashboardDto> dashboardDtos = new ArrayList<>();

        for (Training training : trainingList) {
            DashboardDto dto = new DashboardDto();
            dto.setTrainingId(training.getTrainingId());
            dto.setTrainingName(training.getTrainingName());
            dto.setTrainingDesc(training.getTrainingDesc());
            dto.setTrainingCapacity(training.getTrainingCapacity());
            dto.setTrainingClass(training.getTrainingClass());
            dto.setTrainingTeacherId(training.getTrainingTeacherId());
            dto.setTrainingStatus(training.getTrainingStatus());
            dto.setTrainingDate(training.getTrainingDate());

            dashboardDtos.add(dto);
        }

        return dashboardDtos;
    }

    public ApiResponse<String> requestTraining(RequestTrainingDto input) {

        try{
            Date date = new Date();
            Training training = new Training();
            training.setTrainingTeacherId(input.getUserId());
            training.setTrainingName(input.getTrainingName());
            training.setTrainingDesc(input.getTrainingDesc());
            training.setTrainingCapacity(input.getTrainingCapacity());
            training.setTrainingClass(input.getTrainingClass());
            training.setTrainingDate(input.getTrainingDate());
            training.setTrainingStatus(TrainingStatus.PENDING);
            training.setTrainingCreateDate(date);

            trainingRepository.save(training);

            return new ApiResponse<String>("Training class created successfully", null);
        } catch (Exception e) {
            return new ApiResponse<String>("Error creating training class : " + e.getMessage(), null);
        }
    }

    public ApiResponse<String> respondRequest(RespondRequestDto input){
        Optional<Training> trainingTemp = trainingRepository.findById(input.getTrainingId());
        if(!trainingTemp.isPresent()) {
            return new ApiResponse<String>("Error data is not found", null);
        }
        Training training = trainingTemp.get();
        String status = null;
    
        switch (input.getRespondType()) {
            case "Approve":
                status = "OPEN";
                break;
            case "Reject":
                status = "DENIED";
                break;
            case "Cancel":
                status = "CANCELLED";
                break;
            default:
                return new ApiResponse<String>("Error invalid respond", null);
        }

        TrainingStatus trainingStatus = TrainingStatus.valueOf(status.toUpperCase());
        try{
            training.setTrainingStatus(trainingStatus);
            trainingRepository.save(training);

            return new ApiResponse<String>("Training class status updated successfully!", null);
        } catch (Exception e) {
            return new ApiResponse<String>("Error updating training class status : " + e.getMessage(), null);
        }
    }
}
