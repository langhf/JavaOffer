package cn.drelang.java.advanced;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配Demo，具体正则匹配规则查看 Java Doc 关于 Pattern 类的说明。
 * String 类下面的 matches() 方法实际上就是调用 Pattern 和 Matcher 类的一些方法。
 * Created by Drelang on 2020/05/21 10:08
 */

public class RegexCase {
    public static void main(String[] args) throws ParseException {
        String str = "a";
        String str1 = "\\"; // \ 代表转义符，\\表示转义后的 \
        System.out.println(str.matches("."));
        System.out.println(str1.matches("\\\\"));   // regex 里面，\\表示一个\。

        System.out.println("fsdf".matches("\\w+"));

        // 去掉非数字和字母的字符
        String str2 = "dJLKSghlasf092&8(*#%(*&Q(*dfjlsadjfhsalfj";
        System.out.println(str2.replaceAll("[^a-zA-Z0-9]*", ""));

        // 判断小数
        String str3 = "100.12";
        String regex = "\\d+(\\.\\d+)?"; // 小数点和小数位绑定一起，要么同时出现，要么同时不出现
        System.out.println(str3.matches(regex));

        // 判断日期
        String str4 = "1999-12-21";
        String regex4 = "\\d{4}-\\d{2}-\\d{2}";
        if (str4.matches(regex4)) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").parse(str4));
        }

        // 验证邮箱
        String str5 = "drealng@dr.cn";
        String regex5 = "[0-9a-zA-Z]\\w+@\\w+\\.(cn|com|com.cn|gov.cn)";
        System.out.println(str5.matches(regex5));

        /// 一般情况下 String 类提供的正则匹配操作够用，但是 java.util.regex 能够提供更为复杂的功能。
        // 取出 "#{内容}" 标记中的所有内容
        String str6 = "INSERT INTO dept(deptno,dname,loc) VALUES (#{deptno},#{dname},#{loc})";
        String regex6 = "#\\{\\w+\\}";
        Pattern pattern = Pattern.compile(regex6);
        Matcher matcher = pattern.matcher(str6);
        while (matcher.find()) {    //是否有匹配的内容
            System.out.println(matcher.group().replaceAll("\\{|#|\\}", ""));
        }
    }
}

