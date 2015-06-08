package ide;

import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Simple line numbering listener. Has an issue when a line is edited
 * after scrolling, needs to be looked into further
 * 
 * @author Mike Nowicki
 *
 */
public class LineListener implements DocumentListener{

	private JTextPane worksheet;
	private JTextArea lines;
	
	public LineListener(JTextPane worksheet, JTextArea lines) {
		this.worksheet = worksheet;
		this.lines = lines;
	}
	
	public String getText() {
		String text = "";
		String document = worksheet.getText();
		String[] documentLines = document.split("\n");
		for (int i = 1; i <= documentLines.length+1; i++) {
			text += i + "\n";
		}		
		return text;
	}
	@Override
	public void insertUpdate(DocumentEvent e) {
		lines.setText(getText());
	}
	@Override
	public void removeUpdate(DocumentEvent e) {
		lines.setText(getText());
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		lines.setText(getText());
	}	
}
