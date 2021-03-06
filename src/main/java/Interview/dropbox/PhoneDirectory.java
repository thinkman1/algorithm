package Interview.dropbox;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 379. Design Phone Directory
 * Design a Phone Directory which supports the following operations:
 * get: Provide a number which is not assigned to anyone.
 * check: Check if a number is available or not.
 * release: Recycle or release a number.
 *
 * queue + HashSet, O(1) time, O(n) space
 */
public class PhoneDirectory {

    Set<Integer> used = new HashSet<>();
    Queue<Integer> available = new LinkedList<>();
    int minAvailable = 0;
    int max = 0;

    /**
     * Initialize your data structure here
     *
     * @param maxNumbers - The maximum numbers that can be stored in the phone directory.
     */
    public PhoneDirectory(int maxNumbers) {
        //O(n) for constructor
//        for (int i = 0; i < maxNumbers; i++) {
//            available.offer(i);
//        }
        minAvailable = 0;  //O(1) for constructor
        this.max = maxNumbers;
    }

    /**
     * Provide a number which is not assigned to anyone.
     *
     * @return - Return an available number. Return -1 if none is available.
     */
    //O(1)
    public int get() {
        if (minAvailable > max && available.isEmpty()) {
            return -1;
        } else {
            if (available.isEmpty()) {
                available.offer(minAvailable);
                minAvailable++;
            }

            int num = available.poll();
            used.add(num);

            return num;
        }
    }

    /**
     * Check if a number is available or not.
     */
    public boolean check(int number) {
        if (number < 0 || number >= this.max) {
            return false;
        }

        return !used.contains(number);
    }

    /**
     * Recycle or release a number.
     */
    public void release(int number) {
        if (used.contains(number)) {
            used.remove(number);
            available.offer(number);
        }
    }

}
