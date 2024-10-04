package cz.braza.pojistovna;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Třída spravující data o pojištěncích. Pro uložení dat používá
 * ArrayList, data jsou tedy uložena pouze v paměti.
 * Pro potřeby persistence {@link EvidenceCSV} nebo {@link EvidenceMySQL}
 */
public class EvidenceInMemory implements EvidenceService {
    /**
     * Seznam spravovaných záznamů (pojištěnců)
     */
    private List<Pojistenec> pojistenci = new ArrayList<>();

    /**
     * Metoda přidává nového pojištěnce do seznamu
     * @param jmeno Jméno pojištěnce
     * @param prijmeni Příjmení pojištěnce
     * @param vek Věk pojištěnce
     * @param telefon Telefonní číslo pojištěnce
     * @throws IllegalArgumentException Konstruktor pojištěnce může vracet výjimku nejsou-li splněna zadaná kritéria
     */
    @Override
    public void pridejPojistence(String jmeno, String prijmeni, int vek, String telefon) {
        pojistenci.add(new Pojistenec(jmeno, prijmeni, vek, telefon));
    }

    /**
     * Vrací seznam všech pojištěnců jako neměnitelnou kolekci
     * @return Kolekce záznamů o pojištěncích
     */
    @Override
    public Collection<Pojistenec> vyberVsechnyPojistence() {
        return Collections.unmodifiableList(pojistenci);
    }

    /**
     * Vyhledává pojištěnce podle zadaného textu. Je-li zadán jeden token
     * (bez mezer), vyhledává jej ve jménu i v příjmení, je-li jich zadáno více,
     * rozdělí to dle poslední mezery a hledá jména a příjmení začínající
     * příslušnou částí.
     * V obou případech text zároveň hledá jako podřetězec v telefonním čísle
     * @param text Text k vyhledání
     * @return Seznam pojištěnců splňujících zadaná kritéria
     */
    @Override
    public Collection<Pojistenec> vyhledejPojistence(String text) {
        String textJmeno = text;
        String textPrijmeni = text;
        if (text.contains(" ")) {
            int pos = text.lastIndexOf(" ");
            textJmeno = text.substring(0, pos).trim();
            textPrijmeni = text.substring(pos).trim();
        }
        List<Pojistenec> vyhledano = new ArrayList<>();
        for (Pojistenec p: pojistenci) {
            if (p.getJmeno().startsWith(textJmeno) || p.getPrijmeni().startsWith(textPrijmeni) || p.getTelefon().contains(text))
                vyhledano.add(p);
        }
        return vyhledano;
    }

    /**
     * Vyhledá pojištěnce v zadaném věkovém rozmezí (min-max včetně)
     * @param min Minimální věk pojištěnce
     * @param max Maximální věk pojištěnce
     * @return Seznam pojištěnců dle vyhledávacích kritérií
     */
    @Override
    public Collection<Pojistenec> vyhledejPojistenceDleVeku(int min, int max) {
        List<Pojistenec> vyhledano = new ArrayList<>();
        for (Pojistenec p: pojistenci) {
            if (p.getVek() >= min && p.getVek() <= max)
                vyhledano.add(p);
        }
        return vyhledano;
    }

    /**
     * Vrací počet pojištěnců v seznamu
     * @return Počet pojištěnců
     */
    @Override
    public int vratPocetZaznamu() {
        return pojistenci.size();
    }
}
