package cz.braza.educanet;

import java.util.Arrays;

/**
 * Uloha 4
 * =======
 * - název třídy má začínat malým písmenem
 * - název metody má začínat malým písmenem
 * - název metody má být podle funkce metody, takto nicneříkající název
 * - metoda má brát jako parametr pole čísel
 * - metoda má mít návratový typ a vracet medián (prostřední hodnotu)
 * - nevhodné názvy proměnných - searchMediam by se mohl jmenovat prostredniIndex nebo middleIndex
 * - nevhodné názvy proměnných - numberList by se mohl jmenovat zbytekPoDeleniDvema, ale ještě lépe by to mohl být boolean, např.:
 *   boolean lichyPocet = numbers.length % 2 == 1;
 * - české i anglické identifikátory v jedné třídě není dobrá praktika
 * - funkce vůbec nepočítá medián (neřadí pole, resp. nefunguje s neseřazeným polem)
 * - v případě sudého počtu prvků ani nevypočte hodnotu, ale vypíše dvě z nich
 * - poslední else je zbytečné a nesmyslně komplikuje zápis
 *
 * Nesmyslné/nepravdivé pokusy:
 * chybí metoda main -- nic takového třída nevyžaduje
 * chybí import ArrayList -- tato třída se vůbec nepoužívá, není důvod ji importovat
 * třída se nikde nepoužívá -- no a co?
 * numberList == 1 je vždy true -- to vám asi hlásí IDE, protože předtím je fixně dané pole, ale jinak je to blbost
 * int searchMedian = numbers.length / 2; U lichého počtu nefunguje -- nesmysl
 * výpočet programu je správně -- no to opravdu není, ale pro jedno konkrétní zadání možná ano
 * nepoužívání try catch -- není důvod, když nemá kde vzniknout výjimka (a i kdyby ano, tady na ni stejně nebudeme reagovat)
 * chybí public class -- to tam v daném případě (kdy se soubor jmenuje jinak než třída) ani být NESMÍ, ale jindy nemusí
 * nefunguje else if, protože nefunguje 0, protože se nic 0 nemůže rovnat -- nerozumím řeči tvého kmene
 *
 *
 *
 * Výsledek:
 */

/**
 * Spočte medián, vrací celé číslo, takže v případě sudého počtu zaokrouhluje průměr na celé číslo dolů
 */
class PetiminutovkyBrezen {
    static int najdiMedian(int[] numbers) {
        Arrays.sort(numbers);
        int prostredniPrvek = numbers.length / 2;
        boolean lichyPocet = numbers.length % 2 == 1;

        if (lichyPocet) {
            return numbers[prostredniPrvek];
        } else {
            return (numbers[prostredniPrvek - 1] + numbers[prostredniPrvek]) / 2;
        }
    }
}

class ClovekRozsireny {
    private String jmeno = "NONAME";  // není-li zadané vyhovující (neprázdné) jméno, toto je defaultní hodnota
    private int vek = 0; // není-li zadaný vyhovující (nezáporný) věk, defaultní hodnota
    private int hendikep = 0; // není-li zadaný vyhovující hendikep (+- 100), defaultní hodnota
    public static final int HENDIKEP_ROZSAH = 100; // konstanta pro hlídání rozsahu hendikepu

    /**
     * Vytvoří objekt, vyžaduje všechny parametry
     * Použitím setterů v konstruktoru zajistím, že logika hlídání hodnot je v kódu jen jednou
     * @param jmeno jméno člověka
     * @param vek aktuální věk
     * @param hendikep jeho herní hendikep
     */
    public ClovekRozsireny(String jmeno, int vek, int hendikep) {
        setJmeno(jmeno);
        setVek(vek);
        setHendikep(hendikep);
    }


    // vygenerováno v IDE
    @Override
    public String toString() {
        return "Clovek{" +
                "jmeno='" + jmeno + '\'' +
                ", vek=" + vek +
                ", hendikep=" + hendikep +
                '}';
    }

    // vygenerováno v IDE
    public String getJmeno() {
        return jmeno;
    }

    // vygenerováno v IDE, doplněna podmínka
    public void setJmeno(String jmeno) {
        if (jmeno != null && !jmeno.isBlank())
            this.jmeno = jmeno;
    }

    // vygenerováno v IDE
    public int getVek() {
        return vek;
    }

    // vygenerováno v IDE, doplněna podmínka
    public void setVek(int vek) {
        if (vek >= 0)
            this.vek = vek;
    }

    // vygenerováno v IDE
    public int getHendikep() {
        return hendikep;
    }

    // vygenerováno v IDE, doplněna podmínka
    public void setHendikep(int hendikep) {
        if (Math.abs(hendikep) <= HENDIKEP_ROZSAH)
            this.hendikep = hendikep;
    }
}


public class PetiminutovkyDuben {
    /**
     * Mohu si vybrat, jestli použít anglickou sadu, českou sadu nebo nějakou vlastní
     * (druhý parametr příslušných metod)
     */
    public static final String SAMOHLASKY = "aáeéěiíoóuúůyý";
    public static final String VOWELS = "aeiou";

    /**
     * Faktoriál rekurzí, včetně bonusu díky ternárnímu operátoru.
     * Všimněte si návratového typu long, takže spočítáme i větší faktoriály, nejen do 12.
     * @param n
     * @return
     */
    public static long faktorial(int n) {
        return n < 2 ? 1 : n * faktorial(n - 1);
    }

    /**
     * Faktoriál počítaný cyklem, tedy bez rekurze.
     * Upozorňuji na operátor dekrementace v rámci výpočtu, jinak lze i takto:
     while (n > 1) {
         result *= n;
         n--;
     }
     * @param n
     * @return
     */
    public static long faktorialCyklem(int n) {
        long result = 1;
        while (n > 1)
            result *= n--;
        return result;
    }

    public static int spoctiSamohlasky(String text) {
        return spoctiSamohlasky(text, SAMOHLASKY);
    }

    public static int spoctiSamohlasky(String text, String seznamKPorovnani) {
        int pocet = 0;
        for (char c: text.toLowerCase().toCharArray())
            if (seznamKPorovnani.contains("" + c))
                pocet++;
        return pocet;
    }

    public static String najdiSamohlasky(String text) {
        return najdiSamohlasky(text, SAMOHLASKY);
    }

    public static String najdiSamohlasky(String text, String seznamKPorovnani) {
        String vysledek = "";
        for (char c: text.toLowerCase().toCharArray())
            if (seznamKPorovnani.contains("" + c))
                vysledek += "" + c;
        return vysledek;

    }

    /**
     * Spočte samohlásky v předaném textu využitím funkce @see najdiSamohlasky()
     * Pokud už totiž takovou funkci mám, chci mít příslušnou logiku v kódu jenom
     * jednou, a je zvlášť v najdiSamohlasky() a zvlášť v spoctiSamohlasky()
     * @param text řetězec, v kterém vyhledávám samohlásky
     * @return počet samohlásek v řetězci
     */
    public static int lineSpoctiSamohlasky(String text) {
        return najdiSamohlasky(text).length();
    }

    public static void main(String[] args) {
        System.out.println(faktorial(5));
        System.out.println(faktorialCyklem(5));
        System.out.println(lineSpoctiSamohlasky("PřÍliš žluťoučkÝ kůň"));
    }
}
