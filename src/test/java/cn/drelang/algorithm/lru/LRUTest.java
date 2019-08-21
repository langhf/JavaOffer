package cn.drelang.algorithm.lru;

import org.junit.Test;

import static org.junit.Assert.*;

public class LRUTest {

    @Test
    public void testLRU1() {
        LRU1<Character, Integer> lru = new LRU1<>(16, 0.75f, true, 6);
        String s = "abcdefghijkl";
        for (int i = 0; i < s.length(); i++) {
            lru.put(s.charAt(i), i);
        }
        System.out.println("LRU中key为h的Entry的值为： " + lru.get('h'));
        System.out.println("LRU的大小 ：" + lru.size());
        System.out.println("LRU ：" + lru);
    }
}