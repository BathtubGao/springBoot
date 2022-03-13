package com.bathtub.algorithm.exercise;

/**
 * 给定一个二叉树其中的一个结点，请找出中序遍历顺序的下一个结点并且返回
 * @author 17031612
 * @date 2021/12/24
 */
public class JZ8 {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    public TreeLinkNode getNext(TreeLinkNode pNode) {
        if (null != pNode.right) {
            pNode = pNode.right;
            while (null != pNode.left) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (null != pNode.next) {
            if (pNode == pNode.next.left) {
                return pNode.next;
            } else if (pNode == pNode.next.right) {
                pNode = pNode.next;
            }
        }
        return null;
    }
}
