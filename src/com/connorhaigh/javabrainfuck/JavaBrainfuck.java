package com.connorhaigh.javabrainfuck;

import com.connorhaigh.javabrainfuck.core.Script;

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
		
		//check for arguments
		if (args.length <= 0)
		{
			//usage
			System.out.println("Usage:");
			System.out.println("-i\tSpecify the input script");
			System.out.println("-d\tSpecify the data cache size");
			System.out.println("-s\tSpecify strict evaluation");
			
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
					case "-i":
					{
						input = args[++argument];
						
						break;
					}
					case "-d":
					{
						dataSize = Integer.parseInt(args[++argument]);
						
						break;
					}
					case "-s":
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
		catch (Exception ex)
		{
			//invalid arguments
			System.err.println("Invalid arguments specified");
			
			return;
		}
		
		//create script
		Script script = new Script(input);
		if (dataSize > 0)
			script.setDataSize(dataSize);
		if (strict)
			script.setStrict(strict);
		
		try
		{
			//evaluate
			System.out.println("Evaluating script...");
			System.out.println();
			
			script.evaluate(System.in, System.out);
			
			System.out.println();
			System.out.println("Evaluated script successfully");
		}
		catch (Exception ex)
		{
			System.err.println();
			System.err.println("Error during evaluation: " + ex.getMessage());
		}
	}
}
