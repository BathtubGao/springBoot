package com.bathtub.algorithm.exercise;

import java.util.Arrays;

/**
 * 给定节点数为 n 二叉树的前序遍历和中序遍历结果，请重建出该二叉树并返回它的头结点。
 * @author 17031612
 * @date 2021/12/24
 */
public class JZ7 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static TreeNode reConstructBinaryTree(int [] pre,int [] vin) {
        if (pre.length == 0 || vin.length == 0) {
            return null;
        }
        if (pre.length == 1) {
            return new TreeNode(pre[0]);
        }
        int root = pre[0];
        TreeNode treeNode = new TreeNode(root);
        int rootIndex = 0;
        for (int i = 0; i < vin.length; i++) {
            if (root == vin[i]) {
                rootIndex = i;
                break;
            }
        }
        int[] leftVin = Arrays.copyOfRange(vin, 0, rootIndex);
        int[] rightVin = Arrays.copyOfRange(vin, rootIndex+1, vin.length);
        int[] leftPre = Arrays.copyOfRange(pre, 1, rootIndex + 1);
        int[] rightPre = Arrays.copyOfRange(pre, rootIndex+1, vin.length);
        TreeNode leftNode = reConstructBinaryTree(leftPre, leftVin);
        TreeNode rightNode = reConstructBinaryTree(rightPre, rightVin);
        treeNode.left = leftNode;
        treeNode.right = rightNode;
        return treeNode;
    }

    public static void main(String[] args) {
        int [] pre = {1,2,4,7,3,5,6,8};
        int [] vin = {4,7,2,1,5,3,8,6};
        TreeNode treeNode = reConstructBinaryTree(pre, vin);
        System.out.println(treeNode);
    }
}
