package com.connorhaigh.javabrainfuck.exceptions;

public class EvaluationException extends Exception
{
	/**
	 * Create a new evaluation exception with details about the error.
	 * @param message the message
	 * @param character the offending character
	 * @param position the index of said character
	 */
	public EvaluationException(String message, char character, int position)
	{
		super(message + " (with character '" + character + "' at position " + position + ")");
	}
	
	/**
	 * Create a new evaluation exception.
	 * @param message the message
	 */
	public EvaluationException(String message)
	{
		super(message);
	}
	
	public static final long serialVersionUID = 1;
}
