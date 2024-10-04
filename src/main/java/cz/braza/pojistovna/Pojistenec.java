package cz.braza.pojistovna;

import java.time.LocalDate;
import java.time.Period;

/**
 * Hlavní datová třída reprezentující jednotlivé pojištěnce.
 * Každý pojištěnec má jméno a příjmení, datum narození,
 * které je možné na počátku (přibližně) vypočítat ze zadaného věku,
 * telefonní číslo, vhodné též jako identifikátor.
 * Jméno ani příjmení nesmějí být prázdné, další omezení na velikost
 * nejsou uplatňována na datové úrovni.
 */
public class Pojistenec {
    /**
     * Chybová zpráva (pro výjimku) v případě, že jméno nebo příjmení nejsou vyplněny
     */
    public static final String ERR_EMPTY_STRING = "Řetězec nesmí být prázdný";
    /**
     * Chybová zpráva (pro výjimku) v případě, že věk nevyhovuje zadaným kritériím (0-150 let)
     */
    public static final String ERR_WRONG_AGE = "Věk musí být nezáporné číslo do 150";

    /**
     * První (křestní) jméno pojištěnce, resp. všechna taková (mimo příjmení)
     */
    private String jmeno;
    /**
     * Příjmení pojištěnce
     */
    private String prijmeni;
    /**
     * Datum narození pojištěnce, v aplikaci může být též přibližně vypočítáno ze zadaného věku
     */
    private LocalDate narozen;
    /**
     * Telefonní číslo pojištěnce (kontakt)
     */
    private String telefon;

    /**
     * Vrací hlavičku pro CSV soubor
     * @return CSV řádek hlavičky (seznam polí)
     */
    public static String csvHlavicka() {
        return "jmeno,prijmeni,vek,telefon";
    }

    /**
     * Vrátí data instance jako CSV s určeným oddělovačem
     * @param delimiter Oddělovač
     * @return řetězec s atributy oddělenými zadaným oddělovačem
     */
    public String ulozDoCsv(String delimiter) {
        return getJmeno() + delimiter + getPrijmeni() + delimiter + getVek() + delimiter + getTelefon();
    }

    /**
     * Vrátí data instance jako CSV řádek s oddělovačem čárkou (dle standardu)
     * @return řetězec oddělený čárkami
     */
    public String ulozDoCsv() {
        return ulozDoCsv(",");
    }

    /**
     * Metoda ze zadaného věku vytvoří datum narození, přičemž
     * hlídá základní integritní omezení (nezáporný věk do 200)
     * @param vek počet let
     * @return Datum posunuté o "vek" let dozadu
     */
    private static LocalDate vytvorDatumZVeku(int vek) {
        if (vek < 0 || vek > 200)
            throw new IllegalArgumentException(ERR_WRONG_AGE);
        return LocalDate.now().minusYears(vek);
    }

    /**
     * Vytvoří instanci, je-li zadáno neprázdné jméno, příjmení, vyplněné
     * datum narození a telefon, bez omezení vstupu
     * @param jmeno Jméno pojištěnce (případně více jmen)
     * @param prijmeni Příjmení pojištěnce
     * @param datumNarozeni Datum narození pojištěnce
     * @param telefon Telefon pojištěnce
     */
    public Pojistenec(String jmeno, String prijmeni, LocalDate datumNarozeni, String telefon) {
        if (jmeno == null || jmeno.isEmpty())
            throw new IllegalArgumentException(ERR_EMPTY_STRING);
        if (prijmeni == null || prijmeni.isEmpty())
            throw new IllegalArgumentException(ERR_EMPTY_STRING);
        if (datumNarozeni == null)
            datumNarozeni = LocalDate.now();
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.narozen = datumNarozeni;
        this.telefon = telefon;
    }

    /**
     * Vytváří instanci ze zadaného jména, příjmení, věku a telefonního čísla
     * Využívá druhý konstruktor a statickou metodu na převod věku na datum
     * (včetně kontroly vstupu), protože volání this musí být první příkaz
     * v konstruktoru a nelze to tedy provést zde
     * @param jmeno Jméno pojištěnce (nebo více křestních jmen)
     * @param prijmeni Příjmení pojištěnce
     * @param vek Věk pojištěnce (0-200)
     * @param telefon Telefonní číslo pojištěnce
     */
    public Pojistenec(String jmeno, String prijmeni, int vek, String telefon) {
        this(jmeno, prijmeni, vytvorDatumZVeku(vek), telefon);
    }

    /**
     * Getter na celé jméno pojištěnce
     * @return Křestní jména + příjmení jako jeden řetězec
     */
    public String celeJmeno() {
        return jmeno + " " + prijmeni;
    }

    /**
     * Getter na vlastnost jmeno
     * @return Jméno(a) pojištěnce
     */
    public String getJmeno() { return jmeno; }

    /**
     * Getter na příjmení
     * @return Příjmení pojištěnce
     */
    public String getPrijmeni() { return prijmeni; }

    /**
     * Getter na telefonní číslo
     * @return Vrací telefonní číslo pojištěnce
     */
    public String getTelefon() { return telefon; }

    /**
     * Getter na (virtuální) vlastnost věk
     * @return Věk pojištěnce
     */
    public int getVek() {
        return Period.between(narozen, LocalDate.now()).getYears();
    }

    /**
     * Řetězcová reprezentace dat instance
     * @return Jméno, věk a telefon
     */
    @Override
    public String toString() {
        return celeJmeno() + ", věk: " + getVek() + " let, tel.: " + telefon;
    }
}
