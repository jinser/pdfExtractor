package ser.pdfbox;

import java.io.IOException; 
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

public class pdfDocument  {
    private PDDocument _doc;
    private PDDocument _existingPdf;
    
    private static final String RESOURCE_LOCATION = "resource/";
    private static final String PDF_FORMAT = ".pdf";
    
    private StringBuilder _existingFileLocation = new StringBuilder();
    private StringBuilder _newFileLocation = new StringBuilder();
    
    //constructors
    
    public pdfDocument() throws IOException {
        
    }
    //create new empty pdf 
    public pdfDocument(String fileName) throws IOException {
        _doc = new PDDocument();
        updateNewFileLocation(fileName);
        
        _doc.save(_newFileLocation.toString());
        _doc.close();
    }
    
    //load existing pdf, add blank pages, save as a new pdf
    public String addPageToPDF(String pdfName, int numOfPages, String newPdfName) throws IOException {
        //get pdf first
        updateExistingFileLocation(pdfName);
         File file = new File(_existingFileLocation.toString());
         _existingPdf = PDDocument.load(file);
         int noOfPages = _existingPdf.getNumberOfPages();
         System.out.print("before: " + noOfPages);
         
         //add pages
         while(numOfPages > 0) {
            PDPage newPage = new PDPage();
            _existingPdf.addPage(newPage);
            numOfPages--;
         }
         _existingPdf.close();
         
         updateNewFileLocation(newPdfName);
         _doc = new PDDocument();
         _doc.save(_newFileLocation.toString());
         
         int noOfPagesAfter = _doc.getNumberOfPages();
         System.out.print("after: " + noOfPagesAfter);
         
         _doc.close();
         
         return _newFileLocation.toString();
    }
    
    public String savePDFAs(String pdfName, String newPdfName) throws IOException {
        return null
    }
            
    public void saveChanges() throws IOException {
        _doc.save(_newFileLocation.toString());
        _doc.close();
    }
    
    private void updateExistingFileLocation(String filename) {
        _existingFileLocation.append(RESOURCE_LOCATION);
        _existingFileLocation.append(filename);
        _existingFileLocation.append(PDF_FORMAT);
    }
    
    private void updateNewFileLocation(String filename) {
        _newFileLocation.append(RESOURCE_LOCATION);
        _newFileLocation.append(filename);
        _newFileLocation.append(PDF_FORMAT);
    }
    
}