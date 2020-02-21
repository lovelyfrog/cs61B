import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void test1() {
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();
        int length = 0;
        String wrongString = "\n";
        for (int i=0; i<100; i++) {
            double randNumber = StdRandom.uniform();
            if (randNumber < 0.25) {
                stu.addFirst(i);
                sol.addFirst(i);
                length += 1;
                wrongString = wrongString + "addFirst(" + String.valueOf(i) + ")" + "\n";
            } else if (randNumber >= 0.25 && randNumber < 0.5){
                stu.addLast(i);
                sol.addLast(i);
                length += 1;
                wrongString = wrongString + "addLast(" + String.valueOf(i) + ")" + "\n";
            } else if (randNumber >= 0.5 && randNumber < 0.75) {
                if (length > 0) {
                    Integer actual = stu.removeFirst();
                    Integer expected = sol.removeFirst();
                    length -= 1;
                    wrongString = wrongString + "removeFirst()" + "\n";
                    assertEquals(wrongString, expected, actual);
                }
            } else {
                if (length > 0) {
                    Integer actual = stu.removeLast();
                    Integer expected = sol.removeLast();
                    length -= 1;
                    wrongString = wrongString + "removeLast()" + "\n";
                    assertEquals(wrongString, expected, actual);
                }
            }
        }
    }
}
