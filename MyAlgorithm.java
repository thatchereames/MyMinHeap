/**
 * Name: Thatcher Eames
 * PID: A17284279
 * Sources Used: PA Write up, TA tutoring hours, CSE12 Style guidelines
 * 
 * This file is used to create a algorithm that finds the kth largest item in a
 * list. Utilizes MyPriorityQueue as its underlying data structure. 
 */

import java.util.ArrayList;

/**
 * This class is an algorithm that takes an unsorted array and an integer and 
 * returns the integer place largest element in the array
 */
public class MyAlgorithm {
    
    /**
     * This method takes an unsorted array and an integer and returns the kth
     * largest element in the array, where k is determined by the integer input.
     * @param list List with the desired element
     * @param k Represents what largest place element is desired
     * @return The element that is the kth largest in the list
     */
    public static Integer getKthLargest(ArrayList<Integer> list, int k) {
        if(list == null) throw new NullPointerException();
        if(list.size() == 0) throw new IllegalArgumentException();
        if(k <= 0 || k > list.size()) throw new IllegalArgumentException();
        //ArrayList<Integer> kList = list.subList(0, k + 1);
        MyPriorityQueue<Integer> pq = new MyPriorityQueue<>();
        for (int i = 0 ; i < list.size() ; i++) {
            if(i <= k - 1) pq.push(list.get(i));
            else if (list.get(i) > pq.peek()) {
                pq.pop();
                pq.push(list.get(i));
            }
            System.out.println(pq.peek());
        }
        return pq.peek();
    }
}
