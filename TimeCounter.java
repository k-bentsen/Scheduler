/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

/**
 *
 * @author Kyle
 */
public class TimeCounter extends Time
{
	private int counter; // represents a value for a specific time

	TimeCounter()
	{
		super();
		counter = 0;
	}

	TimeCounter(int theHour, int theMinute, int count)
	{
		super(theHour, theMinute);
		counter = count;
	}

	public int getCount()
	{
		return counter;
	}

	public void add()
	{
		counter++;
	}

	public void sub()
	{
		counter--;
	}
}