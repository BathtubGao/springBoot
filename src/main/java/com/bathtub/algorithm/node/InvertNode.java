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
    public static Node invert(Node<Integer> head, int left, int right) {
        if (left == right)
            return head;
        Node newHead = new Node(0, head);
        int index = 1;
        right++;
        Node fromN = null;
        Node toN = null;
        Node cur = newHead.next;
        Node pre = newHead;
        while (cur != null) {
            Node next = cur.next;
            if (index == left - 1) {
                pre = cur;
            } else if (index == left) {
                fromN = cur;
                fromN.next = null;
                toN = fromN;
            } else if (index > left && index < right) {
                cur.next = fromN;
                fromN = cur;
            } else if (index == right) {
                toN.next = cur;
                pre.next = fromN;
            }
            cur = next;
            index++;
        }
        if (index == right) {
            toN.next = cur;
            pre.next = fromN;
        }
        return newHead.next;
    }

    public static void main(String[] args) {
        Node<Integer> node = new Node<>(1, new Node<>(2, new Node<>(3, new Node<>(4, new Node<>(5)))));
        Node<Integer> newNode = invert(node, 2, 3);
        System.out.println(newNode);
    }
}
