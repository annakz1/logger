package logger;

import java.sql.Timestamp;

public class LogMessage {
	
	String message;
	LogLevel level;
	Timestamp timestamp;

	public LogMessage(String message, LogLevel level) {
		this.message = message;
		this.level = level;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}
	
	public String toString(){
		return this.timestamp + ", " + this.level+ ": " + this.message;
	}
}
