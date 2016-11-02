package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Find Median from Data Stream
 */
public class MedianFinder {

    //The size of two heaps differs at most 1. maxHeap size always >= minHeap size
    PriorityQueue<Integer> maxHeap; //lower half
    PriorityQueue<Integer> minHeap; //higher half

    public MedianFinder() {
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        minHeap = new PriorityQueue<>();
    }

    //time complexity: O(log(n))
    public void addNum(int num) {
        maxHeap.offer(num);
        int max = maxHeap.poll();
        minHeap.offer(max);

        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    //time complexity: O(1)
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek())/2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        MedianFinder finder = new MedianFinder();
        int[] numbers = {2,3,4};

        for (int num : numbers) {
            finder.addNum(num);
        }

        double result = finder.findMedian();
        System.out.println(result);
    }
}