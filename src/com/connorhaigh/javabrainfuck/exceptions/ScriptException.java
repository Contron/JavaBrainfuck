package com.connorhaigh.javabrainfuck.exceptions;

public class ScriptException extends Exception
{
	/**
	 * Create a new script exception with details about the error.
	 * @param message the message
	 * @param character the offending character
	 * @param position the index of said character
	 */
	public ScriptException(String message, char character, int position)
	{
		super(message + " (with character '" + character + "' at position " + position + ")");
	}
	
	/**
	 * Create a new script exception.
	 * @param message the message
	 */
	public ScriptException(String message)
	{
		super(message);
	}
	
	public static final long serialVersionUID = 1;
}
