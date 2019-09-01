package cn.drelang.io;

import java.io.*;

/**
 * 测试 BufferedReader 和 BufferedWriter
 *
 * Created by Drelang on 2019/9/1 10:16
 */

public class BufferedTest {
    public static void main(String[] args) throws Exception {
        String path = "C:\\Users\\drelang\\Documents\\Repository\\Java\\JavaOffer\\src\\main\\java\\cn\\drelang\\io\\";

        InputStreamReader sin = new InputStreamReader(System.in);
        BufferedReader bin = new BufferedReader(sin);

        FileWriter fw = new FileWriter(path + "buffered_test.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        try {
            String s;
            while ((s = bin.readLine()).length() > 0) {
                bw.write(s);
            }
        } finally {
            // 一定要关闭缓冲区
            bw.flush();
            bw.close();
        }
    }
}
