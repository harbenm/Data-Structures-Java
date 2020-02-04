import java.awt.*;
import javax.swing.*;
import java.time.ZonedDateTime;

public class FooterPanel_Calendar

{

	public FooterPanel_Calendar() 
	{
		Footer = new JPanel();
		currentTime = currentTime.now();
		FooterText = new JLabel();
		
		Footer.setLayout(new GridLayout(1,1));
		FooterText.setText("Today: " + currentTime.getMonth() + " " + currentTime.getDayOfMonth() + ", " + currentTime.getYear());
		FooterText.setForeground(Color.BLUE);
		Footer.add(FooterText);
	}
	
	public JPanel getFooter()
	{
		return Footer;
	}
	
	private JPanel Footer;
	private JLabel FooterText;
	private ZonedDateTime currentTime;

}
