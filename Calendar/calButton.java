import java.awt.*;
import javax.swing.*;
import java.util.*;

public class calButton extends JButton
{

	public calButton(String temp)
	{
		super(temp);
	}
	
	public void setList(ToDoList l)
	{
		list = l;
		count = 1;
	}
	
	public ToDoList getList()
	{
		return list;
	}
	
	public int getCount()
	{
		return count;
	}
	
	private ToDoList list;
	int count = 0;
	
}
