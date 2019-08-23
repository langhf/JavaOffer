package cn.drelang.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 参考：https://www.cnblogs.com/lzrabbit/p/3734850.html
 *
 * Created by Drelang on 2019/08/18 15:12
 */

public class LRU {
}

class LRU1<k, v> extends LinkedHashMap<k, v> {
    private final int CAP;
    // accessOrder 为 true 的时候，按照访问次数排序
    // accessOrder 为 false 的时候，按照插入顺序排序
    LRU1(int initCapicity, float loadFactor, boolean accessOrder, int cap) {
        super(initCapicity, loadFactor, accessOrder);
        this.CAP = cap;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<k, v> eldest) {
        return size() > CAP;
    }
}

class LRU2 {
    private class Node {
        Integer val;
        Node next;
        Node(Integer val) {
            this.val = val;
        }
    }

    private  int CAP;
    private  Node dummyHead;
    private int size;

    LRU2(int cap) {
        this.CAP = cap;
        dummyHead = new Node(0);
    }

}