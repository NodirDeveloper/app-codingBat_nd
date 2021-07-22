package com.example.appcodingbat.controller;

import com.example.appcodingbat.entity.Answer;
import com.example.appcodingbat.payload.AnswerDto;
import com.example.appcodingbat.payload.ApiResponse;
import com.example.appcodingbat.repository.AnswerRepository;
import com.example.appcodingbat.service.AnswerService;
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
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @GetMapping
    public ResponseEntity<?> getAnswerListMapping(){
        List<Answer> answerList=answerService.getAnswerList();
        return ResponseEntity.ok(answerList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAnswerByIdMapping(@PathVariable Integer id){
        Answer answer=answerService.getAnswerById(id);
        return ResponseEntity.ok(answer);
    }

    @PostMapping
    public ResponseEntity<?> addAnswerMapping(@Valid @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse=answerService.addAnswer(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editAnswerMapping(@RequestBody AnswerDto answerDto,@PathVariable Integer id){
        ApiResponse apiResponse=answerService.editAnswer(answerDto,id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswerByIdMapping(@PathVariable Integer id){
        ApiResponse apiResponse=answerService.deleteAnswerById(id);
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
