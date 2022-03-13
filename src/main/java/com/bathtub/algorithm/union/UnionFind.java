package com.bathtub.algorithm.union;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 并查集
 * @author 17031612
 * @date 2021/12/29
 */
public class UnionFind {

    private static HashMap<String, LinkedList<String>> union = new HashMap<>();

    /**
     * 集合合并
     */
    private static void mergeArr(String[] strArr) {
        for (int i = 1;i < strArr.length; i++) {
            union(strArr[i], strArr[i-1]);
        }
    }

    private static void union(String s1, String s2) {
        String root1 = findRoot(s1);
        String root2 = findRoot(s2);
        if (root1.equals(root2)) {
            // 根节点相同
            return;
        }
        LinkedList<String> s1List = union.get(s1);
        LinkedList<String> s2List = union.get(s2);
        LinkedList<String> newList = new LinkedList<>();
        if (s1List.size() <= s2List.size()) {
            // 更新S1节点信息
            newList.addAll(s2List);
            newList.add(s1);
            union.put(s1, newList);
        } else {
            newList.addAll(s1List);
            newList.add(s2);
            union.put(s2, newList);
        }
    }

    /**
     * 优化树高
     * 导致树高实主为2
     * @author 17031612
     * @date 2021/12/29
     */
    private static void changeHeight(LinkedList<String> list) {
        String root = list.getFirst();
        for (int i = 2; i < list.size(); i++) {
            int index = i - 2;
            LinkedList<String> newList = new LinkedList<>();
            newList.add(root);
            while (index > 0) {
                newList.add(list.get(index));
                index -= 2;
            }
            newList.add(list.get(i));
            union.put(list.get(i), newList);
        }
    }

    private static String findRoot(String str) {
        checkNull(str);
        LinkedList<String> strList = union.get(str);
        return strList.getFirst();
    }

    private static void checkNull(String str) {
        if (!union.containsKey(str)) {
            LinkedList<String> selfList = new LinkedList<>();
            selfList.add(str);
            union.put(str, selfList);
        }
    }

    private static boolean isSame(String s1, String s2) {
        String root1 = findRoot(s1);
        String root2 = findRoot(s2);
        if (root1.equals(root2)) {
            // 根节点相同
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
//        String[] strArr = new String[]{"2", "3", "4", "2", "1", "7", "8", "9", "10", "A", "B"};
//        mergeArr(strArr);
//        System.out.println(isSame("4", "B"));
        UnionFindNode unionFindNode = new UnionFindNode<String>();
        unionFindNode.init("2");
        unionFindNode.init("3");
        unionFindNode.init("B");
        unionFindNode.init("A");
        unionFindNode.init("1");
        unionFindNode.init("5");
        unionFindNode.init("6");
        unionFindNode.init("C");
        unionFindNode.union("2", "B");
        unionFindNode.union("2", "3");
        unionFindNode.union("1", "B");
        unionFindNode.union("A", "3");
        unionFindNode.union("C", "5");
        unionFindNode.union("C", "6");
        System.out.println(unionFindNode.find("C"));
        System.out.println(unionFindNode.isSame("C", "2"));
    }
}
