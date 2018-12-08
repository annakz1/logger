package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TxtPrinter implements LogPrinter{

	@Override
	public void print(LogMessage logMessage, Path fileDestination) throws IOException {
		System.out.println("Inside TxtPrinter::print() method.");
		Path fileName = Paths.get(fileDestination.toString(), "log.txt");
		
		try(FileWriter fw = new FileWriter(fileName.toString(), true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			out.println(logMessage.toString());
		}

	}

}
