package logger;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;

public interface LogPrinter {
	void print(LogMessage logMessage, Path fileDestination) throws InvalidPasswordException, IOException;
}
