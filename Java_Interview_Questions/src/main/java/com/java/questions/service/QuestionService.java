package com.java.questions.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.questions.dto.QuestionDTO;
import com.java.questions.entity.Questions;
import com.java.questions.repo.QuestionsRepository;

@Service
public class QuestionService {

	@Autowired
	private QuestionsRepository questionRepository;
	
	public String addQuestion(QuestionDTO questionDTO) {
		
		Questions question= new Questions();
		
		BeanUtils.copyProperties(questionDTO, question);
		
		Questions save = questionRepository.save(question);
	
		return "Question record successfully";
	}
	
	
	public List<Questions> getAllQuestions(){
		
		List<Questions> questions = questionRepository.findAll();
	
		return questions;
	}
}
