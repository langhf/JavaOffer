package cn.drelang.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Created by Drelang on 2019/8/31 20:26
 */

public class FileTest {
    public static void main(String[] args) throws IOException {
        int N = 1024;
        byte[] buffer = new byte[N];

        // 文件输入流
        String path = "C:\\Users\\drelang\\Documents\\Repository\\Java\\JavaOffer\\src\\main\\java\\cn\\drelang\\io";
        FileInputStream fin = new FileInputStream(new File(path, "test.txt"));
        while (fin.read(buffer, 0, N) != -1) {
            System.out.println(Arrays.toString(buffer));
        }
        fin.close();

        // 读取键盘输入，输出到文件
        FileOutputStream fout = new FileOutputStream(new File(path, "outFile.txt"));
        int count;
        count = System.in.read(buffer);
        fout.write(buffer, 0, count);
        fout.close();

        // 复制文件
        fin = new FileInputStream(new File(path, "test.txt"));
        fout = new FileOutputStream(new File(path, "test_copy.txt"));
        int c;
        while ((c = fin.read(buffer, 0, N)) != -1) {
            fout.write(buffer, 0, c);
        }
        fin.close();
        fout.close();
    }
}
