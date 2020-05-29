package cn.drelang.java.advanced.io;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件基本操作
 * Created by Drelang on 2019/8/31 20:26
 */

public class FileTest {
    public static void main(String[] args) throws IOException {
//        String path = "C:\\Users\\drelang\\Documents\\Repository\\Java\\JavaOffer\\src\\main\\java\\cn\\drelang\\java\\advanced\\io";
        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
        File file = new File(path, "test.txt");
        if (!file.getParentFile().exists()) {   // 父路径不存在的话，就创建父路径
            file.getParentFile().mkdirs();
        }

        System.out.println("是否可读：" + file.canRead());
        System.out.println("是否可写：" + file.canWrite());
        System.out.println("是否可执行：" + file.canExecute());
        System.out.println("文件大小：" + file.length());
        System.out.println("上次修改日期：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified())));
    }
}
