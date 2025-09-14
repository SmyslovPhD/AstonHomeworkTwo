/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.astondevs.learn.astonhomeworktwo;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.*;

import static ru.astondevs.learn.astonhomeworktwo.AstonHomeworkTwo.println;

/**
 *
 * @author fds
 */
public class QuizCardBuilder {
	
	private ArrayList<QuizCard> cardList = new ArrayList<>();
	private JTextArea question;
	private JTextArea answer;
	private JFrame frame;
	
	public void go() {
		// Создаем GUI
		frame = new JFrame("Редактор Квизов");
		JPanel mainPanel = new JPanel();
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		
		question = createTextArea(bigFont);
		JScrollPane qScroller = createScroller(question);
		answer = createTextArea(bigFont);
		JScrollPane aScroller = createScroller(answer);
		
		mainPanel.add(new JLabel("Вопрос"));
		mainPanel.add(qScroller);
		mainPanel.add(new JLabel("Ответ"));
		mainPanel.add(aScroller);
		
		JButton nextButton = new JButton("След. карточка");
		nextButton.addActionListener(e -> nextCard());
		mainPanel.add(nextButton);
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Файл");
		
		JMenuItem newMenuItem = new JMenuItem("Создать файл");
		newMenuItem.addActionListener(e -> clearAll());
		JMenuItem saveMenuItem = new JMenuItem("Сохранить файл");
		saveMenuItem.addActionListener(e -> saveCard());
		
		fileMenu.add(newMenuItem);
		fileMenu.add(saveMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(480, 550);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private JScrollPane createScroller(JTextArea textArea) {
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		return scroller;
	}
	
	private JTextArea createTextArea(Font font) {
		JTextArea textArea = new JTextArea(6, 20);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFont(font);
		return textArea;
	}
	
	private QuizCard createQuizCard() {
		// создаем карту из того, что ввели в окна
		return new QuizCard(question.getText(), answer.getText());
	}
	
	private void nextCard() {
		// Добавляем карту ко списку
		// и чистим поле с текстом
		QuizCard card = createQuizCard();
		cardList.add(card);
		clearCard();
	}
	
	private void saveCard() {
		// рисуем окно для сохранения набора карт
		QuizCard card = createQuizCard();
		cardList.add(card);
		
		JFileChooser fileSave = new JFileChooser();
		fileSave.showSaveDialog(frame);
		// обрабатываем наше исключение
		try {
			saveFile(fileSave.getSelectedFile().toPath());
		}
		catch (MyException e) {
			println(e.getMessage());
		}
	}
	
	private void clearAll() {
		cardList.clear();
		clearCard();
	}
	
	private void clearCard() {
		// чистим тексты в полях ввода
		question.setText("");
		answer.setText("");
		question.requestFocus();
	}
	
	private void saveFile(Path path) throws MyException {
		// проходимся по списку с карточками стримом и
		// сохраняем их в структурированный текстовый файл
		try {
			java.util.List<String> lines;
			//стримом создаем строку типа "Вопрос/Ответ"
			lines = cardList.stream()
							.map(c -> c.getQuestion() + "/" + c.getAnswer() + "\n")
							.collect(Collectors.toList());
			Files.write(path, lines);
		} catch (Exception e) {
			throw new MyException("Exception on write to file: " + 
					e.getMessage());
		}
	}
}