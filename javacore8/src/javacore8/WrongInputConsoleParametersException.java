package javacore8;
/*
 * This class is used for throwing custom exceptions 
 */
		
public class WrongInputConsoleParametersException extends Exception{

	WrongInputConsoleParametersException(String message){
		super(message);
	}
}
