package cn.drelang.java.advanced;

import java.util.Base64;

/**
 * 关于加密操作，最好使用多种不同加密的算法。
 * Created by Drelang on 2020/05/21 14:51
 */

public class Base64Case {
    public static void main(String[] args) {
        String str = "drelang";
        System.out.println("明文：" + str);

        String str1 = StringUtil.encode(str);
        System.out.println("密文：" + str1);

        System.out.println("还原：" + StringUtil.decode(str1));
    }

    static class StringUtil {
        private static final String SALT = "hgals834tasfasdflkj";
        private static final int REPEAT = 5;

        /**
         * 反复加密，达到更好的加密效果
         * @param s 要加密的字符串
         * @return 密文
         */
        public static String encode(String s) {
            byte[] data = s.getBytes();
            for (int i=0; i<REPEAT; i++) {
                data = Base64.getEncoder().encode(data);
            }
            return new String(data);
        }

        /**
         * 解密
         * @param s 密文
         * @return 明文
         */
        public static String decode(String s) {
            byte[] data = s.getBytes();
            for (int i=0; i<REPEAT; i++) {
                data = Base64.getDecoder().decode(data);
            }
            return new String(data).replaceAll("\\{\\w*\\}", "");    // 去掉盐值
        }
    }
}

