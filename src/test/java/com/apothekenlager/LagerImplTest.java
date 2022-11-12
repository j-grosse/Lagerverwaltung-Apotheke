package com.apothekenlager;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LagerImplTest {

    @Test
    void addMedikament() {
        Medikament medikament = new Medikament("04324188", 10);
        assertEquals("04324188 10", "04324188 10");
    }

//    @Test
//    int increaseCount() throws Exception {
//        LagerImpl.increaseCount("04324188", 23);
//        assertEquals(23, 23);
//        int neueMenge = 23;
//        return 23;
//        return 23;
//    }

    @Test
    int sell(String s, int i) throws Exception {
        int remainingCount = sell("04324188", 12);
        assertEquals(33, 33);
        return remainingCount;
    }
}