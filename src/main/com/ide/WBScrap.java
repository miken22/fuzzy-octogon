package main.com.ide;
//package main.com.ide;
//
//import java.awt.Color;
//import java.awt.Container;
//import java.awt.Dimension;
//import java.awt.Font;
//import java.awt.GridLayout;
//import java.awt.Image;
//import java.awt.Toolkit;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.KeyEvent;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JMenu;
//import javax.swing.JMenuBar;
//import javax.swing.JMenuItem;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.JTextPane;
//import javax.swing.KeyStroke;
//import javax.swing.border.Border;
//import javax.swing.border.LineBorder;
//
//import main.com.ide.texteditor.TextEditorDocument;
//import main.com.ide.texteditor.TextLineNumber;
//import main.com.toolbar.CompileButton;
//import main.com.toolbar.OpenButton;
//import main.com.toolbar.SaveButton;
//import main.com.toolbar.listeners.CompileButtonListener;
//import main.com.toolbar.listeners.OpenButtonListener;
//import main.com.toolbar.listeners.PropertiesButtonListener;
//import main.com.toolbar.listeners.SaveFileListener;
//
///**
// * Main frame of the application, holds all buttons,
// * text editors, and output.
// * 
// * @author Mike
// *
// */
//public class WorkBench {
//
//	// Main frame components
//	private JFrame frame;
//
//	private Container mainContainer;
//	private JScrollPane mainScroll;
//	private JScrollPane outputScroll;
//	private JTextPane editor;
//	private JTextArea buildlog;
//	private JTextArea lines;
//
//	private JPanel toolbar;
//	private JButton newFileButton;
//	private CompileButton compilerButton;
//	private SaveButton saveFileButton;
//	private OpenButton openFileButton;
//
//	// Custom style for editor environment
//	private TextEditorDocument textarea;
//
//	private JMenuBar menu;
//	private JMenu file;
//	private JMenu build;
//	private JMenuItem exit;
//	private JMenuItem save;
//	private JMenuItem open;
//	private JMenuItem buildScript;
//	private JMenuItem packageLoader;
//
//	private Properties properties;
//
//	public WorkBench() {
//		frame = new JFrame("Networx Graph Editor");
//		mainContainer = frame.getContentPane();
//	
//		properties = new Properties();	
//		
//		menu = new JMenuBar();
//		file = new JMenu("File");
//		open = new JMenuItem("Load Script");
//		save = new JMenuItem("Save Script");
//		exit = new JMenuItem("Exit");
//		
//		build = new JMenu("Build Tools");
//		buildScript = new JMenuItem("Build Script");
//		packageLoader = new JMenuItem("Set Package Imports");
//		
//		lines = new JTextArea("1");	 		
//		textarea = new TextEditorDocument();
//		editor = new JTextPane(textarea);
//		
//		buildlog = new JTextArea();
//		mainScroll = new JScrollPane(editor);
//		outputScroll = new JScrollPane(buildlog);		
//		
//		toolbar = new JPanel();
//		newFileButton = new JButton();
//		openFileButton = new OpenButton();
//		saveFileButton = new SaveButton();
//		compilerButton = new CompileButton();
//	}
//	
//	/**
//	 * Initialize the main frame, set sizes and load all components
//	 */
//	public void loadWorkbench() {	
//				
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		double width = screenSize.getWidth();
//		double height = screenSize.getHeight();
//
//		// Initialize frame and add the menu items
//		frame.setLayout(new GridLayout(3, 1));
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize((int) width/2, (int)(height/1.2));
//		frame.setResizable(true);
//		frame.setLocationRelativeTo(null);
//		frame.setJMenuBar(menu);
//		mainContainer.setBackground(new Color(217, 217, 217));		
//		
//		// Load all components for the workbench
//		buildToolbar();
//		createMenuBar();	
//		loadConsoles();
//		
//		// Reveal the frame
//		frame.setVisible(true);
//		editor.requestFocus();
//
//	}
//
//	private void createMenuBar() {
//		
//		menu.setBackground(new Color(217,217,217));
//		menu.add(file);
//		file.add(open);
//		file.add(save);
//		file.add(exit);
//
//		menu.add(build);
//		build.add(buildScript);
//		build.add(new CommandLineArgument(properties));
//		build.add(packageLoader);	
//
//		save.addActionListener(new MenuListener(1));
//		open.addActionListener(new MenuListener(2));
//		exit.addActionListener(new MenuListener(3));
//		buildScript.addActionListener(new MenuListener(4));
//	
//		packageLoader.addActionListener(new PropertiesButtonListener(properties));
//		
//		buildScript.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));
//		open.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_DOWN_MASK));
//		save.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_DOWN_MASK));
//		
//	}
//	
//	private void loadConsoles() {
//
//		Border b = new LineBorder(Color.LIGHT_GRAY, 1, true);
////		JLabel lineNumber = new JLabel("1");
//
//		Font font = new Font("Normal", Font.PLAIN, 14);
////		Main font theme
////		try {
////			font = Font.createFont(0, this.getClass().getResourceAsStream("/resources/Trebuchet MS.ttf"));
////		} catch (FontFormatException|IOException e) {
////			e.printStackTrace();
////			System.exit(-1);
////		}
//
//		font = font.deriveFont(Font.PLAIN, 14);
//		
//		// Create the script area
//		editor.setEditable(true);
//		editor.setFont(font);
//		editor.setBorder(b);
//		editor.setBackground(new Color(252, 252, 252));
//		mainScroll.setBounds(2, 25, frame.getWidth()-12,  (int)(frame.getHeight()/1.4));
//		mainScroll.setBackground(new Color(240, 240, 240));
//		mainScroll.setBorder(BorderFactory.createTitledBorder(
//				BorderFactory.createLineBorder(Color.LIGHT_GRAY,1,true), "Current Script:"));
//		mainScroll.setBackground(new Color(217, 217, 217));
//		
//		lines.setBackground(Color.LIGHT_GRAY);
//		lines.setEditable(false);
//		lines.setFont(font);
//		
////		LineListener lineListener = new LineListener(editor, lines, lineNumber);
//		TextLineNumber tln = new TextLineNumber(editor);
////		editor.getDocument().addDocumentListener(lineListener);
////		editor.addKeyListener(lineListener);
////		editor.addMouseListener(lineListener);
//		
//		mainScroll.setRowHeaderView(tln);
//		mainContainer.add(mainScroll);
//
//		// To display build logs
//		buildlog.setLineWrap(true);
//		buildlog.setEditable(false);
//		buildlog.setBorder(b);
//		buildlog.setBackground(new Color(252, 252, 252));
//		buildlog.setFont(font);
//		outputScroll.setBounds(2, (mainScroll.getHeight()+25), 
//				(frame.getWidth() - 12), (int)(mainScroll.getHeight()/4));
//		outputScroll.setBackground(new Color(240, 240, 240));
//		outputScroll.setBorder(BorderFactory.createTitledBorder(
//				BorderFactory.createLineBorder(Color.LIGHT_GRAY,1,true), "Build Log:"));
//		outputScroll.setBackground(new Color(217, 217, 217));
//		mainContainer.add(outputScroll);
//		
////		lineNumber.setBounds(10, frame.getHeight()-50, 50, 25);
////		mainContainer.add(lineNumber);
//		
//	}
//
//	private void buildToolbar() {
//	
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		double width = screenSize.getWidth();
//
//		toolbar.setBounds(0, 0, (int)width, 25);
//		toolbar.setLayout(null);
//		toolbar.setBackground(new Color(217, 217, 217));
//
//		// Setup listeners
//		newFileButton.addActionListener(new NewFileListener());
//		openFileButton.addActionListener(new OpenButtonListener(editor, properties));
//		saveFileButton.addActionListener(new SaveFileListener(editor, properties));
//		compilerButton.addActionListener(new CompileButtonListener(editor, buildlog, properties));
//				
//		Image img;
//		// Empty catches are bad, mmmkkayyyy?
//		try {
//			img = ImageIO.read(getClass().getResource("/res/rsz_newfile.png"));
//			newFileButton.setIcon(new ImageIcon(img));
//		} catch (IOException ex) {
//			System.exit(-1);
//		}
//		newFileButton.setBounds(2, 0, 20, 20);
//		newFileButton.setToolTipText("New File");
//		toolbar.add(newFileButton);
//
//		try {
//			img = ImageIO.read(getClass().getResource("/res/rsz_save.png"));
//			saveFileButton.setIcon(new ImageIcon(img));
//		} catch (IOException ex) {
//			System.exit(-1);
//		}
//		saveFileButton.setBounds(22, 0, 20, 20);
//		saveFileButton.setToolTipText("Save File As");
//		toolbar.add(saveFileButton);
//
//		try {
//			img = ImageIO.read(getClass().getResource("/res/rsz_open.png"));
//			openFileButton.setIcon(new ImageIcon(img));
//		} catch (IOException ex) {
//			System.exit(-1);
//		}
//		openFileButton.setBounds(44, 0, 20, 20);
//		openFileButton.setToolTipText("Open File");
//		toolbar.add(openFileButton);
//
//		try {
//			img = ImageIO.read(getClass().getResource("/res/rsz_play.png"));
//			compilerButton.setIcon(new ImageIcon(img));
//		} catch (IOException ex) {
//			System.exit(-1);
//		}
//		compilerButton.setBounds(66, 0, 20, 20);
//		compilerButton.setToolTipText("Compile Script");
//		toolbar.add(compilerButton);		
//		
//		mainContainer.add(toolbar);
//
//	}
//
//	/**
//	 * Main menu listener for the File options
//	 * 
//	 * @author Michael
//	 *
//	 */
//	private class MenuListener implements ActionListener {
//
//		private int listenerType;
//
//		public MenuListener(int id) { 
//			this.listenerType = id;
//		}
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// Save file
//			if (listenerType == 1) {
//			
//				saveFileButton.doClick();
//				textarea.isSaved();
//			
//			} else if (listenerType == 2) {
//			// Open file, check to save first
//				if (textarea.documentHasChanged()) {
//					int dialogButton = JOptionPane.YES_NO_OPTION;
//					int dialogResult = JOptionPane.showConfirmDialog (null,
//							"Would You Like to Save your Work?","Warning",dialogButton);
//					if(dialogResult == JOptionPane.YES_OPTION){
//						saveFileButton.doClick();
//						textarea.isSaved();
//					}
//				}
//				
//				openFileButton.doClick();
//				textarea.isSaved();
//			} else if (listenerType == 3) {
//			// Exit application, check if saved first				
//				int dialogButton;
//				int dialogResult = JOptionPane.NO_OPTION;
//				
//				if (textarea.documentHasChanged()) {
//					dialogButton = JOptionPane.YES_NO_OPTION;
//					dialogResult = JOptionPane.showConfirmDialog (null,
//							"Would You Like to Save your Work?","Warning",dialogButton);
//					if(dialogResult == JOptionPane.YES_OPTION){
//						saveFileButton.doClick();
//						textarea.isSaved();
//					}
//				}
//				// If the document has not changed, and the user does not click yes then the app
//				// can exit
//				if (!textarea.documentHasChanged() || dialogResult == JOptionPane.NO_OPTION) {
//					System.exit(0);
//				}
//			// Compile script
//			} else if (listenerType == 4) {
//				compilerButton.doClick();
//			}
//		}
//	}
//	
//	/**
//	 * Trivial listener to create a new file
//	 * 
//	 * @author Michael
//	 *
//	 */
//	private class NewFileListener implements ActionListener {
//		@Override
//		public void actionPerformed(ActionEvent click) {
//			
//			if (textarea.documentHasChanged()) {
//				int dialogButton = JOptionPane.YES_NO_OPTION;
//				int dialogResult = JOptionPane.showConfirmDialog (null, 
//						"Would You Like to Save your Work?","Warning", dialogButton);
//				if(dialogResult == JOptionPane.YES_OPTION){
//					saveFileButton.doClick();
//					textarea.isSaved();
//				}
//			}
//			
//			editor.setText("");
//			saveFileButton.removeActionListener(saveFileButton.getActionListeners()[0]);
//			saveFileButton.addActionListener(new SaveFileListener(editor, properties));
//			properties.clearArguments();
//			properties.clearImports();
//		}
//	}
//}
