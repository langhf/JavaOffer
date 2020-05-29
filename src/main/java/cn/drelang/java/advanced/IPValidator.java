package cn.drelang.java.advanced;

/**
 * IP 地址验证器
 * Created by Drelang on 2020/05/25 17:32
 */

public class IPValidator {
    public static void main(String[] args) {
        System.out.println(validate("192.168.1.12"));
        System.out.println(validate("1234.23.12.123"));
        System.out.println(validate("132.12.32.255"));
        System.out.println(validate("-1.12.123.25"));

    }

    static boolean validate(String ip) {
        if (ip == null || "".equals(ip)) {
            return false;
        }

        String regex = "([12]?[0-9]?[0-9]\\.){3}([12]?[0-9]?[0-9])";
        if (ip.matches(regex)) {
            String[] temp = ip.split("\\.");
            for (int i=0; i<temp.length; i++) {
                int t = Integer.parseInt(temp[i]);
                if (t > 255) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}

