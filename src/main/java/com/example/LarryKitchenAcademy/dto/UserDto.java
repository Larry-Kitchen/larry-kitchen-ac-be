package com.example.LarryKitchenAcademy.dto;

import com.example.LarryKitchenAcademy.utils.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private int userId;
    private String username;
    private UserRole role;
}
