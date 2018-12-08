package logger.Test;

import java.nio.file.Paths;

import logger.FileType;
import logger.LogLevel;
import logger.Logger;

public class TestLogger {

	public static void main(String[] args) {
		
		// Sanity check- create the default logger and print to file
		Logger defaultLogger= new Logger();
		defaultLogger.logMessage("abc", LogLevel.INFO);
		
		
		// Test with multiple threads and PDF file type
		Logger customLogger= new Logger(Paths.get(System.getProperty("user.dir")), FileType.PDF);
		
		MyRunnable runnable1 = new MyRunnable("Thread1", customLogger);
		runnable1.start();
		
		MyRunnable runnable2 = new MyRunnable("Thread2", customLogger);
		runnable2.start();
		
	}

}
