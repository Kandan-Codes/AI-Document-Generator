package org.Document;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.Controllers.MainPageController;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocumentGenerator {

    //Creating  pdf document
    public void generatePDF(String content, File file) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(file);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        document.add(new Paragraph(content));
        document.close();
        MainPageController.showAlert("Success","PDF created successfully!");
    }

    //creating DOCX document
    public void generateDOCX(String content, File file) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText(content);

        FileOutputStream out = new FileOutputStream(file);
        document.write(out);
        out.close();
        document.close();
        MainPageController.showAlert("Success", "DOCX created successfully!");
    }

}
