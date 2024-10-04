package cz.braza.pojistovna;

import java.util.Collection;

/**
 * Rozhraní pro třídu/službu, která obstarává práci se seznamem záznamů (pojištěnců).
 * Pro některé metody už toto rozhraní poskytuje defaultní implementaci, dostačující
 * v běžných použitích. Protože se předpokládá, že některé implementující třídy
 * budou potřebovat něco jako finalizér při ukončení práce s aplikací, je k tomu
 * k dispozici metoda <b>ukonci()</b> včetně prázdné defaultní implementace.
 */
public interface EvidenceService {
    /**
     * Metoda přidává nového pojištěnce do seznamu
     * @param jmeno Jméno pojištěnce
     * @param prijmeni Příjmení pojištěnce
     * @param vek Věk pojištěnce
     * @param telefon Telefonní číslo pojištěnce
     * @throws IllegalArgumentException Konstruktor pojištěnce může vracet výjimku nejsou-li splněna zadaná kritéria
     */
    void pridejPojistence(String jmeno, String prijmeni, int vek, String telefon);

    /**
     * Vrací seznam všech pojištěnců jako neměnitelnou kolekci
     * @return Kolekce záznamů o pojištěncích
     */
    Collection<Pojistenec> vyberVsechnyPojistence();

    /**
     * Vyhledává pojištěnce podle zadaného textu. Je-li zadán jeden token
     * (bez mezer), vyhledává jej ve jménu i v příjmení, je-li jich zadáno více,
     * rozdělí to dle poslední mezery a hledá jména a příjmení začínající
     * příslušnou částí.
     * V obou případech text zároveň hledá jako podřetězec v telefonním čísle
     * @param text Text k vyhledání
     * @return Seznam pojištěnců splňujících zadaná kritéria
     */
    Collection<Pojistenec> vyhledejPojistence(String text);

    /**
     * Vyhledá pojištěnce v zadaném věkovém rozmezí (min-max včetně)
     * @param min Minimální věk pojištěnce
     * @param max Maximální věk pojištěnce
     * @return Seznam pojištěnců dle vyhledávacích kritérií
     */
    Collection<Pojistenec> vyhledejPojistenceDleVeku(int min, int max);

    /**
     * Vyhledává všechny pojištěnce starší než zadaný věk (včetně)
     * Standardní implementace (nejspíš nebude potřeba nikde překrývat)
     * využívá definovanou funkci {@link #vyhledejPojistenceDleVeku}
     * s maximem nastaveným na Integer.MAX_VALUE
     * @param min Minimální věk pojištěnce
     * @return Seznam pojištěnců dle vyhledávacích kritérií
     */
    default Collection<Pojistenec> vyhledejPojistenceDleVekuOd(int min) {
        return vyhledejPojistenceDleVeku(min, Integer.MAX_VALUE);
    }

    /**
     * Vyhledává všechny pojištěnce do zadaného věku (včetně)
     * Standardní implementace (nejspíš nebude potřeba nikde překrývat)
     * využívá definovanou funkci {@link #vyhledejPojistenceDleVeku}
     * s minimem nastaveným na hodnotu 0
     * @param max Maximální věk pojištěnce
     * @return Seznam pojištěnců dle vyhledávacích kritérií
     */
    default Collection<Pojistenec> vyhledejPojistenceDleVekuDo(int max) {
        return vyhledejPojistenceDleVeku(0, max);
    }

    /**
     * Vrací počet záznamů v evidenci
     * @return Počet platných záznamů
     */
    int vratPocetZaznamu();

    /**
     * Ukončení práce s evidencí (úklid)
     * Prázdná implementace dostačující pro řadu implementací.
     * Nahrazuje destruktor/finalizér.
     */
    default void ukonci() {}
}
