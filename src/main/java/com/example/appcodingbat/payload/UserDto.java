package com.example.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    @NotNull(message = "email shouldn't be empty")
    private String email;

    @NotNull(message = "PhoneNUmber shouldnt be empty")
    private String phoneNumber;

}
