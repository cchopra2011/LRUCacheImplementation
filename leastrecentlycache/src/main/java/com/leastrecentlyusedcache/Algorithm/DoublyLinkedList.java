package com.leastrecentlyusedcache.Algorithm;

import java.util.NoSuchElementException;

/**
 * An Object which support in creating a List with Non-contiguous Memory Location.
 * We can not access random element (like in Array List), but only if we have pointer to a node,
 * then we can traverse the list in both forward and direction in the list
 * 
 * @param <E> Type of element stored in list.
 */

public class DoublyLinkedList<E> {
    
    DoublyLinkedListNode<E> dummyHead;
    DoublyLinkedListNode<E> dummyTail;

    public DoublyLinkedList(){
        /*instantiating the list with dummy values, as we are never going to use the values */
        dummyHead = new DoublyLinkedListNode<>(null);
        dummyTail = new DoublyLinkedListNode<>(null);

        /*initially there are no items , therefore we will join
         * dummyHead and Tail .Also we can add the items in between them easily
         */

         dummyHead.next = dummyTail;
         dummyTail.prev = dummyHead;
    }

    /**
     * Method to detach a random node from the doubly linked list. 
     * The node itself will not be removed from the memory.
     * Just that it will be removed from the list and becomes orphaned.
     *
     * @param node Node to be detached.
     */
    public void detachNode(DoublyLinkedListNode<E> node){
         // Just Simply modifying the pointers.
        if(node!=null){
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
    }


    /**
     * Helper method to add an element at the end.
     *
     * @param element Element to be added.
     * @return Reference to new node created for the element.
     */
    public DoublyLinkedListNode<E> addElementAtLast(E element) {
        if (element == null) {
            //throw new InvalidElementException();
        }
        DoublyLinkedListNode<E> newNode = new DoublyLinkedListNode<>(element);
        addNodeAtLast(newNode);
        return newNode;
    }

    /**
     * Helper method to add a node at the end of the list.
     *
     * @param node Node to be added.
     */
    public void addNodeAtLast(DoublyLinkedListNode<E> node) {
        DoublyLinkedListNode tailPrev = dummyTail.prev;
        tailPrev.next = node;
        node.next = dummyTail;
        dummyTail.prev = node;
        node.prev = tailPrev;
    }

    public boolean isItemPresent(){
        return dummyHead.next != dummyTail;
    }

     public DoublyLinkedListNode getFirstNode() throws NoSuchElementException {
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyHead.next;
    }

    public DoublyLinkedListNode getLastNode() throws NoSuchElementException {
        DoublyLinkedListNode item = null;
        if (!isItemPresent()) {
            return null;
        }
        return dummyTail.prev;
    }
}
