
package ser.pdfbox;

import java.io.IOException; 
import java.io.File;
import java.util.Calendar; 
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.File;

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
    private static final String TEXT_OUTPUT_LOCATION ="output/";
    private static final String PDF_FORMAT = ".pdf";
    
    //constructor
    public pdfTextExtractor() {
        
    }
    
    public List<String> loadPdfAndExtractText(String pdfName) throws IOException {
        updateExistingFileLocation(pdfName);
        File file = new File(_existingFileLocation.toString());
        _existingPdf = PDDocument.load(file);
                 
        int noOfPages = _existingPdf.getNumberOfPages();
        System.out.println("Num of pages: " + noOfPages);
        
        getDocInfo();
        List<String> pagesText = new ArrayList<String>();
        for(int i = 0; i < noOfPages; i++) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            pdfStripper.setStartPage(i);
            pdfStripper.setEndPage(i+1);
            pagesText.add(pdfStripper.getText(_existingPdf));
            
            pagesText.add("-------------------------------------Page Number: " + i + "-------------------------------------\n");
        }
        System.out.println("completed loadPdfandExtractText Function");
        _existingPdf.close();
        return pagesText;
    }
    
    public String writeRawTextToFile(List<String> text, String outputFileName) throws IOException {
        FileOutputStream output = null;
		ObjectOutputStream objOutputStream = null;
		StringBuilder fileNameLocation =  new StringBuilder(TEXT_OUTPUT_LOCATION);
		fileNameLocation.append(outputFileName);
		fileNameLocation.append(".txt"); System.out.println(fileNameLocation.toString());
        try {
            output = new FileOutputStream(fileNameLocation.toString());
            objOutputStream = new ObjectOutputStream(output);
            objOutputStream.writeObject(text);
        } catch (IOException e) {
            System.out.println("write to txt file error: " + e);
        }
        return fileNameLocation.toString();
    }
    
    public HashMap<Integer,HashMap<Integer,String>> getSegmentedText(List<String> rawText) {
        HashMap<Integer,HashMap<Integer,String>> pagesAndSegments = new HashMap<Integer,HashMap<Integer,String>>();
        for(int i =0; i < rawText.size(); i++) {
            List<String> pageSegments = parseRawText(rawText.get(i));
            HashMap<Integer,String> segmentsForSinglePage = new HashMap<Integer,String>();
            for(int j = 0; j < pageSegments.size();j++) {
                segmentsForSinglePage.put(j,pageSegments.get(j));
            }
            pagesAndSegments.put(i,segmentsForSinglePage);
        }
        return pagesAndSegments;
    }
    
     public List<String> getFileNamesFromFolder(String folderLocation) {
        List<String> fileNames = new ArrayList<String>();
        File[] files = new File(folderLocation).listFiles();

        for(int i = 0; i < files.length;i++) {
            if(files[i].isFile()) {
                //remove file types and only store file names
                if(files[i].getName().indexOf(".")>0) {
                    fileNames.add(files[i].getName().substring(0,files[i].getName().indexOf(".")));
                }
            }
        }
        return fileNames;
    }
    
    private void updateExistingFileLocation(String filename) {
        _existingFileLocation = new StringBuilder();
        _existingFileLocation.append(RESOURCE_LOCATION);
        _existingFileLocation.append(filename);
        _existingFileLocation.append(PDF_FORMAT);
    }
    
    private List<String> parseRawText(String raw) {
        Scanner scan = new Scanner(raw).useDelimiter("\n");
        List<String> rawTextSegments = new ArrayList<String>();
        while(scan.hasNext()){
            rawTextSegments.add(scan.next());
        }
        return rawTextSegments;
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