package com.bupt.awesomejava.javacore.grammar;

public class EffectiveBasicProgramming {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        // EffectiveBasicProgramming.method01();

        EffectiveBasicProgramming.method02();

    }

    /**
     * 数据类型
     */
    public static void method01() {
        // 1. 整形
        System.out.println(0b1001);
//        Byte.toUnsignedInt();
        System.out.println(0x1.0p-3);

        // 2. 浮点型
        System.out.println(Double.POSITIVE_INFINITY);
        System.out.println(Double.NEGATIVE_INFINITY);
        System.out.println(Double.NaN);

        if (Double.isNaN(2)) {
            System.out.println("not a number");
        } else {
            System.out.println("is a number");
        }

        System.out.println(2.0 - 1.1);  // 二进制无法精确的表示分数1/10，十进制无法精确的表示分数1/3；
        // 3. char类型（\u0000 - \uFFFF）:unicode和char

        // System.out.println(\u2122);

        // 4. boolean

    }

    /**
     * 变量与常量
     */
    public static void method02() {
        System.out.println(Character.isJavaIdentifierStart('%'));
        System.out.println(Character.isJavaIdentifierPart('%'));
        double number = 12.23d;
        System.out.println(number);
        var str = "just a test";
        System.out.println(str);

    }
}
