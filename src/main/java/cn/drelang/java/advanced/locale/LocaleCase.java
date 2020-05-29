package cn.drelang.java.advanced.locale;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * java.text 抱提供了很多字符串格式化的操作。
 *
 * Created by Drelang on 2020/05/21 12:48
 */

public class LocaleCase {
    public static void main(String[] args) throws Exception {
        // getBundle() 默认读取当前 Locale 的配置
        ResourceBundle resource = ResourceBundle.getBundle("Messages");
        System.out.println(resource.getString("info"));

        // 通过指定 Locale 获取特定数据
        Locale locale = Locale.US;
        ResourceBundle resource1 = ResourceBundle.getBundle("Messages", locale);  // baseName 不加文件类型
        System.out.println(resource1.getString("info"));

        // 通过 MessageFormat 替换掉占位符
        System.out.println(MessageFormat.format(resource.getString("msg"), "小明", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
        System.out.println(MessageFormat.format(resource1.getString("msg"), "Xiao Ming", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
    }
}

