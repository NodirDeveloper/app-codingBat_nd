package com.example.appcodingbat.payload;

import com.example.appcodingbat.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class QuestionDto {


    @NotNull(message = "Name shouldn't be empty")
    private String name;

    @NotNull(message = "QuestionText shouldn't be empty")
    private String questionText;

    @NotNull(message = "ExampleTest shouldn't be empty")
    private String exampleTest;

    private String solution;

    @ManyToOne(optional = false)
    private Integer topicId;



}
