/**
 * Name: Thatcher Eames
 * PID: A17284279
 * Sources Used: PA Write up, TA tutoring hours, CSE12 Style guidelines
 * 
 * This file is used to create a priority queue data structure. It utilizes the
 * MyMinHeap for its underlying data stucture
 */

import java.util.Collection;

/**
 * This class stores data in a priority queue format. No arg constructor creates 
 * an empty queue and the arg constructor can take any ordered collection and
 * add the elements to the queue. Data can be pushed, the highest priority 
 * element viewed, the minimum value can be removed, the size can be checked, 
 * and the queue can be cleared.
 * 
 * Instance variables:
 * heap - An miniumum heap holding the variables stored in MyPriorityQueue
 */
public class MyPriorityQueue<E extends Comparable<E>> {
    protected MyMinHeap<E> heap;

    /**
     * No arg constructor that creates an empty MyPriorityQueue
     */
    public MyPriorityQueue() {
        heap = new MyMinHeap<E>();
    }

    /**
     * Arg constructor that takes an Comparable Collection list and adds the
     * elements to the intial MyPriorityQueue
     * @param collection
     */
    public MyPriorityQueue(Collection<? extends E> collection) {
        heap = new MyMinHeap<E>(collection);
    }

    /**
     * Adds an element into the MyPriorityQueue
     * @param element The element to be added
     */
    public void push(E element) {
        if (element == null) throw new NullPointerException();
        heap.insert(element);
    }

    /**
     * Returns the element at the front of the queue without altering the queue.
     * @return The element at the front of the queue
     */
    public E peek(){
        return heap.getMin();
    }

    /**
     * Deletes and returns the element at the front of the MyPriorityQueue
     * @return The element that was deleted from the front of the queue
     */
    public E pop() {
        return heap.remove();
    }

    /**
     * Returns the number of elements in the MyPriorityQueue
     * @return
     */
    public int getLength() {
        return heap.size();
    }

    /**
     * Deletes all of the elements from the MyPriorityQueue
     */
    public void clear() {
        heap.clear();
    }
}
