import java.awt.*;
import javax.swing.*;
import java.time.ZonedDateTime;

public class HeaderPanel_Calendar
{

	public HeaderPanel_Calendar(CenterPanel_Calendar cent) 
	{
		center = cent;
		currentTime = currentTime.now();
		HeaderPanel = new JPanel();
		left = new JButton("<");
		left.addActionListener(event -> decMonth());
		right = new JButton(">");
		right.addActionListener(event -> incMonth());
		monthText = new JLabel();
		yearPanel = new JPanel();
			
		HeaderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		monthText.setText("" + currentTime.getMonth());
		
		//Year Panel
		yearPanel.setLayout(new GridLayout(1, 2));
		year = new JTextField();
		year.setText("" + currentTime.getYear());
		oldYear = currentTime.getYear();
		year.setBorder(BorderFactory.createLineBorder(Color.black));
		year.addActionListener(event -> changeYear());
		yearPanel.add(year);
		
		JPanel Arrows = new JPanel();
		Arrows.setLayout(new GridLayout(2,1));
		JButton inc = new JButton("inc");
		inc.addActionListener(event -> incYear());
		JButton dec = new JButton("dec");
		dec.addActionListener(event -> decYear());
		Arrows.add(inc);
		Arrows.add(dec);
		yearPanel.add(Arrows);
		
		//Add all to main Panel
		HeaderPanel.add(left);
		HeaderPanel.add(monthText);
		HeaderPanel.add(yearPanel);
		HeaderPanel.add(right);
		
	}

	private void changeYear()
	{
		int temp = Integer.parseInt(year.getText());
		int dif = temp - oldYear;
		int bdif = dif;
		if(dif > 0)
		{
			while(dif > 0)
			{
				incYear();
				dif--;
			}	
			int temp2 = Integer.parseInt(year.getText());
			temp2 -= bdif;
			year.setText(Integer.toString(temp2));
		}
		else if(dif < 0)
		{
			while(dif < 0)
			{
				decYear();
				dif++;
			}
			int temp2 = Integer.parseInt(year.getText());
			temp2 -= bdif;
			year.setText(Integer.toString(temp2));
		}
	}
	private void decYear()
	{
		int decCount = 12;
		while(decCount > 0)
		{
			decMonth();
			decCount--;
		}
	}
	
	private void incYear()
	{
		int incCount = 12;
		while(incCount > 0)
		{
			incMonth();
			incCount--;
		}
	}
	
	private void decMonth()
	{
		
		if(center.getCalendar().get(center.getCalendar().MONTH) == 0)
		{
			int temp = Integer.parseInt(year.getText());
			temp--;
			oldYear--;
			year.setText(Integer.toString(temp));
		}
		
		center.decCalendarMonth();
		setMonth();
		center.setCalendarGrid();
	}
	
	private void incMonth()
	{
		
		if(center.getCalendar().get(center.getCalendar().MONTH) == 11)
		{
			int temp = Integer.parseInt(year.getText());
			temp++;
			oldYear++;
			year.setText(Integer.toString(temp));
		}
		
		center.incCalendarMonth();
		setMonth();
		center.setCalendarGrid();
	}
	
	private void setMonth()
	{
		switch (center.getCalendar().get(center.getCalendar().MONTH))
		{
			case 0:
				monthText.setText("JANUARY");
				monthText.repaint();
				break;
			case 1:
				monthText.setText("FEBRUARY");
				monthText.repaint();
				break;
			case 2:
				monthText.setText("MARCH");
				monthText.repaint();
				break;
			case 3:
				monthText.setText("APRIL");
				monthText.repaint();
				break;
			case 4:
				monthText.setText("MAY");
				monthText.repaint();
				break;
			case 5:
				monthText.setText("JUNE");
				monthText.repaint();
				break;
			case 6:
				monthText.setText("JULY");
				monthText.repaint();
				break;
			case 7:
				monthText.setText("AUGUST");
				monthText.repaint();
				break;
			case 8:
				monthText.setText("SEPTEMBER");
				monthText.repaint();
				break;
			case 9:
				monthText.setText("OCTOBER");
				monthText.repaint();
				break;
			case 10:
				monthText.setText("NOVEMBER");
				monthText.repaint();
				break;
			case 11:
				monthText.setText("DECEMBER");
				monthText.repaint();
				break;
		}
	}
	
	public JPanel getHeaderPanel()
	{
		return HeaderPanel;
	}
	
	private CenterPanel_Calendar center;
	private JPanel HeaderPanel;
	private JPanel yearPanel;
	private JLabel monthText;
	private JTextField year;
	private JButton left;
	private JButton right;
	private ZonedDateTime currentTime;
	private int oldYear;
	
}
