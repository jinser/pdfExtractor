import java.io.IOException; 
import org.apache.pdfbox.pdmodel.PDDocument;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println("Hello World");
        PDDocument document = new PDDocument();
        
        //Saving the document
      document.save("resource/my_doc.pdf");
         
      System.out.println("PDF created");  
    
      //Closing the document  
      document.close();
    }
}
