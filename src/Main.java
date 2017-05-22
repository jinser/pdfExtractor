import java.io.IOException; 
import org.apache.pdfbox.pdmodel.PDDocument;
import ser.pdfbox.pdfDocument;
import ser.pdfbox.pdfTextExtractor;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println("Initializing...");

        //pdfDocument pdf = new pdfDocument("newdoc");
        //pdf.loadPdfAndExtractText("newdoc");
        //pdf.loadPdfAndAddText("newdoc","hello world!");
        //pdf.addPageToPDF("newdoc2",3,"newdoc3");
        //doc.addPage();
        //doc.saveChanges();
        
        pdfTextExtractor pdfText = new pdfTextExtractor("newdoc");
        System.out.println("Title is: " + pdfText.getTitle());
        System.out.println("Raw Text Extracted: " + pdfText.getRawText());
               
      
    }
}
