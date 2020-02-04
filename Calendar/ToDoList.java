import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;
import javax.swing.JLabel;

public class ToDoList {

	private JFrame frame;
	JLabel lblDay;
	
	/**
	 * Create the application.
	 */
	public ToDoList() {
		initialize();
	}

	public boolean checkVisibility()
	{
		return frame.isVisible();
	}
	
	public void setVisible(boolean vis)
	{
		frame.setVisible(vis);
	}
	
	public void setDayLabel(String day)
	{
		lblDay.setText(day);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 350);
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(22, 246, 433, 20);
		frame.getContentPane().add(editorPane);
		

		DefaultListModel listModel;
		listModel = new DefaultListModel();
		JList list = new JList(listModel);
		//list.setBounds(22, 34, 425, 203);
		frame.getContentPane().add(list);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(22, 34, 425, 203);
		frame.getContentPane().add(scrollPane);
		
		
		JButton btnAddTask = new JButton("Add Task");
		btnAddTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.addElement(editorPane.getText());
			}
		});
		btnAddTask.setBounds(258, 277, 89, 23);
		frame.getContentPane().add(btnAddTask);
		
		JButton btnDeleteTask = new JButton("Delete Task");
		btnDeleteTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex != -1) {
					listModel.remove(selectedIndex);
				}
			}
		});

		btnDeleteTask.setBounds(22, 277, 89, 23);
		frame.getContentPane().add(btnDeleteTask);
		
		
		
		JButton btnEd = new JButton("Edit Task");
		btnEd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = list.getSelectedIndex();
				if (selectedIndex != -1) {
					listModel.setElementAt(editorPane.getText(), selectedIndex);					
				}
				
			}
		});
		btnEd.setBounds(138, 277, 89, 23);
		frame.getContentPane().add(btnEd);
		
		
		
		
		JButton btnExport = new JButton("Export");
		btnExport.setBounds(371, 277, 89, 23);
		frame.getContentPane().add(btnExport);
		
		
		lblDay = new JLabel("Day:");
		lblDay.setBounds(22, 11, 48, 14);
		frame.getContentPane().add(lblDay);
		
	    frame.setVisible(true);
	}
}
