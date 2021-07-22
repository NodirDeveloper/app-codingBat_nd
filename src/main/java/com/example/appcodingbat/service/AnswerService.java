package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.Answer;
import com.example.appcodingbat.entity.Topic;
import com.example.appcodingbat.entity.User;
import com.example.appcodingbat.payload.AnswerDto;
import com.example.appcodingbat.payload.ApiResponse;
import com.example.appcodingbat.repository.AnswerRepository;
import com.example.appcodingbat.repository.TopicRepository;
import com.example.appcodingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    public List<Answer> getAnswerList() {
        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }

    public Answer getAnswerById(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);
    }

    public ApiResponse addAnswer(AnswerDto answerDto) {
        Optional<Topic> optionalTopic = topicRepository.findById(answerDto.getTopicId());
        if (!optionalTopic.isPresent())
            return new ApiResponse("Topic with such id not found",false);
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("User with such id not found",false);
        Answer answer=new Answer();
        answer.setCode(answerDto.getCode());
        answer.setTopic(optionalTopic.get());
        answer.setUser(optionalUser.get());
        answerRepository.save(answer);
        return new ApiResponse("Answer added",true);
    }


    public ApiResponse editAnswer(AnswerDto answerDto, Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Answer with such id not found",false);

        Optional<Topic> optionalTopic = topicRepository.findById(answerDto.getTopicId());
        if (!optionalTopic.isPresent())
            return new ApiResponse("Topic with such id not found",false);

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("User with such id not found",false);

        Answer editingAnswer = optionalAnswer.get();
        editingAnswer.setCode(answerDto.getCode());
        editingAnswer.setTopic(optionalTopic.get());
        editingAnswer.setUser(optionalUser.get());
        answerRepository.save(editingAnswer);
        return new ApiResponse("Answer edited",true);

    }


    public ApiResponse deleteAnswerById(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Answer deleted",true);
        }catch (Exception e){
            return new ApiResponse("Answer with such id not found",false);
        }
    }

}

