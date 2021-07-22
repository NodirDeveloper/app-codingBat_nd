package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.ProgrammingLangugage;
import com.example.appcodingbat.payload.ApiResponse;
import com.example.appcodingbat.payload.ProgrammingLanguageDto;
import com.example.appcodingbat.repository.ProgrammingLanguageRepository;
import com.example.appcodingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgrammingLanguageService {

    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;

    public List<ProgrammingLangugage> getProgrammingLangugageList(){
        List<ProgrammingLangugage> programmingLangugageList = programmingLanguageRepository.findAll();
        return programmingLangugageList;
    }

    public ProgrammingLangugage getProgrammingLangugageByI(Integer id){
        Optional<ProgrammingLangugage> optionalProgrammingLangugage = programmingLanguageRepository.findById(id);
        return optionalProgrammingLangugage.orElse(null);
    }

    public ApiResponse addProgrammingLangugage(ProgrammingLanguageDto programmingLanguageDto){
        boolean existsByName = programmingLanguageRepository.existsByName(programmingLanguageDto.getName());
        if (existsByName)
            return new ApiResponse("ProgrammingLangugage already exist!",false);

        ProgrammingLangugage programmingLangugage=new ProgrammingLangugage();
        programmingLangugage.setName(programmingLanguageDto.getName());
        programmingLanguageRepository.save(programmingLangugage);
        return new ApiResponse("ProgrammingLangugage added",true);
    }

    public ApiResponse editProgrammingLangugage(ProgrammingLanguageDto programmingLanguageDto,Integer id){
        Optional<ProgrammingLangugage> optionalProgrammingLangugage = programmingLanguageRepository.findById(id);
        if (!optionalProgrammingLangugage.isPresent())
            return new ApiResponse("ProgrammingLangugage with such id not found",false);
        boolean existsByNameAndIdNot = programmingLanguageRepository.existsByNameAndIdNot(programmingLanguageDto.getName(), id);
        if (existsByNameAndIdNot)
            return new ApiResponse("This name already exists",false);
        ProgrammingLangugage editingProgrammingLangugage = optionalProgrammingLangugage.get();
        editingProgrammingLangugage.setName(programmingLanguageDto.getName());
        programmingLanguageRepository.save(editingProgrammingLangugage);
        return new ApiResponse("ProgrammingLangugage edited",true);
    }

    public ApiResponse deleteProgrammingLangugageById(Integer id){
        try {
             programmingLanguageRepository.deleteById(id);
             return new ApiResponse("ProgrammingLangugage deleted",false);
        }catch (Exception e){
            return  new ApiResponse("ProgrammingLangugage with such id not found",false);
        }
    }

}
