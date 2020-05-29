package cn.drelang.java.advanced.io;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * RandomAccessFile 可以实现读取大文件中指定部分内容。
 * Created by Drelang on 2020/05/28 15:27
 */

public class RandomAccessFileTest {
    public static void main(String[] args) throws Exception {
        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
        File file = new File(path, "random_access_file.txt");

        RandomAccessFile raf = new RandomAccessFile(file, "rw");    // 读写模式
        // 按格式写入内容
        String[] names = new String[] {
                "zhangsan", "wangwu  ", "lisi    "
        };
        int[] ages = new int[] {
                30, 20, 16
        };

        for (int x=0; x<names.length; x++) {
            raf.write(names[x].getBytes());
            raf.writeInt(ages[x]);
        }
        raf.close();
        // 读取 "李四" 的数据，跳过位
        RandomAccessFile raf1 = new RandomAccessFile(file, "rw");
        raf1.skipBytes(24); // 向后跳过 24 个字节
        byte[] data = new byte[8];
        int len = raf1.read(data);
        System.out.println("姓名：" + new String(data, 0, len).trim() + ", 年龄：" + raf1.readInt());
    }
}

