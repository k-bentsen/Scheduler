/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduler;

/**
 *
 * @author Kyle
 */
public class Time
{
	private int hour; // ranges from 0 to 23
	private int minute; // ranges from 0 to 3, representing increments of 15

	Time()
	{
		hour = 0;
		minute = 0;
	}

	Time(int theHour, int theMinute)
	{
		setHour(theHour);
		setMinute(theMinute);
	}

	public void setHour(int theHour)
	{
		if(theHour >= 0 && theHour <= 23) 
                    hour = theHour;
                else
                    hour = 0;
	}

	public void setMinute(int theMinute)
	{
		if(theMinute >= 0 && theMinute <= 3) 
                    minute = theMinute;
                else
                    minute = 0;
	}

	public int getHour()
	{
		return hour;
	}

	public int getMinute()
	{
		return minute;
	}
        public void increment()
        {
            if(minute == 3)
            {
                if(hour == 23)
                    hour = 0;
                else
                    hour++;
            }
            else
                minute++;
        }
        @Override
	public String toString()
	{
		int tempMin = minute * 15;
		return String.format("%d:%d", hour, tempMin);
	}

	public String toUniversalString()
	{
		int tempMin = minute * 15, tempHour = hour;
		if(hour > 12)
		{
			tempHour -= 12;
			return String.format("%d:%dPM", tempHour, tempMin);
		}
		else
			return String.format("%d:%dAM", tempHour, tempMin);
	}
}