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

import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class pdfDocument  {
    private PDDocument _doc;
    private PDDocument _existingPdf;
    
    //pdf document attibutes
    private String _author;
    private String _title;
    private String _creator;
    private String _subject;
    private Calendar _creationDate;
    private Calendar _modificationDate;
    private String _keywords;
    
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
        PDPage newPage = new PDPage();
        _doc.addPage(newPage);
        setDocInfo();
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
         _newFileLocation = new StringBuilder();
         updateNewFileLocation(newPdfName);
         _doc = new PDDocument();
         _doc.save(_newFileLocation.toString());
         
         int noOfPagesAfter = _doc.getNumberOfPages();
         System.out.print("after: " + noOfPagesAfter);
         
         _doc.close();
         
         return _newFileLocation.toString();
    }
        
    public String savePDFAs(String pdfName, String newPdfName) throws IOException {
        return null;
    }
            
    public void saveChanges() throws IOException {
        _doc.save(_newFileLocation.toString());
        _doc.close();
    }
    
    public void loadPdf(String pdfName) throws IOException {
         updateExistingFileLocation(pdfName);
         File file = new File(_existingFileLocation.toString());
         _existingPdf = PDDocument.load(file);
                 
         int noOfPages = _existingPdf.getNumberOfPages();
         System.out.print("Num of pages: " + noOfPages);
        
        _existingPdf.close();
    }
    
    public void loadPdfAndExtractText(String pdfName) throws IOException {
         updateExistingFileLocation(pdfName);
         File file = new File(_existingFileLocation.toString());
         _existingPdf = PDDocument.load(file);
                 
         int noOfPages = _existingPdf.getNumberOfPages();
         System.out.print("Num of pages: " + noOfPages);
         
         PDFTextStripper pdfStripper = new PDFTextStripper();
         String text = pdfStripper.getText(_existingPdf);
         System.out.println(text);
         _existingPdf.close();
    }
    
    public void loadPdfAndAddText(String pdfName, String text) throws IOException {
        updateExistingFileLocation(pdfName);
         File file = new File(_existingFileLocation.toString());
         _existingPdf = PDDocument.load(file);
         
         int noOfPages = _existingPdf.getNumberOfPages();
         if(noOfPages < 1) {
             return ;
         }
         
         //retrieve page to be amended
         PDPage pageToBeAmended = _existingPdf.getPage(0);
         PDPageContentStream contentStream = new PDPageContentStream(_existingPdf, pageToBeAmended);
         
         contentStream.beginText();
         contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
         
         //add text for multiple lines
         //Setting the leading
         contentStream.setLeading(14.5f);
         //Setting the position for the line
         contentStream.newLineAtOffset(25, 725);
         
         contentStream.showText(text); 
         contentStream.newLine();
         contentStream.showText(text); 
         contentStream.newLine();
         
         contentStream.endText();
         contentStream.close();
         
         _existingPdf.save(_existingFileLocation.toString());
         _existingPdf.close();
    }
        
    private void updateExistingFileLocation(String filename) {
        _existingFileLocation = new StringBuilder();
        _existingFileLocation.append(RESOURCE_LOCATION);
        _existingFileLocation.append(filename);
        _existingFileLocation.append(PDF_FORMAT);
    }
    
    private void updateNewFileLocation(String filename) {
        _newFileLocation = new StringBuilder();
        _newFileLocation.append(RESOURCE_LOCATION);
        _newFileLocation.append(filename);
        _newFileLocation.append(PDF_FORMAT);
    }
    
    //Todo - hardcoded for now
    private void setDocInfo() {
        PDDocumentInformation info = _doc.getDocumentInformation();
        info.setAuthor("author");
        info.setTitle("title");
        info.setCreator("creator");
        info.setSubject("subject");
        Calendar cDate = new GregorianCalendar();
        cDate.set(2017,05,22);
        info.setCreationDate(cDate);
        Calendar mDate = new GregorianCalendar();
        mDate.set(2017,05,22);
        info.setModificationDate(mDate);
        info.setKeywords("key words, important stuff");
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
    
}