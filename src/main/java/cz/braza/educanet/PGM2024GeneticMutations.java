package cz.braza.educanet;

/**
 * GENETICKÉ MUTACE (55 bodů)
 * Zjistili jste, že programovat už umíte dokonale a už to pro vás není žádná výzva, proto
 * jste se rozhodli, že se naučíte něco nového. Po chvíli hledání jste narazili na obor
 * Bioinformatika (kombinace biologie a informatiky). Začali jste studovat, co taková
 * bioinformatika zahrnuje a narazili jste na následující úlohu. Vaším úkolem je, tuto
 * úlohu vyřešit:
 * Definujeme gen jako slovo složené z 8 písmen, kde písmena mohou být pouze “A”,
 * ”C”, “G” nebo ”T”. Příklad genu je například sekvence písmen “ACCGATGC”. Máme
 * zadaný počáteční gen a chtěli bychom spočítat a vypsat nejmenší počet mutací,
 * abychom dostali výsledný gen. Mutace je změna jednoho písmene v genu (na jedno
 * ze čtyř možných písmen). Ne všechny geny je samozřejmě možné vytvořit. Geny,
 * které lze vytvořit, jsou uloženy v souboru (na každém řádku jeden gen). Cesta k
 * tomuto souboru je zadaná jako první parametr příkazové řádky. Jestliže Cíl není
 * možné vytvořit, program vypíše -1.
 * Příklad:
 * seznam možných genů: [“AAACGGTT”, “AAACGGTA”, “ATACGGTA”, “AACCGGTT”]
 * počáteční gen: “AACCGGTT”
 * cílový gen: “AAACGGTA”
 * řešení: “AACCGGTT” → “AAACGGTT” → “AAACGGTA”
 * Program by tedy vypsal tyto tři geny (na každý řádek jeden gen) na příkazovou řádku.
 * Pokud nevíte jak začít, můžete na začátku předpokládat, že lze vytvořit libovolný gen. Za
 * takové řešení dostanete část bodů
 */

public class PGM2024GeneticMutations {
    public static final String START = "AACCGGTT";
    public static final String TARGET = "AAACGGTA";
    public static final String[] VARIANTS = {"AAACGGTT", "AAACGGTA", "ATACGGTA", "AACCGGTT"};

    public static void main(String[] args) {
        // variant EASY - all possible combinations.
        if (START.length() != TARGET.length()) {
            System.out.println("-1 (different lenghts of genes)");
        } else {
            StringBuilder mid = new StringBuilder(START);
            int count = 0;
            System.out.println(START);
            for (int i = 0; i < START.length(); i++)
                if (START.charAt(i) != TARGET.charAt(i)) {
                    count++;
                    mid.replace(i, i + 1, "" + TARGET.charAt(i));
                    System.out.println(mid.toString());
                }
            System.out.println("Made " + count + " mutations.");
        }

        // Variant Hard - use the array of all possible mutations (VARIANTS)
        int minCount = VARIANTS.length + 1;
        int count = 0;
        System.out.println(START);
        System.out.println(minPath(START + "-", TARGET, VARIANTS));
    }

    public static int diff(String first, String second) {
        int result = 0;
        for (int i = 0; i < first.length(); i++)
            if (first.charAt(i) != second.charAt(i))
                result++;
        return result;
    }

    public static String minPath(String path, String target, String[] members) {
        String current = "???TODO?";
        for (String member: members) {
            if (diff(current, member) == 1 && !path.contains(member + "-")) {
                if (member == target)
                    return path + member;
                path += member + "-";
                return minPath(path, target, members);
            }
        }
        return null;
    }

}
