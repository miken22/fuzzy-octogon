package main.com.ide.texteditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.JTextPane;
import javax.swing.text.DefaultStyledDocument;

/**
 * Overriding JTextPane class to provide more
 * control over the behaviour of the editor
 * 
 * @author Michael Nowicki
 *
 */
public class TextEditor extends JTextPane {

	private static final long serialVersionUID = 1L;
	
	public TextEditor(DefaultStyledDocument document) {
		super(document);
		addKeyListener(new CommentHotKeyListener());
	}
	
	private class CommentHotKeyListener implements KeyListener {

		// NOT WORKING RIGHT NOW
		
		@Override
		public void keyPressed(KeyEvent event) {

			if (event.isControlDown() && event.getKeyCode() == 47){
				int position = getCaretPosition();
				String text = getText();
			
//				System.out.println(position);
						
				// Buffer the text to iterate through faster
				BufferedReader bufReader = new BufferedReader(new StringReader(text));
				int scanPosition = 0;
				try {
					String line = bufReader.readLine();
					
					while (line != null) {
						scanPosition += line.length();
						if (scanPosition >= position) {
							break;
						}
						line = bufReader.readLine();
					}
					
					if (scanPosition == 0) {
						setText(text + "//");
						return;
					}
					
					if (line.startsWith("//")) {
						line = line.substring(1, line.length());
						setCaretPosition(scanPosition - line.length());
						setText(text.substring(0, scanPosition + 2 - line.length()) +
								line +
								text.substring(scanPosition, text.length()));
					} else {
						line = "//" + line;
						setText(text.substring(0, scanPosition + 2 - line.length()) + 
								line + 
								text.substring(scanPosition, text.length()));
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
							
			}
		}
		
		/**************************************************************
		 * 
		 * Not used
		 * 
		 **************************************************************/
		
		@Override
		public void keyTyped(KeyEvent e) {		}

		@Override
		public void keyReleased(KeyEvent e) {		}
		
	}
}