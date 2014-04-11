package com.connorhaigh.javabrainfuck;

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
			System.out.println("-i\tSpecify the input source file");
			System.out.println("-d\tSpecify the data cache size");
			System.out.println("-s\tSpecify strict evaluation");
			
			return;
		}
	}
}
