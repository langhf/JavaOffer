package cn.drelang.java.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Drelang on 2019/9/1 10:42
 */

public class EncodeTest {
    private static void readBuff(byte[] buff) throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(buff);
        int data;
        while ((data = in.read()) != -1) {
            System.out.print(data + " ");
        }
        System.out.println();
        in.close();
    }

    public static void main(String[] args) throws IOException {
        System.out.println("内存中采用 unicode 字符编码：");
        char c = '好';
        int lowBit = c & 0xFF;
        int highBit = (c & 0xFF00) >> 8;
        System.out.println("lowbit=" + lowBit + ", highBit=" + highBit);

        String s = "好";
        System.out.println("本地操作系统默认字符编码：");
        readBuff(s.getBytes());

        System.out.println("采用 GBK 字符编码：");
        readBuff(s.getBytes("GBK"));

        System.out.println("采用 UTF-8 字符编码：");
        readBuff(s.getBytes(StandardCharsets.UTF_8));
    }
}
