package cz.braza.pojistovna;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 * Evidence pojištěnců na bázi CSV souborů. Pouze <b>rozšiřuje</b> třídu {@link EvidenceInMemory}
 * o schopnost načítat data (na začátku, tedy při vytváření instance) a ukládat data při korektním
 * ukončení aplikace. Díky tomu není třeba soubor neustále otevérat a zavírat s každou operací.
 */
public class EvidenceCSV extends EvidenceInMemory {
    /**
     * Objekt File odkazující na vstupní a výstupní datový soubor (CSV)
     */
    private File soubor;
    /**
     * CSV určuje jako defaultní oddělovač čárku, ta bude použita, není-li v konstruktoru řečeno jinak
     */
    private static final String DEF_ODDELOVAC = ",";
    /**
     * Oddělovač, který používáme pro oddělení jedotlivých záznamů, možno zadat v konstruktoru
     */
    private String oddelovac;

    /**
     * Konstruktor se zadáním pouze cesty k souboru
     * @param fileName Umístění souboru pro data (vstup i výstup)
     */
    public EvidenceCSV(String fileName) {
        this(fileName, DEF_ODDELOVAC);
    }

    /**
     * Konstruktor se zadáním cesty k souboru i požadovaného oddělovače
     * @param fileName
     * @param oddelovac
     */
    public EvidenceCSV(String fileName, String oddelovac) {
        super();
        this.oddelovac = oddelovac;
        soubor = new File(fileName);
        loadFile(soubor);
    }

    /**
     * Implementace finalizéru z rozhraní, tj. je-li aplikace ukončována,
     * chceme uložit všechny záznamy
     */
    @Override
    public void ukonci() {
        try {
            Files.writeString(soubor.toPath(), Pojistenec.csvHlavicka() + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            for (Pojistenec p: vyberVsechnyPojistence())
                Files.writeString(soubor.toPath(), p.ulozDoCsv() + System.lineSeparator(), StandardOpenOption.APPEND);
        } catch (IOException ioe) {
            System.err.println("Pozor, při ukládání dat došlo k chybě: " + ioe.getMessage());
        }
    }

    /**
     * Metoda načítající data ze zadaného souboru. Potichu (bez hlášení) přeskakuje
     * případné chybové záznamy (např. proto, že se mezitím změnily požadavky v datové třídě)
     * @param file Soubor pro čtení záznamů o pojištěncích
     */
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
