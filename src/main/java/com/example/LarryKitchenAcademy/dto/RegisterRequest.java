package com.example.LarryKitchenAcademy.dto;

import com.example.LarryKitchenAcademy.utils.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {

    private String username;

    private String password;

    private UserRole role;

    private Date createDate;

}
