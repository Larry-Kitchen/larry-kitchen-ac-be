package com.example.LarryKitchenAcademy.service;

import com.example.LarryKitchenAcademy.dto.DashboardDto;
import com.example.LarryKitchenAcademy.dto.RequestTrainingDto;
import com.example.LarryKitchenAcademy.dto.RespondRequestDto;
import com.example.LarryKitchenAcademy.entity.Training;
import com.example.LarryKitchenAcademy.repository.TrainingRepository;
import com.example.LarryKitchenAcademy.utils.ApiResponse;
import com.example.LarryKitchenAcademy.utils.TrainingStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {
    @Autowired
    private TrainingRepository trainingRepository;

    public ApiResponse<List<DashboardDto>> getTrainingList(){
        try{
            List<Object[]> trainingList = trainingRepository.findAllTrainingWithTeacherName();
            List<DashboardDto> dashboardDtos = new ArrayList<>();

            for (Object[] training : trainingList) {
                DashboardDto dto = new DashboardDto();
                dto.setTrainingId(((Training) training[0]).getTrainingId());
                dto.setTrainingName(((Training) training[0]).getTrainingName());
                dto.setTrainingDesc(((Training) training[0]).getTrainingDesc());
                dto.setTrainingCapacity(((Training) training[0]).getTrainingCapacity());
                dto.setTrainingClass(((Training) training[0]).getTrainingClass());
                dto.setTrainingTeacherId(((Training) training[0]).getTrainingTeacherId());
                dto.setTrainingStatus(((Training) training[0]).getTrainingStatus());
                dto.setTrainingDate(((Training) training[0]).getTrainingDate());
                dto.setTrainingTeacherName((String) training[1]);
                dashboardDtos.add(dto);
            }

            return ApiResponse.<List<DashboardDto>>builder()
                    .message("Training list fetched successfully")
                    .data(dashboardDtos)
                    .build();
        }catch (Exception e){
            return ApiResponse.<List<DashboardDto>>builder()
                    .message("Failed to fetch training list: " + e.getMessage())
                    .build();
        }
    }

    public ApiResponse<String> requestTraining(RequestTrainingDto input) {

        try{
            Date date = new Date();
            Training training = new Training();
            // 1️⃣ **Validate required fields**
            if (input == null) {
                return new ApiResponse<>("Invalid request: Input cannot be null", null);
            }
            if (!StringUtils.hasText(input.getTrainingName())) {
                return new ApiResponse<>("Training name is required", null);
            }
            if (!StringUtils.hasText(input.getTrainingClass())) {
                return new ApiResponse<>("Training class is required", null);
            }
            if (input.getTrainingCapacity() <= 0) {
                return new ApiResponse<>("Training capacity must be greater than 0", null);
            }
            if (input.getTrainingDate() == null) {
                return new ApiResponse<>("Training date is required", null);
            }

            // 2️⃣ **Check if the training date is in the future**
            if (input.getTrainingDate().before(new Date())) {
                return new ApiResponse<>("Training date must be in the future", null);
            }

            // 3️⃣ **Check if the teacher (user) exists**
            // Optional<User> teacher = userRepository.findById(input.getUserId());
            // if (teacher.isEmpty()) {
            //     return new ApiResponse<>("Invalid user ID: Teacher does not exist", null);
            // }
            training.setTrainingTeacherId(input.getUserId());
            training.setTrainingName(input.getTrainingName());
            training.setTrainingDesc(input.getTrainingDesc());
            training.setTrainingCapacity(input.getTrainingCapacity());
            training.setTrainingClass(input.getTrainingClass());
            training.setTrainingDate(input.getTrainingDate());
            training.setTrainingStatus(TrainingStatus.PENDING);
            training.setTrainingCreateDate(date);

            trainingRepository.save(training);

            return new ApiResponse<>("Training class created successfully", null);
        } catch (Exception e) {
            return new ApiResponse<>("Error creating training class : " + e.getMessage(), null);
        }
    }

    public ApiResponse<String> respondRequest(RespondRequestDto input){
        Optional<Training> trainingTemp = trainingRepository.findById(input.getTrainingId());
        if(!trainingTemp.isEmpty()) {
            return new ApiResponse<>("Error data is not found", null);
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
                return new ApiResponse<>("Error invalid respond", null);
        }

        TrainingStatus trainingStatus = TrainingStatus.valueOf(status.toUpperCase());
        try{
            training.setTrainingStatus(trainingStatus);
            trainingRepository.save(training);

            return new ApiResponse<>("Training class status updated successfully!", null);
        } catch (Exception e) {
            return new ApiResponse<>("Error updating training class status : " + e.getMessage(), null);
        }
    }
}
