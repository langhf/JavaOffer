package cn.drelang.exam.huawei8_21;

import java.util.*;

/**
 * 给你一个人的名字，该人在一个群里，向群里发了一条消息，然后每个收到消息的人给自己所在的群再发消息，依次类推。
 * 问最后有多少个人接收到消息，同一个人重复接收消息只算一个人。这是个并查集的问题。
 *
 * 示例：
 *  例1： 输入： Jack // 初始发消息的人，总共 8 个人
 *              3 // 群的数量
 *              Jack, Lily, Lucy, Mick, Mike
 *              Lily, Mick, Jane
 *              Michale, Lance
 *        输出: 6
 *  例2： 输入： Jack
 *              3
 *              Tom,Mary
 *              Jack,Lily
 *              Lily,Tom
 *       输出： 4
 *
 * Created by Drelang on 2019/9/3 20:10
 */

public class Problem3 {
    static class UnionFind {
        int[] parents;

        UnionFind(int cap) {
            parents = new int[cap];
            // 注意要初始化该数组!!!
            for (int i=0; i<cap; i++) {
                parents[i] = i;
            }
        }

        int find(int node) {
            while (node != parents[node]) {
                parents[node] = parents[parents[node]];
                node = parents[node];
            }
            return node;
        }

        void union(int m, int n) {
            int p1 = find(m);
            int p2 = find(n);
            if (p1 != p2) {
                parents[p1] = p2;
            }
        }

        boolean isConnected(int m, int n) {
            return find(m) == find(n);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String first = sc.nextLine().trim();
            int N = Integer.parseInt(sc.nextLine().trim());
            // 构建 group 和名单录
            List<List<String>> groups = new ArrayList<>();
            List<String> names = new ArrayList<>();
            for (int i=0; i<N; i++) {
                String[] ss = sc.nextLine().split(",");
                List<String> tmp = new ArrayList<>(Arrays.asList(ss));
                groups.add(tmp);
                for (String name : tmp) {
                    if (!names.contains(name)) {    // 同一个人只加入一次，模拟set，主要是用 list 的 indexOf 函数
                        names.add(name);
                    }
                }
            }

            UnionFind un = new UnionFind(names.size()+1);
            int notify = names.size();
            // 一个区块的构建到一起
            for (List<String> group : groups) {
                String curName=null, nextName=null;
                for (int i=0; i<group.size()-1; i++) {
                    curName = group.get(i);
                    nextName = group.get(i+1);
                    if (curName.equals(first)) {
                        un.union(names.indexOf(curName), notify);
                    }
                    un.union(names.indexOf(curName), names.indexOf(nextName));
                }
            }
            // 统计通知到的人
            Set<String> set = new HashSet<>();
            for (List<String> group : groups) {
                for (String name : group) {
                    if (un.isConnected(names.indexOf(name), notify)) {
                        set.add(name);
                    }
                }
            }

            System.out.println(set.size());
        }
    }
}
