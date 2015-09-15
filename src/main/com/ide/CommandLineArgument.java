package main.com.ide;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

/**
 * Allows users to add custom arguments to the command line
 * when compiling their project.
 * 
 * @author Mike Nowicki
 *
 */
public class CommandLineArgument extends JMenuItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Properties properties;
	
	public CommandLineArgument(Properties properties) {
		super("Set Command Line Arguments");
		this.properties = properties;
		addActionListener(new MenuItemListener());
	}

	/**
	 * Controls the action when the menu item is selected.
	 * 
	 * @author Mike Nowicki
	 *
	 */
	private class MenuItemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			Runnable task = new Runnable(){
				@Override
				public void run() {
					new ArgumentWindow();
				}
			}; 
			EventQueue.invokeLater(task);
		}
	}
	
	/**
	 * Basic frame to present to the user. Provides a text area
	 * to add command line arguments.
	 * 
	 * @author Mike Nowicki
	 *
	 */
	private class ArgumentWindow extends JFrame {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private JTextPane arguments;
		private JButton save;
		private JButton cancel;

		public ArgumentWindow() {
			super("Set Arguments");
			
			arguments = new JTextPane();
			save = new JButton("Save Arguments");
			cancel = new JButton("Cancel");
			
			loadWindow();			
		}
		
		private void loadWindow() {
			
			setSize(450, 140);
			setLayout(null);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			JLabel title = new JLabel("Set Arguments:");
			title.setBounds(15, 10, 250, 25);
			this.getContentPane().add(title);
			
			arguments.setBounds(15, 40, 415, 25);
			arguments.setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createLineBorder(Color.LIGHT_GRAY,1,true)));
			this.add(arguments);
			
			save.setBounds(75, 80, 150, 25);
			save.setBackground(new Color(252, 252, 252));	
			save.addActionListener(new SaveListener(this,properties));
			
			cancel.setBounds(255, 80, 100, 25);
			cancel.setBackground(new Color(239, 54, 54).brighter());
			cancel.addActionListener(new CancelListener(this));
			
			this.add(save);
			this.add(cancel);
			
			arguments.setText(properties.getCommandArguments());

			setAlwaysOnTop(true);
			setResizable(false);
			setVisible(true);
						
		}
		
		public JTextPane getArguments() {
			return arguments;
		}
		
	}
	
	/**
	 * Class that takes the text and adds it to the project properties
	 * 
	 * @author Mike Nowicki
	 *
	 */
	private class SaveListener implements ActionListener {
		
		Properties properties;
		ArgumentWindow window;
		
		public SaveListener(ArgumentWindow frame, Properties properties) {
			this.properties = properties;
			this.window = frame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			properties.clearArguments();
			
			String args = window.getArguments().getText();
			// No arguments, do nothing
			if (args.length() != 0) {
				properties.addCommandLineArgument(args);
			}
			window.dispose();
		}
		
	}
	
	/**
	 * Basic class to dispose frame
	 * 
	 * @author Mike Nowicki
	 *
	 */
	private class CancelListener implements ActionListener {

		ArgumentWindow window;
		
		public CancelListener(ArgumentWindow window) {
			this.window = window;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();
		}
	}
	
}
