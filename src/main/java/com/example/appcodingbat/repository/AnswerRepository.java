package com.example.appcodingbat.repository;

import com.example.appcodingbat.entity.Answer;
import com.example.appcodingbat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

}
