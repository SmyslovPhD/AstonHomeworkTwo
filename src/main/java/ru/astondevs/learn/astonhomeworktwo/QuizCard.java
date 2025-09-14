/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.astondevs.learn.astonhomeworktwo;

/**
 *
 * @author fds
 */
public class QuizCard {
	
	private String question;	
	private String answer;

	private String formatString(String s) {
		s = s.replaceAll("\\s++", "");
		return s.trim()
				.replaceAll("\n|/", " ")
				.replaceAll("\\s+", " ");
	}
	
	public QuizCard(String question, String answer) {
		this.question = formatString(question);
		this.answer = formatString(answer);
	}

	public String getAnswer() {
		return answer;
	}

	public String getQuestion() {
		return question;
	}
}