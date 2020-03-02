public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int k = pattern.length() - 1;

        RollingString rInput = new RollingString(input.substring(0, k+1), k+1);
        RollingString rPattern = new RollingString(pattern, k+1);

        int patternHash = rPattern.hashCode();
        while (k < input.length()) {
            if (rInput.hashCode() == patternHash && rInput.equals(rPattern)) {
                return k - pattern.length() + 1;
            }
            k++;
            if (k < input.length()) {
                rInput.addChar(input.charAt(k));
            } else {
                break;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        char a = 'a';
        int hash = a - 96;
        System.out.println("sss" + a);
    }
}
