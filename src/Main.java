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
        List<String> parsedRawText = pdfText.loadPdfAndExtractText("ar2016");
        HashMap<Integer,HashMap<Integer,String>> pageSegments = pdfText.getSegmentedText(parsedRawText);
        //test and check how content is stored
        HashMap<Integer,String> segment = pageSegments.get(180);
        for(int i =0; i <10; i++) {
            System.out.println(segment.get(i));    
        }
        System.out.println("completed.");
    }
}
