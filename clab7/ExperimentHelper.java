import javax.swing.*;
import java.util.Random;

/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        if (N == 1) {
            return 0;
        }
        return optimalIPL(N-1) + layer(N);
    }

    private static int layer(int N) {
        int count = 0;
        while (N / 2 != 0) {
            count += 1;
            N /= 2;
        }
        return count;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;
    }

    public static void randomInsert(BST<Double> x) {
        Random r = new Random();
        double tmp = r.nextDouble();
        while (x.contains(tmp)) {
            tmp = r.nextDouble();
        }
        x.add(tmp);
    }

    public static void randomDelete(BST<Double> x) {
        double tmp = x.getRandomKey();
        x.deleteTakingSuccessor(tmp);
    }

    public static void randomDeleteRandom(BST<Double> x) {
        double tmp = x.getRandomKey();
        x.deleteTakingRandom(tmp);
    }


    public static void main(String[] args) {
        System.out.println(1/2);
    }
}
