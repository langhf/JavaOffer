//package cn.drelang.java.advanced.io;
//
//import java.io.*;
//
///**
// * 文件拷贝案例。
// * 1. 考虑各种形式的文件，因此最好用字节流；
// * 2. 需要考虑大文件的情况。
// *
// * 使用 InputStream 和 OutputStream，推荐使用 FileUtil 中 copy_new 方法。
// * Created by Drelang on 2020/05/28 11:34
// */
//
//public class FileCopyCase {
//    public static void main(String[] args) throws IOException {
//        String path = "/Users/drelang/Documents/repository/Java/offer/src/main/java/cn/drelang/java/advanced/io/files";
//        File srcFile = new File(path, "test.dmg");    // 文件不存在的话，可以自动创建
//        File desFile = new File(path, "test_copy.dmg");
//
//        FileUtil fileUtil = new FileUtil(srcFile, desFile);
//        long start = System.currentTimeMillis();
//        fileUtil.copy_new();
//        long end = System.currentTimeMillis();
//        System.out.println("拷贝所用时间：" + (end - start) + "ms");
//    }
//}
//
//class FileUtil {
//    private File srcFile;
//    private File desFile;
//
//    public FileUtil(File srcFile, File desFile) {
//        this.srcFile = srcFile;
//        this.desFile = desFile;
//    }
//
//    // 文件拷贝原始实现
//    public boolean copy() throws IOException {
//        if (!srcFile.exists()) {
//            System.out.println("原文件不存在");
//            return false;
//        }
//        if (!desFile.getParentFile().exists()) {
//            desFile.getParentFile().mkdirs();
//        }
//
//        byte[] data = new byte[1024];   // 开辟一个缓冲区
//        InputStream input = null;
//        OutputStream output = null;
//        try {
//            input = new FileInputStream(srcFile);
//            output = new FileOutputStream(desFile);
//            int len = 0;
//            while ((len = input.read(data)) != -1) {
//                output.write(data, 0, len);
//            }
//            return true;
//        } catch (IOException e) {
//            throw e;
//        } finally {
//            if (input != null) {
//                input.close();
//            }
//            if (output != null) {
//                output.close();
//            }
//        }
//    }
//
//    // JDK1.9之后，可以使用转存的方式处理
//    public boolean copy_new() throws IOException {
//        if (!srcFile.exists()) {
//            System.out.println("原文件不存在");
//            return false;
//        }
//        if (!desFile.getParentFile().exists()) {
//            desFile.getParentFile().mkdirs();
//        }
//
//        InputStream input = null;
//        OutputStream output = null;
//        try {
//            input = new FileInputStream(srcFile);
//            output = new FileOutputStream(desFile);
//            input.transferTo(output);
//            return true;
//        } catch (IOException e) {
//            throw e;
//        } finally {
//            if (input != null) {
//                input.close();
//            }
//            if (output != null) {
//                output.close();
//            }
//        }
//    }
//
//    // 拷贝目录
//    public boolean copyDir() throws Exception {
//        return false;
//    }
//
//}