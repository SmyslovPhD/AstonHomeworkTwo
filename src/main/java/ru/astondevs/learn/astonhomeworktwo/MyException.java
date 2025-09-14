/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.astondevs.learn.astonhomeworktwo;

/**
 *
 * @author fds
 */
public class MyException extends Exception {
	
	public MyException(){
		super();
	}
	
	public MyException(String message){
		super(message);
	}
	
	public MyException(String message, Throwable cause){
		super(message, cause);
	}
	
	public MyException(Throwable cause){
		super(cause);
	}
}