import java.awt.*;
import javax.swing.*;
import java.util.*;

public class CenterPanel_Calendar 
{

	public CenterPanel_Calendar() 
	{
		//Main calendar holds current month and day
		//Copy calendar is used to increment/decrement days freely. (maybe i should of used an iterator...)
		myCalendar = new GregorianCalendar();
		copyCalendar = (GregorianCalendar)myCalendar.clone();
		centerPanel = new JPanel();
		
		//The days of the week panel
		JPanel calendarPanel = new JPanel();
		calendarPanel.setLayout(new GridLayout(1, 7));
		setCalendarDays(calendarPanel);
		calendarPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		calendarPanel.setBackground(Color.LIGHT_GRAY);
		
		//The actual days panel
		calendar = new JPanel();
		calendar.setLayout(new GridLayout(0, 7));
		setCalendarGrid();
		
		//adding them all to the overall panel
		centerPanel.setLayout(new BorderLayout());
		centerPanel.add(calendarPanel, BorderLayout.NORTH);
		centerPanel.add(calendar, BorderLayout.CENTER);
	}


	/**
	 * This creates the Sun -> Sat portion of the calendar
	 * @param calendarPanel
	 */
	public void setCalendarDays(JPanel calendarPanel)
	{
		for(int i = 0; i < 7; i++)
		{
			JLabel day = new JLabel(Days[i]);
			day.setBackground(Color.LIGHT_GRAY);
			day.setForeground(Color.BLUE);
			calendarPanel.add(day);
		}
	}
	
	/**
	 * This decrements the current month when you click on the < button.
	 */
	protected void decCalendarMonth()
	{
		myCalendar.set(myCalendar.MONTH, myCalendar.get(myCalendar.MONTH)-1);
	}
	
	/**
	 * This increments the current month when you click on the > button.
	 */
	protected void incCalendarMonth()
	{
		myCalendar.set(myCalendar.MONTH, myCalendar.get(myCalendar.MONTH)+1);
	}
	
	/**
	 * This opens the to-do list for that day (a day outside the current month)
	 * @param day
	 */
	protected void openOtherMonthList(calButton day)
	{
		day.setForeground(Color.RED);
		if(day.getCount() == 0)
		{
			ToDoList temp = new ToDoList();	
			temp.setDayLabel("Day: " + day.getText());
			day.setList(temp);
		}
		else if(day.getList().checkVisibility() == false)
		{
			day.getList().setVisible(true);
		}
			
		day.setForeground(Color.LIGHT_GRAY);
	}
	
	/**
	 * This opens the to-do list for that day(a day in the current month)
	 * @param day
	 */
	protected void openThisMonthList(calButton day)
	{
		day.setForeground(Color.RED);
		if(day.getCount() == 0)
		{
			ToDoList temp = new ToDoList();	
			temp.setDayLabel("Day: " + day.getText());
			day.setList(temp);
		}
		else if(day.getList().checkVisibility() == false)
		{
			day.getList().setVisible(true);
		}
		day.setForeground(Color.BLACK);
	}
	
	/**
	 * This creates the buttons that are displayed as 1 to 31. Also accounts for empty spaces.
	 */
	protected void setCalendarGrid()
	{
		calendar.removeAll();
		copyCalendar.set(myCalendar.get(myCalendar.YEAR), myCalendar.get(myCalendar.MONTH), 1);
		int tempMonth = copyCalendar.get(copyCalendar.MONTH);
		while(copyCalendar.get(copyCalendar.DAY_OF_WEEK) > 1)
		{
			copyCalendar.set(copyCalendar.DATE, (copyCalendar.get(copyCalendar.DATE) - 1));
		}
		while(copyCalendar.get(copyCalendar.MONTH) <= tempMonth && copyCalendar.get(copyCalendar.YEAR) == myCalendar.get(myCalendar.YEAR)
				|| copyCalendar.get(copyCalendar.MONTH) > tempMonth && copyCalendar.get(copyCalendar.YEAR) < myCalendar.get(myCalendar.YEAR))
		{
			if(copyCalendar.get(copyCalendar.MONTH) < tempMonth || copyCalendar.get(copyCalendar.YEAR) < myCalendar.get(myCalendar.YEAR))
			{
				String temp = Integer.toString(copyCalendar.get(copyCalendar.DATE));
				calButton day = new calButton(temp);
				day.addActionListener(event -> openOtherMonthList(day));
				day.setBackground(Color.WHITE);
				day.setForeground(Color.LIGHT_GRAY);
				day.setBorder(BorderFactory.createEmptyBorder());
				calendar.add(day);
				copyCalendar.set(copyCalendar.DATE, (copyCalendar.get(copyCalendar.DATE)+1));
			}
			else
			{
			String temp = Integer.toString(copyCalendar.get(copyCalendar.DATE));
			calButton day = new calButton(temp);
			day.addActionListener(event -> openThisMonthList(day));
			day.setBackground(Color.WHITE);
			day.setBorder(BorderFactory.createEmptyBorder());
			calendar.add(day);
			copyCalendar.set(copyCalendar.DATE, (copyCalendar.get(copyCalendar.DATE)+1)); //1,1,2019
			}
		}
		int extraDays = 7 - copyCalendar.get(copyCalendar.DAY_OF_WEEK);
		while(extraDays >= 0)
		{
			if(copyCalendar.get(copyCalendar.MONTH) > tempMonth || copyCalendar.get(copyCalendar.YEAR) > myCalendar.get(myCalendar.YEAR))
			{
				String temp = Integer.toString(copyCalendar.get(copyCalendar.DATE));
				calButton day = new calButton(temp);
				day.addActionListener(event -> openOtherMonthList(day));
				day.setBackground(Color.WHITE);
				day.setForeground(Color.LIGHT_GRAY);
				day.setBorder(BorderFactory.createEmptyBorder());
				calendar.add(day);
				copyCalendar.set(copyCalendar.DATE, (copyCalendar.get(copyCalendar.DATE)+1));
			}
			extraDays--;
		}
	}
	
	/**
	 * Used by the calendarFrame to grab the panel
	 * @return
	 */
	public JPanel getCenterPanel()
	{
		return centerPanel;
	}
	
	/**
	 * used by the headerPanel to grab the calendar and change months/years accordingly.
	 * @return
	 */
	public Calendar getCalendar()
	{
		return myCalendar;
	}
	
	private JPanel centerPanel;
	private JPanel calendar;
	private Calendar myCalendar;
	private Calendar copyCalendar;
	private String[] Days = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
}
