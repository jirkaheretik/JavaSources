package cz.braza.pojistovna;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class EvidenceCSV extends Evidence {
    private File soubor;
    private static final String DEF_ODDELOVAC = ",";
    private String oddelovac;

    public EvidenceCSV(String fileName) {
        this(fileName, DEF_ODDELOVAC);
    }

    public EvidenceCSV(String fileName, String oddelovac) {
        super();
        this.oddelovac = oddelovac;
        soubor = new File(fileName);
        loadFile(soubor);
    }

    @Override
    public void ukonci() {
        try {
            Files.writeString(soubor.toPath(), Pojistenec.csvHlavicka() + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            for (Pojistenec p: vyberVsechnyPojistence())
                Files.writeString(soubor.toPath(), p.ulozDoCsv() + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException ioe) {

        }
    }

    private void loadFile(File file) {
        try {
            Scanner vstup = new Scanner(file);
            while (vstup.hasNextLine()) {
                String[] data = vstup.nextLine().trim().split(oddelovac);
                try {
                    pridejPojistence(data[0], data[1], Integer.parseInt(data[2]), data[3]);
                } catch (Exception ex) {
                } // skip errorneous record (including CSV header)
            }
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }
}
