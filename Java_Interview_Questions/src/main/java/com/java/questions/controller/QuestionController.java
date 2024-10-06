package com.java.questions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.java.questions.entity.Questions;
import com.java.questions.service.QuestionService;

@Controller
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/questions")
	public String getAllQuestion(@ModelAttribute Model model) {
		List<Questions> allQuestions = questionService.getAllQuestions();
	
		model.addAttribute("questions", allQuestions);
		
		return "home";
	}
}
