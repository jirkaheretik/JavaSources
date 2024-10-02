package cz.braza.pojistovna;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EvidenceMySQL implements SeznamPojistencu {
    private Connection spojeni;
    private boolean spojeno = false;
    private PreparedStatement hledani;
    private PreparedStatement pridani;
    private PreparedStatement hledaniVek;

    public EvidenceMySQL() {
        try {
            spojeni = DriverManager.getConnection("jdbc:mysql://localhost/pojistenci?user=root&password=root&serverTimezone=Europe/Rome");
            System.out.println("Spojení navázáno");
            spojeno = true;
            hledani = spojeni.prepareStatement("select * from pojistenec where jmeno like ? or prijmeni like ? or telefon like ?");
            hledaniVek = spojeni.prepareStatement("select * from pojistenec where vek >= ? and vek <= ?");
            pridani = spojeni.prepareStatement("insert into pojistenec (jmeno, prijmeni, vek, telefon) values(?, ?, ?, ?)");
        } catch (SQLException e) {
            System.err.println("Chyba databáze: " + e.getMessage());
        }
    }

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

    @Override
    public Collection<Pojistenec> vyberVsechnyPojistence() {
        try {
            return posbirejPojistenceZDotazu(spojeni.prepareStatement("select * from pojistenec"));
        } catch (SQLException e) {
            System.err.println("Chyba DB: " + e.getMessage());
        }
        return null;
    }

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
