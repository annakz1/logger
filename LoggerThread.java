package logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentLinkedQueue;

public class LoggerThread extends Thread{
	private ConcurrentLinkedQueue<LogMessage> messageQueue;
	private FileType fileType;
	private Path fileDestination;


	public LoggerThread(ConcurrentLinkedQueue<LogMessage> messageQueue, FileType fileType,
			Path fileDestination){
		this.messageQueue = messageQueue;
		this.fileType = fileType;
		this.fileDestination = fileDestination;
	}


	@Override
	public void run() {
		System.out.println("Thread started:::"+Thread.currentThread().getName());

		while(true){
			try {
				printMessage();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * The main thread function: 
	 * while the messageQueue is not empty, print the oldest message
	 * @throws InterruptedException 
	 */
	private synchronized void printMessage() throws InterruptedException{	
		while(this.messageQueue.isEmpty()){
			this.wait();
			System.out.println("Thread woken:::"+Thread.currentThread().getName());
		}
		LogMessage logMessage = this.messageQueue.poll();
		//get LogPrinter object according to the fileType
		LogPrinterFactory logPrinterFactory = new LogPrinterFactory();
		LogPrinter logPrinter = logPrinterFactory.getLogPrinter(this.fileType);

		System.out.println("prints message: " + logMessage);
		
		try {
			logPrinter.print(logMessage, this.fileDestination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}