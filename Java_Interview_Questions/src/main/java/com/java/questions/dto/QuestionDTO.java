package com.java.questions.dto;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

public class QuestionDTO {

	private Integer questionId;
	
	private String question;
	
	private String answer;
	
	@CreationTimestamp
	private Date createdDate;

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
