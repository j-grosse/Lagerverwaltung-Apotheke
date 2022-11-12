package com.apothekenlager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface LagerService {

    // LOAD
    List<Medikament> load(String filename) throws FileNotFoundException;

    void write(List<Medikament> medikamentList, String filename);

    void export(String filenameSource, String filename) throws IOException;


    void addMedikament(String pzn, int menge) throws Exception;

    void display(String filename) throws Exception;

    int increaseCount(String pzn, int menge) throws Exception;

    int sell(String pzn, int menge) throws Exception;

    void compare(String filename1, String filename2, String filename) throws Exception;

}