package com.dsa.phase3.linkedList;

public class LinkedList {

    private Node head;
    private Node tail;
    private int size;

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    LinkedList(int data) {
        Node newNode = new Node(data);
        this.head = newNode;
        this.tail = newNode;
        this.size = 1;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public int getSize() {
        return this.size;
    }

    public void printLinkedList() {
        Node temp = this.head;

        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public void append(int data) {
        Node newNode = new Node(data);

        if (this.size == 0) {
            this.head = newNode;
        } else {
            tail.next = newNode;
        }
        this.tail = newNode;
        this.size++;
    }

    public void prepend(int data) {
        Node newNode = new Node(data);
        if (this.size == 0) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            newNode.next = this.head;
            head = newNode;
        }
        this.size++;
    }

    public void removeLast() {
        if (this.size == 0) {
            return;
        }

        if (this.size == 1) {
            this.head = null;
            this.tail = null;
            return;
        }

        Node temp = this.head;

        while (temp.next.next != null) {
            temp = temp.next;
        }
        temp.next = null;
        this.tail = temp;
        this.size--;
    }

    public void removeFirst() {
        if (this.size == 0) {
            return;
        }
        if (this.size == 1) {
            this.head = null;
            this.tail = null;
            return;
        }

        Node temp = this.head.next;
        this.head.next = null;
        this.head = temp;
        this.size--;

        if (this.size == 1) {
            this.tail.next = null;
        }
    }

    public Node findNodeByIndex(int index) {
        Node temp = this.head;

        if (index > this.size || index < 0) {
            return null;
        }

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void setNodeByIndex(int index, int data) {
        Node temp = findNodeByIndex(index);
        if (temp == null) {
            return;
        }
        temp.data = data;
    }

    public void insertNodeAtIndex(int index, int data) {
        if (index < 0 || index > this.size) {
            return;
        }

        if (index == 0) {
            prepend(data);
        } else if (index == this.size) {
            append(data);
        } else {
            Node temp = findNodeByIndex(index - 1);
            Node newNode = new Node(data);
            newNode.next = temp.next;
            temp.next = newNode;
            this.size++;
        }
    }

    public void removeNodeAtIndex(int index) {
        if (index < 0 || index > this.size) {
            return;
        }

        if (index == 0) {
            removeFirst();
        } else if (index == this.size) {
            removeLast();
        } else {
            Node temp = findNodeByIndex(index - 1);
            Node nodeToRemove = temp.next;
            temp.next = nodeToRemove.next;
            nodeToRemove.next = null;
            this.size--;
        }
    }

    public void reverse() {
        Node temp = this.head;
        Node prev = null;
        Node next;

        while (temp != null) {
            next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
    }
}
