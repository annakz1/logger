package logger.Test;

import logger.LogLevel;
import logger.Logger;

public class MyRunnable implements Runnable{
	private Thread thread;
	private String threadName;
	private Logger logger;

	public MyRunnable(String name, Logger myLogger) {
		this.threadName = name;
		this.logger = myLogger;
		System.out.println("Creating " +  threadName );
	}

	public void run() {
		System.out.println("Running " +  threadName );
		for(int i = 4; i > 0; i--) {
			System.out.println("Thread: " + threadName + ", " + i);
			// call the logger and log message
			logger.logMessage("1", LogLevel.INFO);
			logger.logMessage("2", LogLevel.INFO);
			logger.logMessage("3", LogLevel.INFO);
			logger.logMessage("4", LogLevel.INFO);
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start() {
		System.out.println("Starting " +  threadName );
		if (thread == null) {
			thread = new Thread (this, threadName);
			thread.start();
		}
	}
}

