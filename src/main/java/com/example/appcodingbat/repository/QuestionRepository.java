package com.example.appcodingbat.repository;

import com.example.appcodingbat.entity.Question;
import com.example.appcodingbat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
