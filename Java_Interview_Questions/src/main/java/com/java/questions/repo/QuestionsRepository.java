package com.java.questions.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.questions.entity.Questions;

public interface QuestionsRepository extends JpaRepository<Questions, Integer> {

}
