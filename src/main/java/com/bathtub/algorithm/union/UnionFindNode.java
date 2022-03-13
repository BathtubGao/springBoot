package com.bathtub.algorithm.union;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class UnionFindNode <T>{
    private Map<T, Node<T>> nodes = new HashMap<>();

    public void init(T t) {
        nodes.put(t, new Node<T>(t));
    }

    public T find(T t) {
        Node<T> node = findNode(t);
        return null == node ? null : node.value;
    }

    private Node<T> findNode(T t) {
        Node<T> node = nodes.get(t);
        if (node == null) {
            return null;
        }
        while (!Objects.equals(node.value, node.parent.value)) {
            node.parent = node.parent.parent; // 指向祖父节点
            node = node.parent;
        }
        return node;
    }

    public void union(T t1, T t2) {
        Node<T> node1 = findNode(t1);
        Node<T> node2 = findNode(t2);
        if (null == node1 || null == node2) {
            return;
        }
        if (Objects.equals(node1.value, node2.value)) {
            return;
        }

        if (node1.rank < node2.rank) {
            node1.parent = node2;
        } else if (node1.rank > node2.rank) {
            node2.parent = node1;
        } else {
            node1.parent = node2;
            node1.rank += 1;
        }
    }

    public boolean isSame(T t1, T t2) {
        return Objects.equals(find(t1), find(t2));
    }

    private class Node<T> {
        T value;
        Node<T> parent = this;
        int rank = 1;

        public Node(T t) {
            this.value = t;
        }
    }
}
