import java.util.*;

class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of elements which make up the association table.
        int Q = in.nextInt(); // Number Q of file names to be analyzed.
        Map<String, String> dictionary = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String EXT = in.next().toLowerCase(); // file extension
            String MT = in.next(); // MIME type.
            dictionary.put(EXT, MT);
        }
        in.nextLine();
        for (int i = 0; i < Q; i++) {
            String FNAME = in.nextLine(); // One file name per line.
            System.err.println(FNAME);
            String extension = FNAME.contains(".") ? FNAME.substring(FNAME.lastIndexOf(".") + 1).toLowerCase()
                    : "nothing";
            System.out.println(dictionary.getOrDefault(extension, "UNKNOWN"));

        }
        in.close();
    }
}
