package com.bathtub.algorithm.node;

public class Node<V> {

    private V value;

    public Node<V> next;

    public Node(V value) {
        this.value = value;
    }

    public Node() {
    }

    public Node(V value, Node<V> next) {
        this.value = value;
        this.next = next;
    }
}
