package cn.drelang.java.advanced;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * 大数字Demo.
 * 主要 BigInteger 和 BigDecimal 类。
 * 过大的数字可能会消耗硬件资源，性能较差。
 * Created by Drelang on 2020/05/21 09:31
 */

public class BigNumberCase {
    public static void main(String[] args) {
        BigInteger bigA = new BigInteger("232324252342352423232324252342352423232324252342352423232324252342352423");
        BigInteger bigB = new BigInteger("23423423234234232342342323423423");
        System.out.println("加法操作：" + bigA.add(bigB));
        System.out.println("减法操作：" + bigA.subtract(bigB));
        System.out.println("乘法操作：" + bigA.multiply(bigB));
        System.out.println("除法操作：" + bigA.divide(bigB));
        System.out.println("求余除法：" + Arrays.toString(bigA.divideAndRemainder(bigB)));

        // 可以进行小数运算
        BigDecimal bigC = new BigDecimal("234252349582342.09394234");
        BigDecimal bigD = new BigDecimal("323233.2323423492349");
        System.out.println("加法操作：" + bigC.add(bigD));
        System.out.println("减法操作：" + bigC.subtract(bigD));
        System.out.println("乘法操作：" + bigC.multiply(bigD));
        System.out.println("除法操作：" + bigC.divide(bigD, 2, RoundingMode.CEILING));
    }
}

