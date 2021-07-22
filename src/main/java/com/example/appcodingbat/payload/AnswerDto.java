package com.example.appcodingbat.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AnswerDto {


    @NotNull(message = "Code shouldn't be empty")
    private String code;

    @ManyToOne(optional = false)
    private Integer userId;

    @ManyToOne(optional = false)
    private Integer topicId;



}
