//package cn.drelang.java.advanced;
//
///**
// * Runtime 是单例，一个JVM内一个，需要通过 getRuntime()获取。
// * 常用函数：
// *      Runtime.getRuntime()
// *      maxMemory(), 最大可用内存 -- 默认配置为本机系统内存的 1/4
// *      totalMemory(), JVM总内存 -- 默认配置为本机系统内存的 1/64
// *      freeMemory(), 空闲内存
// *      gc(), 进行垃圾收集
// * Created by Drelang on 2020/05/20 20:13
// */
//
//public class RuntimeCase {
//    public static void main(String[] args) throws Exception {
//        System.out.println("Runtime版本：" + Runtime.version());
//        Runtime runtime = Runtime.getRuntime();
//        System.out.println("可用处理器内核数：" + runtime.availableProcessors());
//
//        System.out.println("-------------------------------");
//        System.out.println("最大可用内存：" + runtime.maxMemory());
//        System.out.println("JVM总内存：" + runtime.totalMemory());
//        System.out.println("空闲内存：" + runtime.freeMemory());
//
//        String s = "";
//        for (int x=0; x<30000; x++) {
//            s += x;
//        }
//        System.out.println("-------------------------------");
//        System.out.println("最大可用内存：" + runtime.maxMemory());
//        System.out.println("JVM总内存：" + runtime.totalMemory());
//        System.out.println("空闲内存：" + runtime.freeMemory());
//
//        Thread.sleep(200);
//        runtime.gc();
//        System.out.println("-------------------------------");
//        System.out.println("最大可用内存：" + runtime.maxMemory());
//        System.out.println("JVM总内存：" + runtime.totalMemory());
//        System.out.println("空闲内存：" + runtime.freeMemory());
//    }
//}
//
