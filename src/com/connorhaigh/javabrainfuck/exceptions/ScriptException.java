package com.connorhaigh.javabrainfuck.exceptions;

public class ScriptException extends Exception
{
	/**
	 * Creates a new script exception.
	 * @param message the message
	 */
	public ScriptException(String message)
	{
		super(message);
	}
	
	public static final long serialVersionUID = 1;
}
