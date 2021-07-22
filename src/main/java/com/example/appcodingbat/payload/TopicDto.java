package com.example.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class TopicDto {

    @NotNull(message = "TopicDescription shouldn't be empty")
    private String topicDescription;

    @NotNull(message = "Name shouldn't be empty")
    private String name;

    @NotNull(message = "ProgrammingLanguage shouldn't be empty")
    private Integer programmingLanguageId;


}
