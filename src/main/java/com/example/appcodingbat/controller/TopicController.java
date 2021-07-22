package com.example.appcodingbat.controller;

import com.example.appcodingbat.entity.Topic;
import com.example.appcodingbat.payload.ApiResponse;
import com.example.appcodingbat.payload.TopicDto;
import com.example.appcodingbat.service.TopicService;
import com.example.appcodingbat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/topic")
public class TopicController {

    @Autowired
    TopicService topicService;

    @GetMapping
    public ResponseEntity<?> getTopicListMapping(){
        List<Topic> topicList = topicService.getTopicList();
        return ResponseEntity.ok(topicList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTopicByIdMapping(@PathVariable Integer id){
        Topic topicById = topicService.getTopicById(id);
        return ResponseEntity.ok(topicById);
    }

    @PostMapping
    public ResponseEntity<?> addTopicMapping(@Valid @RequestBody TopicDto topicDto){
        ApiResponse apiResponse = topicService.addTopic(topicDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editTopicMapping(@RequestBody TopicDto topicDto,@PathVariable Integer id){
        ApiResponse apiResponse = topicService.editTopic(topicDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTopicByIdMapping(@PathVariable Integer id){
        ApiResponse apiResponse = topicService.deleteTopicById(id);
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
