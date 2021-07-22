package com.example.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProgrammingLanguageDto {

    @NotNull(message = "Name shouldn't be empty")
    private String name;


}
