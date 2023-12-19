package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correctList = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();

        int nItem = 3;
        for (int i = 0; i < nItem; i++) {
            correctList.addLast(i + 1);
            buggyAList.addLast(i + 1);
        }

        assertEquals(correctList.size(), buggyAList.size());
        for (int i = 0; i < correctList.size(); i++) {
            assertEquals(correctList.get(i), buggyAList.get(i));
        }

        for (int i = 0; i < nItem; i++) {
            correctList.removeLast();
            buggyAList.removeLast();
            assertEquals(correctList.size(), buggyAList.size());
            for (int j = 0; j < correctList.size(); j++) {
                assertEquals(correctList.get(j), buggyAList.get(j));
            }
        }

        assertEquals(0, buggyAList.size());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buggyAList.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
//                System.out.println("size: " + size);
            } else if (operationNumber == 2 && L.size() > 0) {
                assertEquals(L.size(), buggyAList.size());
                assertEquals(L.getLast(), buggyAList.getLast());
            } else if (operationNumber == 3 && L.size() > 0) {
                L.removeLast();
                buggyAList.removeLast();
            }
        }
    }
}
