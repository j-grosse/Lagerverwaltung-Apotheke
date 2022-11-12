# Beschreibung
__Java Lagerverwaltung für eine Apotheke__

Mit dieser Java-Anwendung lässt sich über ein Swing GUI das Lager einer Apotheke verwalten.
Die Persistierung geschieht dabei über mehrere .csv Dateien (IstBestand.csv, BuchBestand.csv, Abweichungen.csv, Absatz.csv).

# Javadoc

<a href="https://jayd808.github.io/Lagerverwaltung_Apotheke/Lagerverwaltung_Apotheke/javadoc/com/apothekenlager/package-summary.html">javadoc öffnen</a>


# Klassendiagramm

<img height="550px" src="LagerApp Class Diagram.drawio.png">


# Anforderungen

1. Medikamente neu aufnehmen
2. vorhandene Medikamente aufstocken, wenn neue Ware angeliefert wird
3. aktuellen Bestand eines Medikaments auslesen
4. Medikamente für den Verkauf entnehmen
5. wöchenliche Lager-Inventur in Datei IstBestand.csv festhalten
6. Abgleichen von Inventurergebnis mit den Daten der Datei BuchBestand.csv 
7. Liste der Abweichungen von IstBestand und Buchbestand anzeigen
8. PDF-Export der Jahresstatistik über die verkauften Medikamente

Beispiel-Inhalt der Datei IstBestand.csv
```
pharmazentralnummer;stueckzahl
04324188;123
01126111;78
```


# Inhalte

- Entwicklungsumgebung: IntelliJ IDEA community edition
- Java 17 Standard Edition (openJDK)
- UML-Klassendiagramm
- Objektorientierte Analyse / objektorientiertes Design
- objektorientierte Programmierung
- Maven
- Design Pattern (n-tier, MVC)
- Java Interface
- Java Swing GUI
- Logging
- Unit Tests
- De-/Serialization von comma-separated values (.csv)
- PDF Export über die PDFbox Library
- Versionsverwaltung mit Git
- Github
- Javadoc
