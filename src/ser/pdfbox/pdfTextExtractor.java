package ser.pdfbox;

import java.io.IOException; 
import java.io.File;
import java.util.Calendar; 
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import org.apache.pdfbox.pdmodel.PDDocumentInformation;

import org.apache.pdfbox.pdmodel.PDPageContentStream; 
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import org.apache.pdfbox.text.PDFTextStripper;

public class pdfTextExtractor {
        
    private PDDocument _existingPdf;
    private StringBuilder _existingFileLocation = new StringBuilder();
    
    //document attributes
    //pdf document attibutes
    private String _author;
    private String _title;
    private String _creator;
    private String _subject;
    private Calendar _creationDate;
    private Calendar _modificationDate;
    private String _keywords;
    private String _rawText;
    
    private static final String RESOURCE_LOCATION = "resource/";
    private static final String PDF_FORMAT = ".pdf";
    
    //constructor
    public pdfTextExtractor() {
        
    }
    
    public pdfTextExtractor(String pdfName) throws IOException {
        loadPdfAndExtractText(pdfName);
    }
    
    public void loadPdfAndExtractText(String pdfName) throws IOException {
        updateExistingFileLocation(pdfName);
        File file = new File(_existingFileLocation.toString());
        _existingPdf = PDDocument.load(file);
                 
        int noOfPages = _existingPdf.getNumberOfPages();
        System.out.println("Num of pages: " + noOfPages);
        
        getDocInfo();
         
        PDFTextStripper pdfStripper = new PDFTextStripper();
        setRawText(pdfStripper.getText(_existingPdf));
        
        _existingPdf.close();
    }
    
    private void updateExistingFileLocation(String filename) {
        _existingFileLocation = new StringBuilder();
        _existingFileLocation.append(RESOURCE_LOCATION);
        _existingFileLocation.append(filename);
        _existingFileLocation.append(PDF_FORMAT);
    }
    
    private void getDocInfo() {
        PDDocumentInformation info = _existingPdf.getDocumentInformation();
        setAuthor(info.getAuthor());
        setTitle(info.getTitle());
        setCreator(info.getCreator());
        setSubject(info.getSubject());
        setCreationDate(info.getCreationDate());
        setModificationDate(info.getModificationDate());
        setKeywords(info.getKeywords());
    }
    
    public void setAuthor(String value) {
        _author = value;
    }
    public String getAuthor()  {
        return _author;
    }
    public void setTitle(String value) {
        _title = value;
    }
    public String getTitle()  {
        return _title;
    }
    public void setCreator(String value) {
        _creator = value;
    }
    public String getCreator()  {
        return _creator;
    }
    public void setSubject(String value) {
        _subject = value;
    }
    public String getSubject()  {
        return _subject;
    }
    public void setCreationDate(Calendar value) {
        _creationDate = value;
    }
    public Calendar getCreationDate()  {
        return _creationDate;
    }
    public void setModificationDate(Calendar value) {
        _modificationDate = value;
    }
    public Calendar getModificationDate()  {
        return _modificationDate;
    }
    public void setKeywords(String value) {
        _keywords = value;
    }
    public String getKeywords()  {
        return _keywords;
    }
    public void setRawText(String value) {
        _rawText = value;
    }
    public String getRawText() {
        return _rawText;
    }
}