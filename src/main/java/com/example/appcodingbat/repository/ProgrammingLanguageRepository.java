package com.example.appcodingbat.repository;

import com.example.appcodingbat.entity.ProgrammingLangugage;
import com.example.appcodingbat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgrammingLanguageRepository extends JpaRepository<ProgrammingLangugage,Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
