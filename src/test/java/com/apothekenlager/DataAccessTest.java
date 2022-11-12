package com.apothekenlager;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataAccessTest {

    @Test
    @DisplayName("load")
    void load() throws FileNotFoundException {
//        List<Medikament> medikament = new ArrayList<>();
//        assertThrows(FileNotFoundException.class, () -> {
//            DataAccess.write(medikament, "");
//        } );
    }

    @Test
    void write() {
//        List<Medikament> medikament = new ArrayList<>();
//        assertThrows(StacktracePrintingMatcher.class, () -> {
//            DataAccess.write(medikament, "test.csv");
//        } );
    }

    @Test
    void display() throws Exception {
        List<Medikament> medikamentList = new ArrayList<Medikament>();
        assertFalse(medikamentList == null);
    }

//    @Test
//    void compare(String s, String s1, String s2) {
//        assertEquals("BuchBestand.csv", "IstBestand.csv", "Absatz.pdf");
//    }

//    @Test
//    void export(String s, String s1) {
//        assertThrows(FileNotFoundException.class, () -> {
//            export("", "");
//        });
//    }
}