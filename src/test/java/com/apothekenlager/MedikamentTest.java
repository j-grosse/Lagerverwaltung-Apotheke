package com.apothekenlager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedikamentTest {

    @Test
    void getPzn() {
        Medikament medikament = new Medikament("01234567", 100);
        assertEquals("01234567", medikament.getPzn());
    }

    @Test
    void getMenge() {
        Medikament medikament = new Medikament("01234567", 100);
        assertEquals(100, medikament.getMenge());
    }

    @Test
    void testToString() {
        String medikament = "01234567 100";
        assertEquals("01234567 100", medikament, "Output should be pzn and menge as a String");
    }
}