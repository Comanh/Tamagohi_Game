package ua.ghost.guitamagochi;

import java.awt.Font;

import javax.swing.JTextPane;

import ua.ghost.mylibrary.Log;

public class Game {

	private static JTextPane txt=null;
	private static final String LOG_TAG="Состояние игры";
	
	public static final Font defFont = new Font("Verdana", Font.PLAIN, 12);
	
	public static void setOutput(JTextPane output){
		txt = output;
	}
	
	public static void writeToLog(String message){
		
		if(txt==null){
			Log.e(LOG_TAG, "Система вывода не готова");
			return;
		}
		message = Log.time()+" -> "+message+System.lineSeparator();
		txt.setText(txt.getText()+message);
	}
	
	public static int getRnd(int min, int max){
		int rnd= (int)(min + Math.random()*(max-min+1));
		return rnd;	
		
	}
	
}
