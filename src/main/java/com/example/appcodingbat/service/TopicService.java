package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.ProgrammingLangugage;
import com.example.appcodingbat.entity.Topic;
import com.example.appcodingbat.payload.ApiResponse;
import com.example.appcodingbat.payload.TopicDto;
import com.example.appcodingbat.repository.ProgrammingLanguageRepository;
import com.example.appcodingbat.repository.TopicRepository;
import com.example.appcodingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ProgrammingLanguageRepository programmingLanguageRepository;

    public List<Topic> getTopicList(){
        List<Topic> topicList = topicRepository.findAll();
        return topicList;
    }

    public Topic getTopicById(Integer id){
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        return optionalTopic.orElse(null);
    }

    public ApiResponse addTopic(TopicDto topicDto){
        boolean existsByName = topicRepository.existsByName(topicDto.getName());
        if (existsByName)
            return new ApiResponse("This name already exists",false);
        Optional<ProgrammingLangugage> optionalProgrammingLangugage = programmingLanguageRepository.findById(topicDto.getProgrammingLanguageId());
        if (!optionalProgrammingLangugage.isPresent())
            return new ApiResponse("ProgrammingLanguage not found", false);
        Topic topic=new Topic();
        topic.setTopicDescription(topicDto.getTopicDescription());
        topic.setName(topicDto.getName());
        topic.setProgrammingLangugage(optionalProgrammingLangugage.get());
        topicRepository.save(topic);
        return new ApiResponse("Topic added",true);
    }
    public ApiResponse editTopic(TopicDto topicDto,Integer id){
        Optional<Topic> optionalTopic = topicRepository.findById(id);
        if (!optionalTopic.isPresent())
            return new ApiResponse("Topic with such id not found", false);
        boolean existsByNameAndIdNot = topicRepository.existsByNameAndIdNot(topicDto.getName(), id);
        if (existsByNameAndIdNot)
            return new ApiResponse("THis name already exist!",false);
        Optional<ProgrammingLangugage> optionalProgrammingLangugage = programmingLanguageRepository.findById(topicDto.getProgrammingLanguageId());
        if (!optionalProgrammingLangugage.isPresent())
            return new ApiResponse("ProgrammingLanguage with such id not found", false);

        Topic editingTopic = optionalTopic.get();
        editingTopic.setName(topicDto.getName());
        editingTopic.setTopicDescription(topicDto.getTopicDescription());
        editingTopic.setProgrammingLangugage(optionalProgrammingLangugage.get());
        topicRepository.save(editingTopic);
        return new ApiResponse("Topic edited",true);
    }
    public ApiResponse deleteTopicById(Integer id){
        try {
            topicRepository.deleteById(id);
            return new ApiResponse("TOpic deleted",true);
        }catch (Exception e){
            return new ApiResponse("TOpic with such id not found",false);
        }
    }


}
