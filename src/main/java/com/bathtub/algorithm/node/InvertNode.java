package com.bathtub.algorithm.node;

public class InvertNode {

    public static void invert(Node<Integer> node) {
        Node<Integer> pre = node.next;
        Node<Integer> cur = pre.next;
        pre.next = null;
        while (cur != null) {
            Node<Integer> next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        node.next = pre;
    }

    /**
     * 左闭右开
     * @author 17031612
     * @date 2022/3/9
     */
    public static void invert(Node<Integer> node, int from, int to) {
        int index = 1;
        Node<Integer> fromN = null;
        Node<Integer> toN = null;
        Node<Integer> cur = node.next;
        Node<Integer> pre = cur;
        while (cur != null) {
            Node<Integer> next = cur.next;
            if (index < from) {
                cur = next;
            } else if (index == from) {
                fromN = cur;
                fromN.next = null;
                toN = fromN;
                cur = next;
            } else if (index > from && index < to) {
                cur.next = fromN;
                fromN = cur;
                cur = next;
            } else if (index == to) {
                toN.next = cur;
                cur = next;
            } else if (index > to) {
                cur = next;
            }
            index++;
        }
        pre.next = fromN;
    }

    public static void main(String[] args) {
        Node<Integer> node = new Node<>();
        node.next = new Node<>(1);
        node.next.next = new Node<>(2);
        node.next.next.next = new Node<>(3);
        node.next.next.next.next = new Node<>(4);
        invert(node, 2, 4);
        System.out.println(node);
    }
}
