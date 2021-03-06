package main.com.ide;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 * Handles messages to the build log, adjusts line numbers to match
 * what is in the editor
 * 
 * @author Mike Nowicki
 *
 */
public class MessageHandler {

	/**
	 * Looks through the error message, detects if it has to do with a given line number
	 * and formats the message to represent the line it actually is in the program
	 * 
	 * @param errorMessage - The error
	 * @param worksheet - The user worksheet
	 * @param buildlog - The console to print to
	 * @throws InterruptedException
	 */
	public static void handleErrorMessage(String errorMessage, JTextPane worksheet, 
											JTextArea buildlog) throws InterruptedException {
		
		if (errorMessage.matches(".*[0-9].*")) {
			try {
				errorMessage = adjustErrorIndex(errorMessage, worksheet);
				buildlog.append(errorMessage + "\n");
				return;
			} catch (IOException e) {}
		}

		buildlog.append(errorMessage + "\n");
	}

	/**
	 * Prints the messages from the user program
	 * 
	 * @param ins - The stream of output from the user program
	 * @param buildlog - The console to write to
	 * @throws IOException
	 */
	public static void handleProgramMessaage(InputStream ins, JTextArea buildlog) throws IOException {
		String line = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(ins));
		while ((line = in.readLine()) != null) {
			buildlog.append(line + "\n");
		}
	}

	private static String adjustErrorIndex(String errorMessage, JTextPane worksheet) throws IOException {

		String[] parts = errorMessage.split(":");
		String script = worksheet.getText();
		String[] scriptLines = script.split("\n");

		File directory = new File(".UserFiles");
		
		// Get file to scan
		String errorFile = "";
		try {
			errorFile = parts[0].substring(parts[0].indexOf('/')+1,parts[0].length());			
		} catch (StringIndexOutOfBoundsException e) {
			return errorMessage;
		}
		
		File userScript = new File(directory, errorFile);
		userScript.deleteOnExit();

		FileReader reader = new FileReader(userScript);
		BufferedReader bufferedReader = new BufferedReader(reader);

		Integer initialLineNumber = Integer.valueOf(parts[1]);

		// Look through the file for the exact line of the error 
		String line = bufferedReader.readLine();
		int lineCounter = 1;
		while (lineCounter < initialLineNumber) {
			lineCounter++;
			line = bufferedReader.readLine();
		}
		
		int trimEnd = errorFile.indexOf('.');
		errorFile = errorFile.substring(0, trimEnd);

		// Scan through the script looking for the matching line and
		// report adjusted line number back to the user.
		for (int i = 0; i < scriptLines.length; i++) {
			if (scriptLines[i].trim().equals(line.trim())) {
				bufferedReader.close();
				
				if (errorFile.equals("UserScript")) {
					return "Error on Line:" + (i+1) + parts[2] + parts[3];
				} else {
					return "Error in Class " + errorFile + ":" + (i+1) + parts[2] + parts[3];
				}
			}
		}

		bufferedReader.close();
		return errorMessage;
	}
}
