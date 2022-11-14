package com.bupt.awesomejava.javacore;

import lombok.var;

/**
 * @Description: array
 * Created by jason on 2022/11/11 9:08
 */

public class EffectiveArray {
    public static final double CM_PER_INCH_1 = 2.54;   // 可以在其他类中使用

    public static void main(String[] args) {
        // floatType();
        // charType();
        // mathMethod();
        stringType();
    }

    /**
     * 浮点数
     */
    static void floatType() {
        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY);
        System.out.println(Double.NaN);
        System.out.println(Double.isNaN(0.03));
        System.out.println(2.0 - 1.1);  // 会损失精度，若无法接收误差可使用 BigDecimal 类

    }

    /**
     * char 类型：\u0000 - \uFFFF
     */
    static void charType() {
        System.out.println('\uFFFF');
    }

    /**
     * 变量和常量
     */
    static strictfp void var() {    // strictfp 表示严格的浮点计算
        var intNum = 12;
        final double CM_PER_INCH = 2.54;
        System.out.println(Size.SMALL);
    }

    /**
     * 枚举
     */
    enum Size{
        SMALL,
        MEDIUM,
        LARGE,
        EXTRA_LARGE
    }

    /**
     * 数值计算
     */
    static void mathMethod() {
        double x = 0.997;
        int nx = (int)Math.round(x);
        int a = 4;
        a += 3.5;   // 相当于 (int)(x + 3.5)
        System.out.println(a);
        int b = (int)Math.round(x);
        System.out.println(b);
        // 对 boolean 型的类型转换：b?1:0
        // 不要再表达式中使用 ++ ，如 int a = 2 * ++n;
        // 位运算：& | ^ ~(not) << >>
        int n = 0b1111;
        int fourthBitFromRight = (n & 0b1000) / 0b1000;
    }

    /**
     * 字符串
     */
    static void stringType() {
        String string = "strstrstr";
        System.out.println(string.substring(0, 3));
        String all = String.join(" / ", "S", "M", "L");
        System.out.println(all);
        // 字符串不可变，这是为了方便字符串共享；
        System.out.println("HHH".equalsIgnoreCase("hhh"));
        // 判空
        if (string.length() == 0) {
            System.out.println("null!!");
        }
        if (string.equals("")) {
            System.out.println("null!!");
        }
        if (string != null && string.length() != 0) {
            System.out.println("null!!");
        }
        // length() 返回采用 UTF-16 编码给定字符串所需要的代码单元数量
        // 有些字符采用 UTF-16 需要两个代码单元，此时使用 charAt(1) 取字符就会出错，因此不建议使用字符型
        int cpCount = string.codePointCount(0, string.length());
        System.out.println(cpCount);
        System.out.println("U+1D546");

        int i = 0;
        int cp = string.codePointAt(i);
        if (Character.isSupplementaryCodePoint(cp)){
            i += 2;
        } else {
            i++;
        }
        System.out.println(cp);

        int[] codePoints = string.codePoints().toArray();
        System.out.println(codePoints);
        for (int j = 0; j < codePoints.length; j++) {
            System.out.println(codePoints[j]);
        }
    }
}
