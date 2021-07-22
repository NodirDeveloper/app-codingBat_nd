package com.example.appcodingbat.repository;

import com.example.appcodingbat.entity.Topic;
import com.example.appcodingbat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
