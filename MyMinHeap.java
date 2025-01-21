/**
 * Name: Thatcher Eames
 * PID: A17284279
 * Sources Used: PA write-up, TA tutoring hours, CSE Style Guidelines
 * 
 * This file is used to create a miniumum heap data structure.
 */

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class stores data in a Minimum Heap format. No arg constructor creates 
 * an empty heap and the arg constructor can take any ordered collection and add
 * the elements to the heap. Data can be inserted, the minimum value can be
 * viewed, the minimum value can be removed, the size can be checked, and the
 * heap can be cleared.
 * 
 * Instance variables:
 * data - An array holding the variables stored in MyMinHeap
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E>{
    //Instance variable that is the underlying data structure for the class
    protected ArrayList<E> data;

    /**
     * No arg constructor that initializes MyMinHeap with no elements
     */
    public MyMinHeap() {
        data = new ArrayList<>();
    }
    /**
     * Constructor that takes in any data structure of type Collection and adds
     * all of the elements to the MinHeap. Throws an error if the data structure
     * or any of the elements are null
     * @param collection The data structure passed in that is added to MyMinHeap
     */
    public MyMinHeap(Collection<? extends E> collection) {
        //data = new ArrayList<>();
        if (collection == null || collection.contains(null)) 
            throw new NullPointerException();
        this.data = new ArrayList<>(collection);
        for (int i = this.size() - 1; i >= 0 ; i--) {
            percolateDown(i);
        }
    }

    /**
     * Helper method that takes in two indeces of the MinHeap and swaps the
     * elements at those indicies
     * @param from The 
     * @param to
     */
    protected void swap(int from, int to) {
        E fromElement = data.get(from);
        data.set(from , data.get(to));
        data.set(to , fromElement);
    }

    /**
     * Helper method that returns the index of the Parent node of the given 
     * index
     * @param index Index of the child of the desired parent
     * @return Index of the parent node
     */
    protected static int getParentIdx(int index) {
        return (index - 1) /2;
    }

    /**
     * Helper method that returns the index of the left child of the given index
     * 
     * @param index Index of the parent node with the desired left child
     * @return Index of the left child
     */
    protected static int getLeftChildIdx(int index) {
        return index * 2 + 1;
    }

    /**
     * Helper method that returns the index of the right child of the given
     * index
     * @param index Index of the parent node with the desired right child
     * @return Index of the right child
     */
    protected static int getRightChildIdx(int index) {
        return index * 2 + 2;
    }

    /**
     * Helper method that compares the left and right children of the parent
     * node at the specified index
     * @param index
     * @return
     */
    protected int getMinChildIdx(int index) {
        //variables store the indexs of the left child and right child
        int leftIdx = getLeftChildIdx(index);
        int rightIdx = getRightChildIdx(index);

        //Returns -1 if element is a leaf
        if (leftIdx >= size() && rightIdx >= size()) return -1;
        //Returns the left child's index if element does not have a right child
        else if (leftIdx < size() && rightIdx >= size()) return leftIdx;
        //Variables store the data at the idicies
        E leftChild = data.get(getLeftChildIdx(index));
        E rightChild = data.get(getRightChildIdx(index));
        //Returns the left child index if it is greater than or equal to right 
        if (rightChild.compareTo(leftChild) < 0) return getRightChildIdx(index);
        else return getLeftChildIdx(index);
    }
    
    /**
     * Helper method that moves an element up the tree until it no longer
     * violates the properties of a min tree
     * @param index Index of the element to be moved up
     */
    protected void percolateUp(int index) {
        int parentIdx = getParentIdx(index);
        int compareWithParent = data.get(index).compareTo(data.get(parentIdx));
        if (compareWithParent >= 0) return;
        else {
            swap(index , parentIdx);
            percolateUp(parentIdx);
        }
    }

    /**
     * Helper method that moves an element down the tree until it no longer
     * violates the properties of the tree
     * @param index Index of the element to be moved down
     */
    protected void percolateDown(int index) {
        int minChildIdx = getMinChildIdx(index);
        if (minChildIdx == -1) return;
        int compareWithChild = data.get(index).compareTo(data.get(minChildIdx));
        if (compareWithChild <= 0) return;
        else {
            swap(index , minChildIdx);
            percolateDown(minChildIdx);
        }
    }

    /**
     * Helper method that deletes the element at the specified index and returns
     * its value. Then replaces the element with the last most element in the
     * Heap and percilates up or down the element until the properties are
     * complete again
     * @param index Index of the element to be deleted
     * @return The value of the element that was deleted
     */
    protected E deleteIndex(int index) {
        E deletedData = data.get(index);
        if(index != size() - 1) swap(index , size() - 1);
        data.remove(size() - 1);
        if (index != size() - 1) {
            if(index != 0) percolateUp(index);
            percolateDown(index);
        }
        System.out.println(deletedData);
        return deletedData;
    }

    /**
     * Adds the specified element to the heap
     * @param element The element to be added to the heap
     */
    public void insert(E element) {
        if(element == null) throw new NullPointerException();
        data.add(element);
        percolateUp(size() - 1);
    }
    
    /**
     * Returns the smallest element in the heap
     * @return The element in the heap that is the smallest
     */
    public E getMin() {
        if (size() == 0) return null;
        return data.get(0);
    }

    /**
     * Removes the smallest element from the heap and returns it
     * @return The smallest element in the heap that was removed
     */
    public E remove() {
        if (size() == 0) return null;
        E deletedElement = data.get(0);
        deleteIndex(0);
        return deletedElement;
    }

    /**
     * Returns the number of elements that are in the heap
     * @return Number of elements in the heap
     */
    public int size() {
        return data.size();
    }
    /**
     * Deletes all the elements in the heap
     */
    public void clear() {
        data.clear();
    }

}
