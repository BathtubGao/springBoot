package com.bathtub.algorithm.exercise;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \      \
 *         7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 * @author 17031612
 * @date 2022/2/15
 */
public class HasPathSum {

    static class TreeNode {
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    private static boolean hasPathSum(TreeNode root, int sum){
        if (null == root) {
            return false;
        }
        if (sum < 0 || root.value > sum) {
            return false;
        }
        if (sum == 0) {
            return true;
        }
        int lest = sum -root.value;
        if (root.left != null) {
            if (hasPathSum(root.left, lest)) {
                return true;
            }
        }
        if (root.right != null) {
            if (hasPathSum(root.right, lest)) {
                return true;
            }
        }
        if (root.value == sum) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);

        TreeNode nood1 = new TreeNode(1);
        TreeNode nood2= new TreeNode(2);
        TreeNode nood7 = new TreeNode(7);
        TreeNode nood4 = new TreeNode(4);
        TreeNode nood13 = new TreeNode(13);
        TreeNode nood11 = new TreeNode(11);
        TreeNode nood8 = new TreeNode(8);
        TreeNode nood = new TreeNode(4);
        TreeNode nood5= new TreeNode(5);
        nood4.right = nood1;
        nood11.left = nood7;
        nood11.right = nood2;
        nood.left = nood11;
        nood5.left = nood;
        nood5.right = nood8;
        nood8.left = nood13;
        nood8.right = nood4;
        System.out.println(hasPathSum(nood5, 100));
    }
}
