package com.apothekenlager;

import java.util.ArrayList;
import java.util.List;

public class LagerImpl extends DataAccess implements LagerService {

    private static LagerImpl lagerObject;

    // Singleton Design Pattern
    public static LagerImpl getInstance() {

        if (lagerObject == null) {
            lagerObject = new LagerImpl();
        }
        return lagerObject;
    }

    // AUFNEHMEN
    public void addMedikament(String pzn, int menge) throws Exception {

        // load() aufrufen, um die CSV Datei in eine Liste mit Objekten umzuwandeln
        List<Medikament> medikamentList = load("BuchBestand.csv");

        // Medikament-Objekt erzeugen und mit den vom User eingegebenen Parametern
        // "pzn" (String) und 'menge' (int) zur Liste hinzufügen
        Medikament medikament = new Medikament(pzn, menge);

        // Index mit gesuchter PZN finden
        int i = 0;
        // pzn vom jedem Medikament-Objekt abfragen
        String pznToCheck = "";
        // for loop solange die pzn nicht gleich der gesuchten ist und bis das Ende der Liste erreicht ist
        while (i < medikamentList.size() && !pznToCheck.equals(pzn)) {

            pznToCheck = medikamentList.get(i).getPzn();
            // For-Loop break, wenn eine pzn gefunden wurde, die der gesuchten
            // entspricht ODER wenn die Liste komplett durchiteriert wurde
//            if (pznToCheck.equals(pzn) || medikamentList.get(i).getPzn().equals("")) {
            if (pznToCheck.equals(pzn) || pznToCheck.equals("")) {
                break;
            }
            i++;
        }
//        for (i = 0; i < medikamentList.size() && !pznToCheck.equals(pzn); i++) {
//            System.out.println(medikamentList);
//            pznToCheck = medikamentList.get(i).getPzn();
//            if (pznToCheck.equals(pzn)) {
//                break;
//            }
//        }


        // Wenn das neu aufzunehmende Medikament doch schon vorhanden war,
        // Menge hinzuaddieren und geänderte Objektliste als CSV-Datei speichern
        if (pznToCheck.equals(pzn)) {
            int alteMenge = medikamentList.get(i).getMenge();
            int neueMenge = alteMenge + menge;
            medikamentList.get(i).setMenge(neueMenge);
            // wenn die pzn noch nicht in der Liste vorhanden ist, ein neues Objekt am Listenende hinzufügen
        } else if (!pznToCheck.equals(pzn)) {
            medikamentList.add(medikament);
        }
        // Erfolgsmeldung ausgeben und veränderte Liste in CSV-Datei speichern
        write(medikamentList, "BuchBestand.csv");
        System.out.println("Medikament wurde neu ins Lager aufgenommen");
    }


    // ANZEIGEN
    public void display(String filename) throws Exception {

        // load() aufrufen, um die CSV Datei in eine Liste mit Objekten umzuwandeln
        List<Medikament> medikamentList = load(filename);
        // Überschrift und Zeilen schreiben
        System.out.println(filename + ":\n");
        System.out.println("PZN         Stückzahl");
        for (Medikament medikament : medikamentList) {
            System.out.print(medikament);
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }


    // AUFSTOCKEN
    public int increaseCount(String pzn, int menge) throws Exception {

        List<Medikament> medikamentList = load("BuchBestand.csv");
        Medikament medikament = new Medikament(pzn, menge);
        // Index mit gesuchter PZN finden
        int i = 0;
        int neueMenge = 0;
        // pzn vom jedem Medikament-Objekt abfragen
        String pznToCheck = "";
        // for loop solange bis gesuchte pzn und pzn des aktuellen Medikament-Objekts gleich sind
        for (i = 0; i < medikamentList.size() && !pznToCheck.equals(pzn); i++) {

            pznToCheck = medikamentList.get(i).getPzn();
            if (pznToCheck.equals(pzn)) {
                break;
            }
        }
        // Wenn die pzn gefunden wurde, Menge hinzuaddieren und geänderte
        // Objektliste in CSV-Datei umwandeln
        if (pznToCheck.equals(pzn)) {

            int alteMenge = medikamentList.get(i).getMenge();
            neueMenge = alteMenge + menge;
            medikamentList.get(i).setMenge(neueMenge);
            write(medikamentList, "Buchbestand.csv");
            System.out.println("Stückzahl wurde aktualisiert.\nNeuer Bestand: " + medikamentList.get(i));
        }
        // Die aktuelle Menge zurückgeben
        return neueMenge;
    }


    // VERKAUFEN
    public int sell(String pzn, int menge) throws Exception {

        int remainingCount = increaseCount(pzn, (-1) * menge); //increaseCount() wird mit negativer Menge aufgerufen

        // zusätzlich wird die positive, verkaufte Menge in Absatz.csv gespeichert
        List<Medikament> medikamentList = load("Absatz.csv");
        Medikament medikament = new Medikament(pzn, menge);

        // Index mit gesuchter PZN finden
        int i = 0;
        // pzn vom jedem Medikament-Objekt abfragen
        String pznToCheck = "";
        boolean firstLineWritten = false;
        // Bis Dateiende so lange loopen bis gesuchte pzn dem aktuellen Objekt entspricht
        for (i = 0; i < medikamentList.size() && !pznToCheck.equals(pzn); i++) {
            pznToCheck = medikamentList.get(i).getPzn();
            if (pznToCheck.equals(pzn) || medikamentList.get(i).getPzn().equals("[]")) {
                break;
            }
        }
        // Wenn die pzn gefunden wurde, Menge hinzuaddieren
        // oder neu einfügen
        if (pznToCheck.equals(pzn)) {
            int alteMenge = medikamentList.get(i).getMenge();
            int neueMenge = alteMenge + menge;
            medikamentList.get(i).setMenge(neueMenge);
            // geänderte Objektliste in CSV-Datei umwandeln
            write(medikamentList, "Absatz.csv");
            firstLineWritten = true;
        }
        // Wenn die Liste noch kein Objekt enthält (die Datei leer ist) neues Objekt erzeugen,
        // anstatt die Menge eines vorhandenes Objektes upzudaten
        if (firstLineWritten == false) {
            Medikament medikamentNew = new Medikament(pzn, menge);
            medikamentList.add(medikamentNew);
            // geänderte Objektliste in CSV-Datei umwandeln
            write(medikamentList, "Absatz.csv");
        }
        return remainingCount;
    }


    // VERGLEICHEN
    public void compare(String filename1, String filename2, String filename) throws Exception {

        // Als Ergebnis dieser Vergleichsmethode wird die Datei Abweichungen.csv aktualisiert
        // und es wird angezeigt welche im Lager vor Ort gezählten Medikamente (Ist-Bestand.csv)
        // von denen in der Anwendung (Buchbestand.csv) abweichen

        List<Medikament> medikamentList1 = load(filename1);
        List<Medikament> medikamentList2 = load(filename2);

        List<Medikament> Abweichungen = new ArrayList<>();
        if (medikamentList1.equals(medikamentList2)) {
            System.out.println("Buchbestand entspricht dem gezählten Ist-Bestand");
        }
        // CSV-Dateiname und Header ausgeben
        if (!medikamentList1.equals(medikamentList2)) {
            System.out.println("" + filename + "\n");
            System.out.println("PZN         Stückzahl:");
        }
        // pzn der Medikament-Objekte von jedem Index der beiden Listen abfragen
        for (int i = 0; i < medikamentList1.size() && i < medikamentList2.size(); i++) {

            Medikament med1 = medikamentList1.get(i);
            Medikament med2 = medikamentList2.get(i);
            String pzn1 = med1.getPzn();
            String pzn2 = med2.getPzn();
            String menge1 = String.valueOf(med1.getMenge());
            String menge2 = String.valueOf(med2.getMenge());
            // Die pzn und in die String gecasteten mengen vergleichen
            boolean compPzn = pzn1.equals(pzn2);
            boolean compMenge = menge1.equals(menge2);
            // Objekt 'med2' ausgeben, wenn die pzn beider Objekte (Zeilen) übereinstimmen,
            // die mengen jedoch unterschiedlich sind
            if (compPzn == true && compMenge == false) {
                System.out.println(med2);
                Abweichungen.add(med2);
            }
        }
        System.out.println();
        System.out.println();
        write(Abweichungen, filename);
    }


    // LÖSCHEN
    // Medikament ganz aus BuchBestand.csv entfernen - bei Kundenbedarf noch implementierbar

}