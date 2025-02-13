package com.example.LarryKitchenAcademy.dto;

import com.example.LarryKitchenAcademy.utils.UserRole;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private int userId;
    private String userName;
    private String userPassword;
    private UserRole role;
    private Date userCreateDate;
}
