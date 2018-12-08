package logger;

public class LogPrinterFactory {
	
	public LogPrinter getLogPrinter(FileType fileType){		
		if(fileType == FileType.TXT){
			return new TxtPrinter();

		} else if(fileType == FileType.PDF){
			return new PdfPrinter();
		}

		return null;
	}
}