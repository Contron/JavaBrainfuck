package com.connorhaigh.javabrainfuck;

import com.connorhaigh.javabrainfuck.core.Script;
import com.connorhaigh.javabrainfuck.exceptions.EvaluationException;
import com.connorhaigh.javabrainfuck.exceptions.ScriptException;

public class JavaBrainfuck 
{
	/**
	 * Main method.
	 * @param args application arguments
	 */
	public static void main(String[] args)
	{
		//header
		System.out.println("JavaBrainfuck");
		System.out.println("(C) Connor Haigh 2014");
		System.out.println();
		
		if (args.length <= 0)
		{
			//usage
			System.out.println("Usage:");
			System.out.println("\t" + JavaBrainfuck.INPUT_OPTION + "\t\tSpecify the input script");
			System.out.println("\t" + JavaBrainfuck.SIZE_OPTION + "\t\tSpecify the data cache size");
			System.out.println("\t" + JavaBrainfuck.STRICT_OPTION + "\t\tSpecify strict evaluation");
			
			return;
		}
		
		//parameters
		String input = null;
		int dataSize = 0;
		boolean strict = false;
		
		try
		{
			//loop arguments
			for (int argument = 0; argument < args.length; argument++)
			{
				switch (args[argument])
				{
					case JavaBrainfuck.INPUT_OPTION:
					{
						input = args[++argument];
						
						break;
					}
					case JavaBrainfuck.SIZE_OPTION:
					{
						dataSize = Integer.parseInt(args[++argument]);
						
						break;
					}
					case JavaBrainfuck.STRICT_OPTION:
					{
						strict = true;
						
						break;
					}
				}
			}
			
			//check arguments
			if (input == null)
				throw new Exception();
		}
		catch (Exception exception)
		{
			//invalid arguments
			System.err.println("Invalid arguments specified");
			
			return;
		}
		
		try
		{
			//create script
			System.out.println("Creating script...");
			Script script = new Script(input);
			if (dataSize > 0)
				script.setDataSize(dataSize);
			if (strict)
				script.setStrict(strict);
			
			//evaluate
			System.out.println("Evaluating script...");
			System.err.println();
			
			script.evaluate(System.in, System.out);
			
			System.err.println();
			System.out.println("Evaluated script successfully");
		}
		catch (ScriptException exception)
		{
			//creation exception
			System.err.println("Error during creation: " + exception.getMessage());
		}
		catch (EvaluationException exception)
		{
			//evaluation exception
			System.err.println("Error during evaluation: " + exception.getMessage());
		}
		catch (Exception exception)
		{
			//general exception
			System.err.println("General error: " + exception.getMessage());
		}
	}
	
	public static final String INPUT_OPTION = "-script";
	public static final String SIZE_OPTION = "-size";
	public static final String STRICT_OPTION = "-strict";
}
