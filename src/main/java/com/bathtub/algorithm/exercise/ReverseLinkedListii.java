package com.bathtub.algorithm.exercise;

import com.bathtub.algorithm.node.Node;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 *
 * @author 17031612
 * @date 2022/3/9
 */
public class ReverseLinkedListii {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right)
            return head;
        ListNode newHead = new ListNode(0, head);
        int index = 1;
        right++;
        ListNode fromN = null;
        ListNode toN = null;
        ListNode cur = newHead.next;
        ListNode pre = newHead;
        while (cur != null) {
            ListNode next = cur.next;
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
        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        ListNode head = new ListNode(3, new ListNode(5));
        ListNode node = reverseBetween(head, 1, 2);
        System.out.println(node);
    }
}
