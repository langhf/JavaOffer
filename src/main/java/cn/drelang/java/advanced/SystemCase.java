package cn.drelang.java.advanced;

/**
 * System 类里面提供的一些方法。
 *   arraycopy() 复制数组
 *   currentTimeMillis() 当前毫秒数
 *   gc() 垃圾回收. 实际上是 Runtime.getRuntime().gc()
 * Created by Drelang on 2020/05/20 22:25
 */

public class SystemCase {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Runtime run = Runtime.getRuntime();
        String str = "";
        for (int x=0; x<30000; x++) {
            str += x;
        }
        long end = System.currentTimeMillis();
        System.out.println("操作耗时:" + (end - start));
    }
}

