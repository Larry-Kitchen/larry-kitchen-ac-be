package com.example.LarryKitchenAcademy.utils;

import com.example.LarryKitchenAcademy.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthResponse {
    private String message;
    UserDto userDto;
    private String token;
}
