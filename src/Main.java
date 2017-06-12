import java.io.IOException; 
import org.apache.pdfbox.pdmodel.PDDocument;
import ser.pdfbox.pdfDocument;
import ser.pdfbox.pdfTextExtractor;
import java.util.List;
import java.util.HashMap;

public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println("Initializing...");
        
        pdfTextExtractor pdfText = new pdfTextExtractor();
        
        List<String> fileNames = pdfText.getFileNamesFromFolder("resource");
        System.out.println("Total number of files: " + fileNames.size());
        for(int i = 0; i <fileNames.size();i++) {
            System.out.println("Starting - " + fileNames.get(i));
            List<String> parsedRawText = pdfText.loadPdfAndExtractText(fileNames.get(i));     
            String finalFileName = pdfText.writeRawTextToFile(parsedRawText,fileNames.get(i));
            System.out.println("Finished - " + finalFileName);
        }
        
        
        
        //HashMap<Integer,HashMap<Integer,String>> pageSegments = pdfText.getSegmentedText(parsedRawText);
        //test and check how content is stored
        //HashMap<Integer,String> segment = pageSegments.get(180);
        //for(int i =0; i <10; i++) {
        //    System.out.println(segment.get(i));    
       // }
        System.out.println("completed.");
    }
    
   
}
