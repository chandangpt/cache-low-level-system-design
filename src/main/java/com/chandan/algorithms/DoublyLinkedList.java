package com.chandan.algorithms;

import com.chandan.algorithms.exceptions.InvalidElementException;

public class DoublyLinkedList<E> {
    private DoublyLinkedListNode<E> dummyHead;
    private DoublyLinkedListNode<E> dummyTail;

    public DoublyLinkedList() {
        dummyHead = new DoublyLinkedListNode(null);
        dummyTail = new DoublyLinkedListNode(null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public void detachNode(DoublyLinkedListNode<E> node) {
        if(node != null) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
    }

    public void addNodeAtLast(DoublyLinkedListNode<E> node) {
        DoublyLinkedListNode tail = dummyTail.prev;
        tail.next = node;
        node.prev = tail;
        node.next = dummyTail;
        dummyTail.prev = node;
    }

    public DoublyLinkedListNode<E> addElementAtLast(E element) {
        if(element == null) {
            throw new InvalidElementException();
        }
        DoublyLinkedListNode<E> node = new DoublyLinkedListNode<E>(element);
        addNodeAtLast(node);
        return node;
    }

    public boolean isItemPresent() {
        return dummyHead.next != dummyTail;
    }

    public DoublyLinkedListNode getFirstNode() {
        if(!isItemPresent()) {
            return null;
        }
        return dummyHead.next;
    }

    public DoublyLinkedListNode getLastNode() {
        if(!isItemPresent()) {
            return null;
        }
        return dummyTail.prev;
    }
}
