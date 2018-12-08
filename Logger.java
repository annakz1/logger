package logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Logger {

	private Path fileDestination;
	private FileType fileType;
	private ConcurrentLinkedQueue<LogMessage> messageQueue; 
	LoggerThread loggerThread;
	

	public Logger(){
		Path currentPath = Paths.get(System.getProperty("user.dir"));
		this.fileDestination = currentPath;

		this.fileType = FileType.TXT;
		loggerInit();
	}

	public Logger(Path fileDestination, FileType fileType){
		this.fileDestination = fileDestination;
		this.fileType = fileType;
		loggerInit();
	}
	
	private void loggerInit(){
		this.messageQueue = new ConcurrentLinkedQueue<LogMessage>();
		
		loggerThread = new LoggerThread(messageQueue, fileType, fileDestination);
		loggerThread.start();
	}

	public void logMessage(String message, LogLevel level){
		synchronized(loggerThread){
			System.out.println("logs message");
			this.messageQueue.add(new LogMessage(message, level));
			loggerThread.notify();
		}
		
	}




}
