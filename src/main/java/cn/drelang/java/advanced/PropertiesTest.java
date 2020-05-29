package cn.drelang.java.advanced;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Drelang on 2020/05/29 15:00
 */

public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        File file = new File("prop_test.properties");
        Properties pp = new Properties();
        pp.setProperty("name", "xly");
        pp.setProperty("age", "19");
        pp.storeToXML(new FileOutputStream(file), "保存Person到XML文件");
    }
}

