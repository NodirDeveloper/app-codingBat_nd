package com.example.appcodingbat.controller;

import com.example.appcodingbat.entity.Question;
import com.example.appcodingbat.payload.ApiResponse;
import com.example.appcodingbat.payload.QuestionDto;
import com.example.appcodingbat.service.QuestionService;
import com.example.appcodingbat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<?> getQuestionListMapping(){
        List<Question> questionList=questionService.getQuestionList();
        return ResponseEntity.ok(questionList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionMapping(@PathVariable Integer id){
        Question question=questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    @PostMapping
    public ResponseEntity<?> addQuestionMapping(@RequestBody QuestionDto questionDto){
        ApiResponse apiResponse=questionService.addQuestion(questionDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editQuestionMapping(@RequestBody QuestionDto questionDto,@PathVariable Integer id){
        ApiResponse apiResponse=questionService.editQuestion(questionDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionByIdMapping(@PathVariable Integer id){
        ApiResponse apiResponse=questionService.deleteQuestionByid(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
