/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ru.astondevs.learn.astonhomeworktwo;

import java.awt.*;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author fds
 */
public class AstonHomeworkTwo {
	
	public static void println(Object obj) {
		System.out.println(obj);
	}
	
	
    public static void main(String[] args) throws FileNotFoundException {
		// переписал код из книги HeadFirst Java,
		// но используя java.nio (в книге java.io)

		QuizCardBuilder builder = new QuizCardBuilder();
		QuizCardPlayer player = new QuizCardPlayer();
		
		builder.go();
		player.go();
    }
}