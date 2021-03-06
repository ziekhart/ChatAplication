package Utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import Window.Window_Console;

public class Logger{

	private static File log;
	private static PrintWriter printWriter;
	private static Window_Console console;
	private static String prefix;
	private static boolean consoleEnabled = true;
	private static boolean loggerAlive = false;
	private static boolean saveLogs = true;

	private static void setup(){
		prefix = Main.Main.serverHost ? "server" : "client";
		loggerAlive = true;
		if(saveLogs){
			if(!new File("logs").exists())new File("logs").mkdir();
			log = new File("logs/" + prefix + ' ' + getDateAndTimeLogName() + ".log");
			try {
				log.createNewFile();
			} catch (IOException e) {
				javax.swing.JOptionPane.showMessageDialog(null, "Contact administrator!\n" + e.getMessage() + "\n" + e.getLocalizedMessage());
			}
			try {
				printWriter = new PrintWriter(log);
			} catch (IOException e) {
				javax.swing.JOptionPane.showMessageDialog(null, "Contact administrator!\n" + e.getMessage() + "\n" + e.getLocalizedMessage() + e.getStackTrace());
			}
		}
		setupConsole();
	}
	
	public static void disable(){
		close();
		console.setVisible(false);
		console.getFrame().dispose();
		console = null;
		consoleEnabled = false;
		/*
		 * 
		 * REMOVE THE FOLLOWING LINE ON RELEASE
		 * 
		 */
		System.exit(0);
	}

	public static void setupConsole(){
		if(!loggerAlive)setup();
		if(!Main.Main.nogui)console = new Window_Console();
	}

	public static void close(){
		printWriter.close();
	}

	public static void logError(String msg){
		writeToLog("[ERROR] " + msg);
	}

	public static void logInfo(String msg){
		writeToLog("[INFO] " + msg);
	}

	public static void logSevere(String msg){
		writeToLog("[SEVERE] " + msg);
	}

	private static void writeToLog(String msg){
		if(!consoleEnabled)return;
		if(!loggerAlive)setup();
		if(saveLogs)printWriter.println(getDateAndTime() + " " + msg);
		System.out.println(getDateAndTime() + " " + msg);
	}

	public static String getDateAndTime(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDateAndTimeLogName(){
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getTime(){
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getFileName(){
		if(!loggerAlive)setup();
		return log.getName();
	}

	public static void setConsoleVisible(boolean b) {
		if(!loggerAlive)setup();
		console.setVisible(b);
	}

	public static void showWarning(String msg){
		JOptionPane.showMessageDialog(null, msg, "Warning", JOptionPane.WARNING_MESSAGE);
	}

	public static void showMessage(String msg){
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.PLAIN_MESSAGE);
	}
}