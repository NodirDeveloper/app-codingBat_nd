package com.example.appcodingbat.controller;

import com.example.appcodingbat.entity.ProgrammingLangugage;
import com.example.appcodingbat.payload.ApiResponse;
import com.example.appcodingbat.payload.ProgrammingLanguageDto;
import com.example.appcodingbat.repository.ProgrammingLanguageRepository;
import com.example.appcodingbat.service.ProgrammingLanguageService;
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
@RequestMapping("/api/programmingLanguage")
public class ProgrammingLanguageController {

    @Autowired
    ProgrammingLanguageService programmingLanguageService;

    @GetMapping
    public ResponseEntity<?> getProgrammingLangugageListMapping(){
        List<ProgrammingLangugage> programmingLangugageList = programmingLanguageService.getProgrammingLangugageList();
        return ResponseEntity.ok(programmingLangugageList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProgrammingLangugageByIdMapping(@PathVariable Integer id){
        ProgrammingLangugage programmingLangugageByI = programmingLanguageService.getProgrammingLangugageByI(id);
        return ResponseEntity.ok(programmingLangugageByI);
    }

    @PostMapping
    public ResponseEntity<?> addProgrammingLangugageMapping(@Valid @RequestBody ProgrammingLanguageDto programmingLanguageDto){
        ApiResponse apiResponse = programmingLanguageService.addProgrammingLangugage(programmingLanguageDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editProgrammingLangugageMapping(@RequestBody ProgrammingLanguageDto programmingLanguageDto,@PathVariable Integer id){
        ApiResponse apiResponse = programmingLanguageService.editProgrammingLangugage(programmingLanguageDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProgrammingLangugageById(@PathVariable Integer id){
        ApiResponse apiResponse = programmingLanguageService.deleteProgrammingLangugageById(id);
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
