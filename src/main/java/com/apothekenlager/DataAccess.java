package com.apothekenlager;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import static com.apothekenlager.LagerApp.path;

public class DataAccess {

    // LADEN
    public List<Medikament> load(String filename) throws FileNotFoundException {

        List<Medikament> medikamentList = new ArrayList<Medikament>();
        File file = new File(path + filename);
        Scanner sc = new Scanner(file);
        // erste Zeile der CSV-Datei überspringen
        List<String> line = Arrays.asList(sc.nextLine().split(";")); // Zeile teilen
        // sicherstellen, dass die Liste nicht mehr leer ist und dann prüfen, ob eine Leerzeile in der CSV-Datei nach
        // den Medikamenten-Einträgen vorhanden ist. Wenn ja, dann den Ladevorgang abschließen.
        while (sc.hasNextLine()) {
            line = Arrays.asList(sc.nextLine().split(";")); // Elemente bei ";" teilen und lesbares Array erzeugen
            // pzn und menge werden aus dem Array der einzelnen Zeilen ausgelesen und in eine Liste geschrieben
                String pzn = line.get(0);
                int menge = Integer.parseInt(line.get(1));

            Medikament medikament = new Medikament(pzn, menge);
            // für jedeZeile der CSV Datei wird ein Objekt zur Liste hinzugefügt
            medikamentList.add(medikament);
        }
        sc.close(); // close file
        // Die Liste mit Medikament-Objekten wird zurückgegeben
        return medikamentList;
    }


    // SCHREIBEN
    public void write(List<Medikament> medikamentList, String filename) {

        final String DELIMITER = ";";        // Delimiters that must
        final String SEPARATOR = "\n";     // be in the CSV file to export
        final String HEADER = "pharmazentralnummer;stueckzahl";     //File header

        FileWriter file = null;
        try {
            file = new FileWriter(path + filename);
            file.append(HEADER);
            file.append(SEPARATOR); // freie Zeile nach der Überschrift

            Iterator it = medikamentList.iterator();
            while (it.hasNext()) {
                Medikament m = (Medikament) it.next();
                file.append(m.getPzn());
                file.append(DELIMITER);
                file.append(String.valueOf(m.getMenge()));
                file.append(SEPARATOR);
            }
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // EXPORTIEREN
    public void export(String filenameSource, String filename) throws IOException {

        List<Medikament> medikamentList = load("Absatz.csv");

        //Creating PDF document object
        PDDocument doc = new PDDocument();

        // Create a new blank page and add it to the document
        PDPage blankPage = new PDPage();
        doc.addPage(blankPage);
        PDPageContentStream contentStream = new PDPageContentStream(doc, blankPage);

        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 16);

        //Setting the leading
        contentStream.setLeading(14.5f);

        //Setting the position for the line
        contentStream.newLineAtOffset(50, 725);

        //Adding text in the form of string
        contentStream.showText("PZN              Stückzahl");
        contentStream.newLine();
        contentStream.showText("");
        contentStream.newLine();

        for (Medikament medikament : medikamentList) {
            String pzn = medikament.getPzn() + "        ";
            String menge = String.valueOf(medikament.getMenge());
            contentStream.showText(pzn);
            contentStream.showText(menge);
            contentStream.newLine();
        }
        //Ending the content stream
        contentStream.endText();

        //Closing the content stream
        contentStream.close();

        //Saving the document
        doc.save(new File(path + filename));

        //Closing the document
        doc.close();
        System.out.println(path + filename + " wurde gespeichert");
    }

}