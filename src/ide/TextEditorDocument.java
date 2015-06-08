package ide;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleConstants;

public class TextEditorDocument extends DefaultStyledDocument {

	private static final long serialVersionUID = 1L;
	private SimpleAttributeSet reservedWords;
	private SimpleAttributeSet keyWords;
	private SimpleAttributeSet defaultColour;
	private SimpleAttributeSet quotations;
	private SimpleAttributeSet comments;

	private boolean hasChanged;

	public TextEditorDocument() {

		// Initialize all the different styles for text highlighting.
		reservedWords = new SimpleAttributeSet();
		keyWords = new SimpleAttributeSet();
		defaultColour = new SimpleAttributeSet();
		quotations = new SimpleAttributeSet();
		comments = new SimpleAttributeSet();

		StyleConstants.setForeground(reservedWords, Color.RED);
		StyleConstants.setBold(reservedWords, true);

		StyleConstants.setForeground(keyWords, new Color(255,69,0));
		StyleConstants.setBold(keyWords, true);

		StyleConstants.setForeground(defaultColour, Color.BLACK);
		StyleConstants.setBold(defaultColour, false);

		StyleConstants.setForeground(quotations, Color.BLUE);
		StyleConstants.setBold(quotations, false);
		StyleConstants.setItalic(quotations, false);

		StyleConstants.setForeground(comments, Color.GREEN);
		StyleConstants.setBold(comments, false);
//		StyleConstants.setItalic(comments, true); 

		hasChanged = false;

	}

	public void insertString(int offset, String str, AttributeSet a) throws BadLocationException {
		super.insertString(offset, str, a);

		hasChanged = true;
//		boolean inQuotes = false;

		String text = getText(0, getLength());
		int before = findLastNonWordChar(text, offset);
		if (before < 0) before = 0;
		int after = findFirstNonWordChar(text, offset + str.length());
		int wordL = before;
		int wordR = before;

		while (wordR <= after) {
			// TODO: Fix quote colouring to handle full sentence 
//			if (inQuotes) {
//				if (text.substring(wordL, wordR).matches("[.*\"]")) {
//					setCharacterAttributes(wordL, wordR - wordL, quotations, false);
//					inQuotes = false;
//				}
//				setCharacterAttributes(wordL, wordR - wordL, quotations, false);
//				wordL = wordR;
//				wordR++;
//				continue;
//			}
			
			if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
				if (text.substring(wordL, wordR).matches("(\\W)*(" + ReservedWords.reservedWords + ")")) {
					// Only colour the word itself, not brackets, other words, etc
					while (!String.valueOf(text.charAt(wordL)).matches("[a-zA-Z]")) {
						wordL++;
					}
					setCharacterAttributes(wordL, wordR - wordL, reservedWords, false);
				} else if (text.substring(wordL, wordR).matches("(\\W)*(" + KeyWords.keyWords + ")")) {
					while (!String.valueOf(text.charAt(wordL)).matches("[a-zA-Z]")) {
						wordL++;
					}
					setCharacterAttributes(wordL, wordR - wordL, keyWords, false);                	    	            	
				} else {
					setCharacterAttributes(wordL, wordR - wordL, defaultColour, false);
				}           
//				if (text.substring(wordL, wordR).contains("\"")) {		
//					setCharacterAttributes(wordL, wordR - wordL, quotations, false);
//					inQuotes = true;
//				}
				wordL = wordR;
			}
			wordR++;
		}       
		colourComments();
	}

	//TODO: Fix comment colouring
	private void colourComments() {

		String text = "";
		try {
			text = getText(0, this.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}

		StringReader strReader = new StringReader(text);
		BufferedReader reader = new BufferedReader(strReader);

		// Iterate through each line, find those that contain "//" to color.
		String line = null;
		try {
			int position = 0;
			line = reader.readLine();
			while (line != null) {
				if (line.startsWith("//")) {
					int length = line.length();
					setCharacterAttributes(position, length, comments, false);     
				} else if (line.contains("//")) {
					int offset = line.indexOf("//");
					setCharacterAttributes(offset, line.length(), comments, false);

				}
				position += line.length()+1;
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void remove(int offset, int length) throws BadLocationException {
		super.remove(offset, length);

		hasChanged = true;

		String text = getText(0, getLength());
		int before = findLastNonWordChar(text, offset);
		if (before < 0) before = 0;
		int after = findFirstNonWordChar(text, offset);

		if (text.substring(before, after).matches("(\\W)*(" + ReservedWords.reservedWords + ")")) {
			while (!String.valueOf(text.charAt(before)).matches("[a-zA-Z]")) {
				before++;
			}
			setCharacterAttributes(before, after - before, reservedWords, false);
		} else if (text.substring(before, after).matches("(\\W)*(" + KeyWords.keyWords + ")")) {
			while (!String.valueOf(text.charAt(before)).matches("[a-zA-Z]")) {
				before++;
			}
			setCharacterAttributes(before, after - before, keyWords, false);
		} else {
			setCharacterAttributes(before, after - before, defaultColour, false);
		}

		colourComments();

	}

	private int findLastNonWordChar(String text, int index) {
		while (--index >= 0) {
			if (String.valueOf(text.charAt(index)).matches("\\W")) {
				break;
			}
		}
		return index;
	}

	private int findFirstNonWordChar(String text, int index) {
		while (index < text.length()) {
			if (String.valueOf(text.charAt(index)).matches("\\W")) {
				break;
			}
			index++;
		}
		return index;
	}

	public boolean documentHasChanged() {
		return hasChanged;
	}

	public void isSaved() {
		hasChanged = false;
	}
}
