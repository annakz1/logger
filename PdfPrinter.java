package logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PdfPrinter implements LogPrinter{

	@Override
	public void print(LogMessage logMessage, Path fileDestination) throws InvalidPasswordException, IOException {
		System.out.println("Inside PdfPrinter::print() method.");

		Path fileName = Paths.get(fileDestination.toString(), "log.pdf");

		PDDocument doc;
		File file = new File(fileName.toString());
		if (file.isFile()) {
			//Loading an existing document 
			doc = PDDocument.load(file); 
		}else{
			//Creating a new document 
			doc = new PDDocument();
		}

		PDPage page = new PDPage();
		doc.addPage(page);

		PDPageContentStream content;

		content = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, false);
		content.beginText();
		content.setFont(PDType1Font.COURIER, 26);
		content.showText(logMessage.toString());
		content.endText();
		content.close();

		doc.save(fileName.toString());
		doc.close();
	}

}
