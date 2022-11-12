package com.apothekenlager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

public class UI extends JFrame {
    private JPanel mainPanel;
    private JTextField textFieldPzn;
    private JLabel result;
    private JTextField textFieldMenge;
    private JLabel labelMenge;
    private JButton btnAdd;
    private JButton btnInc;
    private JTextArea textArea1;
    private JButton btnComp;
    private JButton btnSell;
    private JButton btnExport;
    private JScrollBar scrollBar;
    private JButton searchButton;
    private LagerService lagerService;

    // constructor
    public UI(String title, LagerService lagerService) {
        super(title);
        this.lagerService = lagerService;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

//        textArea1 = new JTextArea("Put your text here");
//        JScrollPane scrollBar=new JScrollPane(textArea1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
////        Create a window using JFrame
//        JFrame frame=new JFrame("Add scrollbar to JTextArea");
////        add created JScrollPane into JFrame
//        frame.add(scrollBar);
//        //Set default close operation for JFrame
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(scrollBar);
////        frame.pack( );

        displayFile("BuchBestand.csv");


        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pzn = textFieldPzn.getText();
                int menge = Integer.parseInt(textFieldMenge.getText());
                result.setText("Medikament neu aufgenommen");
                try {
                    lagerService.addMedikament(pzn, menge);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                displayFile("BuchBestand.csv");
            }
        });

        btnInc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pzn = textFieldPzn.getText();
                int menge = Integer.parseInt(textFieldMenge.getText());
                result.setText("Medikament hinzugefügt");

                try {
                    lagerService.increaseCount(pzn, menge);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                displayFile("BuchBestand.csv");
            }
        });

        btnComp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String pzn = textFieldPzn.getText();
//                int menge = Integer.parseInt(textFieldMenge.getText());
                result.setText("Abweichungen ermittelt");

                try {
                    lagerService.compare("BuchBestand.csv", "IstBestand.csv", "Abweichungen.csv");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                displayFile("Abweichungen.csv");
            }
        });

        btnSell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pzn = textFieldPzn.getText();
                int menge = Integer.parseInt(textFieldMenge.getText());
                result.setText("Medikament verkauft");

                try {
                    lagerService.sell(pzn, menge);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                displayFile("BuchBestand.csv");
            }
        });

        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                String pzn = textFieldPzn.getText();
//                int menge = Integer.parseInt(textFieldMenge.getText());
                result.setText("Medikamentabsatz exportiert");

                try {
                    lagerService.export("Absatz.csv", "Absatz.pdf");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                displayFile("Absatz.csv");
            }
        });


    } //end constructor


    public void displayFile(String filename) {

        List<Medikament> medikamentList;
        try {
            medikamentList = lagerService.load(filename);
        } catch (FileNotFoundException exc) {
            throw new RuntimeException(exc);
        }
        // Überschrift und Zeilen schreiben
        textArea1.setText("  " + filename + "\n\n");
        textArea1.append("PZN           Stückzahl\n\n");
        for (Medikament medikament : medikamentList) {
            textArea1.append(String.valueOf(medikament) + "\n");
        }
        textArea1.setEditable(false);
    }


}