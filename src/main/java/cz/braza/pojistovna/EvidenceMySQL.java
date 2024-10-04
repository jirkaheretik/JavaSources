package cz.braza.pojistovna;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Evidence pojištěnců využívající pro uložení dat databázi MySQL/MariaDB.
 * Implementuje rozhraní @see EvidenceInterface
 */
public class EvidenceMySQL implements EvidenceService {
    /**
     * Konstanta pro jméno databáze používané při připojování
     */
    private static final String DB_NAME = "pojistenci";
    /**
     * Konstanta pro uživatelské jméno používané při připojování
     */
    private static final String USER_NAME = "root";
    /**
     * Konstanta pro heslo uživatele používané při připojování
     */
    private static final String USER_PASS = "root";
    /**
     * Kompletní připojovací řetězec využívající jednotlivé konstanty pro db, uživatele a heslo
     */
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost/" + DB_NAME + "?user=" + USER_NAME + "&password=" + USER_PASS + "&serverTimezone=Europe/Rome";
    /**
     * Spojení k DB nastavované v konstruktoru
     */
    private Connection spojeni;
    /**
     * Informace, zda spojení k db funguje
     */
    private boolean spojeno = false;
    /**
     * Předpřipravený dotaz pro hledání dle jména, příjmení a telefonního čísla
     */
    private PreparedStatement hledani;
    /**
     * Předpřipravený dotaz pro hledání dle rozmezí věku
     */
    private PreparedStatement hledaniVek;
    /**
     * Předpřipravený dotaz pro přidávání nových záznamů
     */
    private PreparedStatement pridani;

    /**
     * Konstruktor, pokusí se vytvořit a nastavit připojení,
     * v dalších metodách už se funkční spojení předpokládá
     * (řešení chyb zatím není)
     * V rámci instanciace zároveň vytváří a nastavuje do vlastností
     * třídy předpřipravené dotazy pro usnadnění pozdější práce
     */
    public EvidenceMySQL() {
        try {
            spojeni = DriverManager.getConnection(CONNECTION_STRING);
            spojeno = true;
            hledani = spojeni.prepareStatement("select * from pojistenec where jmeno like ? or prijmeni like ? or telefon like ?");
            hledaniVek = spojeni.prepareStatement("select * from pojistenec where vek >= ? and vek <= ?");
            pridani = spojeni.prepareStatement("insert into pojistenec (jmeno, prijmeni, vek, telefon) values(?, ?, ?, ?)");
        } catch (SQLException e) {
            System.err.println("Chyba databáze: " + e.getMessage());
        }
    }

    /**
     * Přidává nový záznam o pojištěnci.
     * Přebaluje případnou SQL výjimku na RuntimeException
     * @param jmeno Jméno pojištěnce
     * @param prijmeni Příjmení pojištěnce
     * @param vek Věk pojištěnce
     * @param telefon Telefonní číslo pojištěnce
     */
    @Override
    public void pridejPojistence(String jmeno, String prijmeni, int vek, String telefon) {
        try {
            pridani.setString(1, jmeno);
            pridani.setString(2, prijmeni);
            pridani.setInt(3, vek);
            pridani.setString(4, telefon);
            pridani.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Soukromá metoda, která z různých dotazů (již dovyplněných PreparedStatements)
     * posbírá data do listu, který vrací.
     * Přebaluje SQL chyby na RuntimeException
     * @param pst Předpřipravený dotaz, který se použije pro získávání záznamů
     * @return Seznam pojištěnců vyhovujících zadanému dotazu
     */
    private Collection<Pojistenec> posbirejPojistenceZDotazu(PreparedStatement pst) {
        List<Pojistenec> vysledek = new ArrayList<>();
        try {
            ResultSet data = pst.executeQuery();
            while (data.next()) {
                try {
                    vysledek.add(new Pojistenec(data.getString("jmeno"), data.getString("prijmeni"), data.getInt("vek"), data.getString("telefon")));
                } catch (SQLException e) {} // ignore results we cannot instantiate
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vysledek;
    }

    /**
     * Metoda vrací všechny pojištěnce v databázi
     * @return Seznam všech pojištěnců
     */
    @Override
    public Collection<Pojistenec> vyberVsechnyPojistence() {
        try {
            return posbirejPojistenceZDotazu(spojeni.prepareStatement("select * from pojistenec"));
        } catch (SQLException e) {
            System.err.println("Chyba DB: " + e.getMessage());
        }
        return null;
    }

    /**
     * Metoda vyhledává pojištěnce dle jména, příjmení nebo části telefonního čísla
     * @param text Text k vyhledání
     * @return Seznam pojištěnců vyhovujících dotazu
     */
    @Override
    public Collection<Pojistenec> vyhledejPojistence(String text) {
        try {
            hledani.setString(1, text + "%");
            hledani.setString(2, text + "%");
            hledani.setString(3, "%" + text + "%");
            return posbirejPojistenceZDotazu(hledani);
        } catch (SQLException e) {
            System.err.println("Chyba DB: " + e.getMessage());
        }
        return null;
    }

    /**
     * Vyhledávání pojištěnců dle věku (minimum a maximum, obojí včetně)
     * @param min Minimální věk pojištěnce
     * @param max Maximální věk pojištěnce
     * @return Seznam pojištěnců vyhovujících zadaným podmínkám.
     */
    @Override
    public Collection<Pojistenec> vyhledejPojistenceDleVeku(int min, int max) {
        try {
            hledaniVek.setInt(1, min);
            hledaniVek.setInt(2, max);
            return posbirejPojistenceZDotazu(hledaniVek);
        } catch (SQLException e) {
            System.err.println("Chyba DB: " + e.getMessage());
        }
        return null;
    }

    /**
     * Vrací počet záznamů v databázi
     * @return Počet pojištěnců v tabulce
     */
    @Override
    public int vratPocetZaznamu() {
        int pocetZaznamu = 0;
        try {
            ResultSet rs = spojeni.createStatement().executeQuery("select count(*) from pojistenec");
            rs.next();
            pocetZaznamu = rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Chyba DB: " + e.getMessage());
        }
        return pocetZaznamu;
    }
}
