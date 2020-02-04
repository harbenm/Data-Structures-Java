import java.awt.*;
import javax.swing.*;
import java.util.*;

public class CalendarFrame extends JFrame
{
	public CalendarFrame()
	{
		
		//Center
		CenterPanel_Calendar Center = new CenterPanel_Calendar();
		
		//Top
		HeaderPanel_Calendar Header = new HeaderPanel_Calendar(Center);
		
		//Bottom
		FooterPanel_Calendar Footer = new FooterPanel_Calendar();
		
		//Frame contains each section: Top, Middle, Bottom
		calFrame = new JFrame();
		calFrame.add(Header.getHeaderPanel(), BorderLayout.NORTH);
		calFrame.add(Center.getCenterPanel(), BorderLayout.CENTER);
		calFrame.add(Footer.getFooter(), BorderLayout.SOUTH);
		calFrame.setSize(500, 500);
	    calFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    calFrame.setVisible(true);
	}
	
	private JFrame calFrame;
}
