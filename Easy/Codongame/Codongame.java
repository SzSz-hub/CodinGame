import java.util.*;

public class Solution {
    private static final Map<String, String> CODON_TABLE = new HashMap<>();
    static {
        CODON_TABLE.put("UUU", "F");
        CODON_TABLE.put("UUC", "F");
        CODON_TABLE.put("UUA", "L");
        CODON_TABLE.put("UUG", "L");
        CODON_TABLE.put("UCU", "S");
        CODON_TABLE.put("UCC", "S");
        CODON_TABLE.put("UCA", "S");
        CODON_TABLE.put("UCG", "S");
        CODON_TABLE.put("UAU", "Y");
        CODON_TABLE.put("UAC", "Y");
        CODON_TABLE.put("UAA", "*");
        CODON_TABLE.put("UAG", "*");
        CODON_TABLE.put("UGU", "C");
        CODON_TABLE.put("UGC", "C");
        CODON_TABLE.put("UGA", "*");
        CODON_TABLE.put("UGG", "W");
        CODON_TABLE.put("CUU", "L");
        CODON_TABLE.put("CUC", "L");
        CODON_TABLE.put("CUA", "L");
        CODON_TABLE.put("CUG", "L");
        CODON_TABLE.put("CCU", "P");
        CODON_TABLE.put("CCC", "P");
        CODON_TABLE.put("CCA", "P");
        CODON_TABLE.put("CCG", "P");
        CODON_TABLE.put("CAU", "H");
        CODON_TABLE.put("CAC", "H");
        CODON_TABLE.put("CAA", "Q");
        CODON_TABLE.put("CAG", "Q");
        CODON_TABLE.put("CGU", "R");
        CODON_TABLE.put("CGC", "R");
        CODON_TABLE.put("CGA", "R");
        CODON_TABLE.put("CGG", "R");
        CODON_TABLE.put("AUU", "I");
        CODON_TABLE.put("AUC", "I");
        CODON_TABLE.put("AUA", "I");
        CODON_TABLE.put("AUG", "M");
        CODON_TABLE.put("ACU", "T");
        CODON_TABLE.put("ACC", "T");
        CODON_TABLE.put("ACA", "T");
        CODON_TABLE.put("ACG", "T");
        CODON_TABLE.put("AAU", "N");
        CODON_TABLE.put("AAC", "N");
        CODON_TABLE.put("AAA", "K");
        CODON_TABLE.put("AAG", "K");
        CODON_TABLE.put("AGU", "S");
        CODON_TABLE.put("AGC", "S");
        CODON_TABLE.put("AGA", "R");
        CODON_TABLE.put("AGG", "R");
        CODON_TABLE.put("GUU", "V");
        CODON_TABLE.put("GUC", "V");
        CODON_TABLE.put("GUA", "V");
        CODON_TABLE.put("GUG", "V");
        CODON_TABLE.put("GCU", "A");
        CODON_TABLE.put("GCC", "A");
        CODON_TABLE.put("GCA", "A");
        CODON_TABLE.put("GCG", "A");
        CODON_TABLE.put("GAU", "D");
        CODON_TABLE.put("GAC", "D");
        CODON_TABLE.put("GAA", "E");
        CODON_TABLE.put("GAG", "E");
        CODON_TABLE.put("GGU", "G");
        CODON_TABLE.put("GGC", "G");
        CODON_TABLE.put("GGA", "G");
        CODON_TABLE.put("GGG", "G");
    }

    private static final String START_CODON = "AUG";
    private static final Set<String> STOP_CODONS = new HashSet<>(Arrays.asList("UAA", "UAG", "UGA"));

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            int n = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < n; i++) {
                String rna = scanner.nextLine().trim();
                String result = translateRNA(rna);
                System.out.println(result);
            }
        }
    }

    public static String translateRNA(String rna) {
        String bestTranslation = "";
        int maxAminoAcids = 0;

        for (int startIndex = 0; startIndex < 3; startIndex++) {
            List<String> sequences = translateFromIndex(rna, startIndex);

            int totalAminoAcids = 0;
            for (String seq : sequences) {
                totalAminoAcids += seq.length();
            }

            if (totalAminoAcids > maxAminoAcids) {
                maxAminoAcids = totalAminoAcids;
                bestTranslation = String.join("-", sequences);
            }
        }

        return bestTranslation;
    }

    private static List<String> translateFromIndex(String rna, int startIndex) {
        List<String> sequences = new ArrayList<>();
        boolean isOpen = false;
        StringBuilder currentSequence = new StringBuilder();

        for (int i = startIndex; i + 2 < rna.length(); i += 3) {
            String codon = rna.substring(i, i + 3);

            if (!isOpen && START_CODON.equals(codon)) {
                isOpen = true;
                currentSequence = new StringBuilder();
                currentSequence.append(CODON_TABLE.get(codon));
            } else if (isOpen && STOP_CODONS.contains(codon)) {
                isOpen = false;
                if (currentSequence.length() > 0) {
                    sequences.add(currentSequence.toString());
                }
                currentSequence = new StringBuilder();
            } else if (isOpen && CODON_TABLE.containsKey(codon)) {
                currentSequence.append(CODON_TABLE.get(codon));
            }
        }

        return sequences;
    }
}
