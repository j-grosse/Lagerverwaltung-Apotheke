package com.apothekenlager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LagerApp {

    public static final String path = "src/main/java/com/apothekenlager/";
    public LagerService lagerService;

    public static void main(String[] args) throws Exception {

        // LOGGING
        Logger logger = LoggerFactory.getLogger("Logger");
        logger.info("program started\n");

        LagerService lagerService = LagerImpl.getInstance();

        JFrame jframe = new UI("LagerApp", lagerService);
        jframe.setVisible(true);
        jframe.setSize(600, 400);
        jframe.setLocation(400, 150);

//        GUI gui = new GUI(); // awt
//        gui.setVisible(true);
//        gui.setLocation(450, 200);



        // create a new connection from MySQLJDBCUtil
        try (Connection conn = MySQLJDBCUtil.getConnection()) {

            // print out a message
            System.out.println(String.format("Connected to database %s "
                    + "successfully.", conn.getCatalog()));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        String sql = "SELECT first_name, last_name, email " +
                "FROM candidates";

        try (Connection conn = MySQLJDBCUtil.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("first_name") + "\t" +
                        rs.getString("last_name")  + "\t" +
                        rs.getString("email"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        /*
        // Medikamente anzeigen
        System.out.println("\nAnzeigen von BuchBestand.csv und IstBestand.csv:\n");
        lagerService.display("BuchBestand.csv");
        lagerService.display("IstBestand.csv");

        // Medikamente ins Lager aufnehmen
        System.out.println("Medikamente ins Lager aufnehmen:");
        lagerService.addMedikament("04324188", 10);
        System.out.println();
        System.out.println();

        // Medikamente aufstocken
        System.out.println("Medikamente aufstocken:");
        lagerService.increaseCount("04324188", 23);
        System.out.println();
        System.out.println();

        // Medikamente verkaufen und BuchWerte.csv und Absatz.csv aktualisieren
        System.out.println("Medikamente verkaufen:");
        int remainingCount = lagerService.sell("04324188", 12);
        System.out.println("verbleibend: " + remainingCount);
        System.out.println();
        System.out.println();

        // Lagerbestand auf Abweichungen prüfen
        System.out.println("Buchbestand und Ist-Bestand vergleichen und Abweichungen des Ist-Bestands anzeigen:");
        lagerService.compare("BuchBestand.csv", "IstBestand.csv", "Abweichungen.csv");

        // Gesamtabsatz anzeigen
        System.out.println("bisherigen Gesamtabsatz anzeigen:");
        lagerService.display("Absatz.csv");

        // Absatz.csv als PDF exportieren
        System.out.println("Gesamtabsatz als PDF exportieren:");
        lagerService.export("Absatz.csv", "Absatz.pdf");
*/
/*

// Eingabeaufforderung

        System.out.println("Eingabeabfrage:\n");
        int eingabe = 0;
        while (eingabe > 4) {

            System.out.println("Bitte wählen Sie eine Nummer:\n");
            System.out.println("""
                    1  Medikamente ins Lager aufnehmen
                    2  Medikamente aufstocken
                    3  Medikamente verkaufen
                    4  Programm beenden""");

            Scanner sc = new Scanner(System.in);
            eingabe = sc.nextInt();

            switch (eingabe) {
                case 1:

                case 2:

                case 3:

                case 4:

                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + eingabe);
            }
        }

*/

    }
}

