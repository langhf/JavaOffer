package cn.drelang.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU Cache
 *
 * @author Drelang
 * @date 2021/3/27 22:37
 */

public class Problem_0146 {
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;
    private Integer capacity;
    private Integer used;

    private static class Node {
        private Node pre, next;
        private int key, val;   // Node 应该存 key 和 value，方便后续处理

        public Node() {}

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    // 将 node 节点移到链表头部
    private void moveToHead(Node node) {
        Node t = head.next;
        head.next = node;
        node.pre = head;
        node.next = t;
        t.pre = node;
    }

    // 从链表中移除 node 节点
    private void removeNode(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public Problem_0146(int capacity) {
        this.capacity = capacity;
        this.used = 0;
        // 初始化链表头和链表尾
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        // 初始化 map
        map = new HashMap<>();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) return -1;
        removeNode(node);
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) { // key 存在
            // 链表移除旧节点
            removeNode(map.get(key));
            // 存放新节点
            Node node = new Node(key, value);
            map.put(key, node);
            moveToHead(node);
        } else {    // key 不存在
            if (used.equals(capacity)) { // 达到容量上限则先移除末尾元素，注意是 map 和链表都要移除
                Node toRemove = tail.pre;
                map.remove(toRemove.key);
                removeNode(toRemove);
                used--;
            }
            Node node = new Node(key, value);
            map.put(key, node);
            moveToHead(node);
            used++;
        }
    }

    public static void main(String[] args) {
        Problem_0146 lRUCache = new Problem_0146(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4

    }
}

