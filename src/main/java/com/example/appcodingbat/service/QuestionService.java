package com.example.appcodingbat.service;

import com.example.appcodingbat.entity.Question;
import com.example.appcodingbat.entity.Topic;
import com.example.appcodingbat.payload.ApiResponse;
import com.example.appcodingbat.payload.QuestionDto;
import com.example.appcodingbat.repository.QuestionRepository;
import com.example.appcodingbat.repository.TopicRepository;
import com.example.appcodingbat.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    TopicRepository topicRepository;

    public List<Question> getQuestionList() {
        List<Question> questionList = questionRepository.findAll();
        return questionList;
    }

    public Question getQuestionById(Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.orElse(null);
    }


    public ApiResponse addQuestion(QuestionDto questionDto) {
        boolean existsByName = questionRepository.existsByName(questionDto.getName());
        if (existsByName)
            return new ApiResponse("THis name already exist!",false);
        Optional<Topic> optionalTopic = topicRepository.findById(questionDto.getTopicId());
        if (!optionalTopic.isPresent())
            return new ApiResponse("Topic with such id not found",false);
        Question question=new Question();
        question.setName(questionDto.getName());
        question.setQuestionText(questionDto.getQuestionText());
        question.setExampleTest(questionDto.getExampleTest());
        question.setSolution(questionDto.getSolution());
        question.setTopic(optionalTopic.get());
        questionRepository.save(question);
        return new ApiResponse("Question added",true);
    }


    public ApiResponse editQuestion(QuestionDto questionDto, Integer id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (!optionalQuestion.isPresent())
            return new ApiResponse("Question with such id not found", false);
        boolean existsByNameAndIdNot = questionRepository.existsByNameAndIdNot(questionDto.getName(), id);
        if (existsByNameAndIdNot)
            return new ApiResponse("This name already exists!",false);
        Optional<Topic> optionalTopic = topicRepository.findById(questionDto.getTopicId());
        if (!optionalTopic.isPresent())
            return new ApiResponse("Topic with such id not found",false);
        Question editingQuestion = optionalQuestion.get();
        editingQuestion.setName(questionDto.getName());
        editingQuestion.setQuestionText(questionDto.getQuestionText());
        editingQuestion.setExampleTest(questionDto.getExampleTest());
        editingQuestion.setSolution(questionDto.getSolution());
        editingQuestion.setTopic(optionalTopic.get());
        questionRepository.save(editingQuestion);
        return new ApiResponse("Question edited",true);

    }


    public ApiResponse deleteQuestionByid(Integer id) {
        try {
            questionRepository.deleteById(id);
            return new ApiResponse("Question deleted",true);
        }catch (Exception e){
            return new ApiResponse("Question with such id not found",false);
        }
    }
}

