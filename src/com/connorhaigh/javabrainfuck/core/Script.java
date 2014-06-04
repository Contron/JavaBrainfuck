package com.connorhaigh.javabrainfuck.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.connorhaigh.javabrainfuck.exceptions.EvaluationException;
import com.connorhaigh.javabrainfuck.exceptions.ScriptException;

public class Script 
{
	/**
	 * Create a new Brainfuck script.
	 * @param file the script data
	 * @param dataSize the size of the script's data
	 * @throws ScriptException if the script could not be created
	 */
	public Script(char[] script, int dataSize) throws ScriptException
	{
		if (script == null)
			throw new ScriptException("Script cannot be null");
		if (dataSize <= 0)
			throw new ScriptException("Cache data size is too small");
		
		this.script = script;
		
		this.data = null;
		this.dataSize = dataSize;
		this.dataPointer = 0;
		
		this.strict = false;
	}
	
	/**
	 * Create a new Brainfuck script.
	 * @param script the script data
	 * @throws ScriptException if the script could not be created
	 */
	public Script(char[] script) throws ScriptException
	{
		this(script, 32000);
	}
	
	/**
	 * Create a new Brainfuck script.
	 * @param script the script
	 * @throws ScriptException if the script could not be created
	 */
	public Script(String script) throws ScriptException
	{
		this(script.toCharArray());
	}
	
	/**
	 * Interpret and evaluate this script.
	 * @param input the input stream to use as standard input
	 * @param output the output stream to use as standard output
	 * @throws IOException if the input/output streams could not be read
	 * @throws EvaluationException if the script could not be evaluated
	 */
	public void evaluate(InputStream input, OutputStream output) throws IOException, EvaluationException
	{
		//start
		this.data = new char[this.dataSize];
		this.dataPointer = 0;
		
		//loop
		for (int position = 0; position < this.script.length; position++)
		{
			char token = this.script[position];
			switch (token)
			{
				case Script.INCREMENT_POINTER:
				{
					//check
					if (this.dataPointer >= this.data.length)
						throw new EvaluationException("Data pointer out of bounds - above data size", token, position);
					
					//increment pointer
					this.dataPointer++;
					
					break;
				}
				case Script.DECREMENT_POINTER:
				{
					//check
					if (this.dataPointer <= 0)
						throw new EvaluationException("Data pointer out of bounds - below zero", token, position);
					
					//decrement pointer
					this.dataPointer--;
					
					break;
				}
				case Script.INCREMENT_AT_POINTER:
				{
					//check
					if (this.data[this.dataPointer] >= Integer.MAX_VALUE)
						throw new EvaluationException("Memory data value out of bounds - too high", token, position);
					
					//increment data at pointer position
					this.data[this.dataPointer]++;
					
					break;
				}
				case Script.DECREMENT_AT_POINTER:
				{
					//check
					if (this.data[this.dataPointer] <= Integer.MIN_VALUE)
						throw new EvaluationException("Memory data value out of bounds - too low", token, position);
					
					//decrement data at pointer position
					this.data[this.dataPointer]--;
					
					break;
				}
				case Script.OUTPUT_POINTER:
				{
					//write data at pointer position to output
					output.write((char) this.data[this.dataPointer]);
					
					break;
				}
				case Script.INPUT_POINTER:
				{
					//read data at pointer position from input
					this.data[this.dataPointer] = (char) input.read();
					
					break;
				}
				case Script.START_LOOP:
				{
					//move to end of loop
					if (this.data[this.dataPointer] == 0)
					{
						//loop depth
						int depth = 1;
						while (depth > 0)
						{
							//get char
							token = this.script[++position];
							switch (token)
							{
								case Script.START_LOOP:
								{
									depth++;
									
									break;
								}
								case Script.END_LOOP:
								{
									depth--;
									
									break;
								}
							}
						}
					}
					
					break;
				}
				case Script.END_LOOP:
				{
					//move to start of loop
					if (this.data[this.dataPointer] != 0)
					{
						//loop depth
						int depth = 1;
						while (depth > 0)
						{
							//get char
							token = this.script[--position];
							switch (token)
							{
								case Script.START_LOOP:
								{
									depth--;
									
									break;
								}
								case Script.END_LOOP:
								{
									depth++;
									
									break;
								}
							}
						}
					}
					
					break;
				}
				default:
				{
					//unknown token
					if (this.strict)
						throw new EvaluationException("Unrecognised token", token, position);
				}
			}
		}
		
		//flush
		output.flush();
	}
	
	/**
	 * Sets the script to evaluate.
	 * @param script the script data
	 */
	public void setScript(char[] script)
	{
		this.script = script;
	}
	
	/**
	 * Returns the script data to evaluate.
	 * @return the script data
	 */
	public char[] getScript()
	{
		return this.script;
	}
	
	/**
	 * Sets the size of the data cache for this script.
	 * @param dataSize the data size
	 */
	public void setDataSize(int dataSize)
	{
		this.dataSize = dataSize;
	}
	
	/**
	 * Returns the size of the data cache for this script.
	 * @return the size of the data cache
	 */
	public int getDataSize()
	{
		return this.dataSize;
	}
	
	/**
	 * Sets if this script should be strict when evaluating (and throw exceptions when unknown tokens are found).
	 * @param strict if this script should be strict
	 */
	public void setStrict(boolean strict)
	{
		this.strict = strict;
	}
	
	/**
	 * Returns if this script is strict when evaluating.
	 * @return if this script is strict
	 */
	public boolean getStrict()
	{
		return this.strict;
	}
	
	public static final char INCREMENT_POINTER = '>';
	public static final char DECREMENT_POINTER = '<';
	public static final char INCREMENT_AT_POINTER = '+';
	public static final char DECREMENT_AT_POINTER = '-';
	public static final char OUTPUT_POINTER = '.';
	public static final char INPUT_POINTER = ',';
	public static final char START_LOOP = '[';
	public static final char END_LOOP = ']';
	
	private char[] script;
	
	private char[] data;
	private int dataSize;
	private int dataPointer;
	
	private boolean strict;
}
