package sokodan.basic.Info;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import sokodan.LevelStatusNow.CurrentStatus;

/**
 * This method is for printing out message
 * 
 * @author Kangle Yuan
 *
 */
public class GameLogger extends Logger {
	/**
	 * getter for date format
	 * 
	 * @param nothing
	 * @return m_dateFormat format for date
	 */
	public DateFormat getM_dateFormat() {
		return m_dateFormat;
	}

	/**
	 * getter for calendar object
	 * 
	 * @param nothing
	 * @return m_calendar calendar variable
	 */
	public Calendar getCalendar() {
		return m_calendar;
	}

	/**
	 * setter for calendar object
	 * 
	 * @return nothing
	 * @param m_calendar calendar variable
	 */
	public void setCalendar(Calendar m_calendar) {
		this.m_calendar = m_calendar;
	}

	/**
	 * getter for logger object
	 * 
	 * @param nothing
	 * @return m_logger logger
	 */
	public static Logger getM_logger() {
		return m_logger;
	}

	/**
	 * setter for logger object
	 * 
	 * @return nothing
	 * @param m_logger logger
	 */
	public static void setM_logger(Logger m_logger) {
		GameLogger.m_logger = m_logger;
	}

	/**
	 * setter for date format
	 * 
	 * @return nothing
	 * @param m_dateFormat format for date
	 */
	public void setDateFormat(String format) {
		this.m_dateFormat = new SimpleDateFormat(format);
	}

	private DateFormat m_dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private Calendar m_calendar = Calendar.getInstance();
	private static Logger m_logger = Logger.getLogger("GameLogger");

	/**
	 * This constructor is to initialise logger.
	 * 
	 * @param nothing
	 * @return nothing
	 * @throws IOException
	 */
	public GameLogger() throws IOException {
		super("GameLogger", null);

		File directory = new File(System.getProperty("user.dir") + "/" + "logs");
		directory.mkdirs();

		FileHandler fh = new FileHandler(directory + "/" + CurrentStatus.GAME_NAME + ".log");
		m_logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		fh.setFormatter(formatter);
	}

	/**
	 * get sting of date result
	 * 
	 * @param nothing
	 * @return the string of date
	 */
	public String getdate() {
		return m_dateFormat.format(m_calendar.getTime());
	}

	/**
	 * 
	 * source code is
	 * 
	 * package sample;
	 * 
	 * import java.io.File; import java.io.IOException; import java.text.DateFormat;
	 * import java.text.SimpleDateFormat; import java.util.Calendar; import
	 * java.util.logging.FileHandler; import java.util.logging.Logger; import
	 * java.util.logging.SimpleFormatter;
	 * 
	 * public class GameLogger extends Logger {
	 * 
	 * private static Logger logger = Logger.getLogger("GameLogger"); private
	 * DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); private
	 * Calendar calendar = Calendar.getInstance();
	 * 
	 * public GameLogger() throws IOException { super("GameLogger", null);
	 * 
	 * File directory = new File(System.getProperty("user.dir") + "/" + "logs");
	 * directory.mkdirs();
	 * 
	 * FileHandler fh = new FileHandler(directory + "/" + StartMeUp.GAME_NAME +
	 * ".log"); logger.addHandler(fh); SimpleFormatter formatter = new
	 * SimpleFormatter(); fh.setFormatter(formatter); }
	 * 
	 * private String createFormattedMessage(String message) { return
	 * dateFormat.format(calendar.getTime()) + " -- " + message; }
	 * 
	 * public void info(String message) {
	 * logger.info(createFormattedMessage(message)); }
	 * 
	 * public void warning(String message) {
	 * logger.warning(createFormattedMessage(message)); }
	 * 
	 * public void severe(String message) {
	 * logger.severe(createFormattedMessage(message)); } }
	 */

	private String createFormattedMessage(String message) {
		return m_dateFormat.format(m_calendar.getTime()) + " -- " + message;
	}

	public void info(String message) {
		m_logger.info(createFormattedMessage(message));
	}
}