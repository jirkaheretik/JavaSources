package cz.braza;
public class CislaNaText {
    public static String[] RADY1 = { "", "tisíc ", "milion ", "miliarda "};
    public static String[] RADY2 = { "", " tisíce ", " miliony ", " miliardy "};
    public static String[] RADY5 = { "", " tisíc ", " milionů ", " miliard "};
    public static String[] JEDNICKY = {"jeden", "jedna", "jedno"};
    public static String[] JEDNOTKY1 = {"", "jeden", "dva", "tři", "čtyři", "pět", "šest", "sedm", "osm", "devět", "deset", "jedenáct", "dvanáct", "třináct", "čtrnáct", "patnáct", "šestnáct", "sedmnáct", "osmnáct", "devatenáct"};
    public static String[] JEDNOTKY1F = {"", "", "dvě", "tři", "čtyři", "pět", "šest", "sedm", "osm", "devět"};
    public static String[] DESITKY = {"", "deset", "dvacet", "třicet", "čtyřicet", "padesát", "šedesát", "sedmdesát", "osmdesát", "devadesát"};
    public static String[] STOVKY = {"", "sto", "stě", "sta", "sta", "set", "set", "set", "set", "set"};

    public static String sub1000ToCzech(int value, int rad) {
        String result = "";
        int jednotky = value % 10;
        value /= 10;
        int desitky = value % 10;
        value /= 10;
        int stovky = value;
        return JEDNOTKY1F[stovky] + STOVKY[stovky] + DESITKY[desitky]
                + switch(jednotky) {
            case 1 -> (rad == 3 || rad == 0 ? JEDNICKY[1] : JEDNICKY[0]) + RADY1[rad];
            case 2 -> (rad == 3 ? JEDNOTKY1F[jednotky] : JEDNOTKY1[jednotky]) + RADY2[rad];
            case 3, 4 -> JEDNOTKY1[jednotky] + RADY2[rad];
            default -> JEDNOTKY1[jednotky] + RADY5[rad];
        };
    }

    public static String intToCzech(int value) {
        String result = "";
        int rady = 0;
        while (value > 0) {
            result = sub1000ToCzech(value % 1000, rady++) + result;
            value /= 1000;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(intToCzech(314));
        System.out.println(intToCzech(2040924666));
        System.out.println(intToCzech(1327));
        System.out.println(intToCzech(301327));
        System.out.println(intToCzech(21327));

        System.out.println(intToCzech(106));


    }
}
