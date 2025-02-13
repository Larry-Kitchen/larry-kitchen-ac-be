package com.example.LarryKitchenAcademy.utils;

import com.example.LarryKitchenAcademy.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class AuthResponse {
    private String message;
    UserDto data;
    private String token;
}
