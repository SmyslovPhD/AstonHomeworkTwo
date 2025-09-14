/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.astondevs.learn.astonhomeworktwo;

import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

import static ru.astondevs.learn.astonhomeworktwo.AstonHomeworkTwo.println;

/**
 *
 * @author fds
 */
public class QuizCardPlayer {
	
	private ArrayList<QuizCard> cardList;
	private int currentCardIndex;
	private QuizCard currentCard;
	
	private JTextArea display;
	private JFrame frame;
	private JButton nextButton;
	private boolean isShowAnswer;
	
	public void go() {
		//Создаем окно, выбираем шрифт
		frame = new JFrame("Сыграем в Квиз!");
		JPanel mainPanel = new JPanel();
		//Выбираем шрифт
		Font bigFont = new Font("sanserif", Font.BOLD, 24);
		//Создаем панель с текстом вопроса
		display = new JTextArea(10, 20);
		display.setFont(bigFont);
		display.setLineWrap(true);
		display.setEditable(false);
		JScrollPane scroller = new JScrollPane(display);
		scroller.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS
		);
		scroller.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
		);
		scroller.setAlignmentX(1);
		mainPanel.add(scroller);
		//Кнопка для ответа
		nextButton = new JButton("Показать ответ");
		nextButton.addActionListener(e -> nextCard());
		nextButton.setEnabled(false);
		mainPanel.add(nextButton);
		//Строка меню
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("Файл");
		JMenuItem loadMenuItem = new JMenuItem("Загрузить квиз");
		loadMenuItem.addActionListener(e -> open());
		fileMenu.add(loadMenuItem);
		menuBar.add(fileMenu);
		frame.setJMenuBar(menuBar);
		//Отображаем готовое окно c выравниванием и размерами
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(500, 430);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void nextCard() {
		if (isShowAnswer) {
			// Отображаем ответ
			display.setText(currentCard.getAnswer());
			nextButton.setText("След. вопрос");
			isShowAnswer = false;
		} else {
			// Отображаем следующий вопрос
			if (currentCardIndex < cardList.size()) {
				showNextCard();
			} else {
				// Вопросы всё!
				display.setText("Вопросы закончились!");
				nextButton.setEnabled(false);
				frame.dispose();
			}
		}
	}
	
	private void open() {
		//Окно выбора файла
		JFileChooser fileOpen = new JFileChooser();
		fileOpen.showOpenDialog(frame);
		try {
			loadFile(fileOpen.getSelectedFile().toPath());
		} catch (MyException e) {
			println(e.getMessage());
		}
	}
	
	private void loadFile(Path path) throws MyException {
		cardList = new ArrayList<>();
		currentCardIndex = 0;
		try (Stream<String> lines = Files.lines(path)) {
			lines.forEach(line -> makeCard(line));
		} catch (IOException e) {
			throw new MyException("Exception on reading: " + e.getMessage());
		}
		showNextCard();
		nextButton.setEnabled(true);
	}
	
	private void makeCard(String lineToParse) {
		String[] result = lineToParse.split("/");
		QuizCard card = new QuizCard(result[0], result[1]);
		cardList.add(card);
		println("made a card");
	}
	
	private void showNextCard() {
		currentCard = cardList.get(currentCardIndex);
		currentCardIndex++;
		display.setText(currentCard.getQuestion());
		nextButton.setText("Показать ответ");
		isShowAnswer = true;
	}
}