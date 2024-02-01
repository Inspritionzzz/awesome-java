package com.bupt.awesomejava.javacore.grammar;

public class EffectiveBasicProgramming {

    public static final double CM_PER_INCH = 2.54;

    enum Size {small, medium, large, extra_large};

    /**
     * 3.1 一个简单的Java应用程序
     * 3.2 注释
     * @param args
     */
    public static void main(String[] args) {
        // EffectiveBasicProgramming.method01();

        // EffectiveBasicProgramming.method02();

        // EffectiveBasicProgramming.method03();

        EffectiveBasicProgramming.method04();
    }

    /**
     * 3.3 数据类型
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
     * 3.4 变量与常量
     */
    public static void method02() {
        System.out.println(Character.isJavaIdentifierStart('%'));
        System.out.println(Character.isJavaIdentifierPart('%'));
        double number = 12.23d;
        System.out.println(number);
        var str = "just a test";
        System.out.println(str);
        Size s = Size.small;
    }

    /**
     * 3.5 运算符
     */
    public static void method03() {
        double number = 12.23d;
        System.out.println(number / 0);
        double x = 4;
        double y = Math.sqrt(x);
        System.out.println("y = " + y);
        double z = Math.pow(x, 3);
        System.out.println("z = " + z);
        int a = -12;
        System.out.println(a % 2);
        System.out.println(Math.floorMod(a, 2));
        // StrictMath类，实现了可自由分发的数学库，www.netlib.org/fdlibm
        // System.out.println(Math.multiplyExact(1000000000, 3));
        System.out.println(1000000000 * 3);
        double b = 9.97;
        double nb = (int)Math.round(b);
        System.out.println("b = " + (int)b);

    }

    /**
     * 3.6 字符串
     */
    public static void method04() {
        String str1 = "just a test";
        System.out.println(str1.substring(0, 3));   // 左闭右开
        System.out.println(str1 + 4);
        String str2 = String.join(" / ", "S", "M", "L", "XL");
        System.out.println("str2 = " + str2);
        String str3 = "Java".repeat(3);
        System.out.println("str3 = " + str3);
        str1 = str1.substring(0, 3) + "test!";
        System.out.println("str1 = " + str1);

        String str4 = "just a test";
        String str5 = "just a test";
        System.out.println(str4.equals(str5));
        System.out.println("just a test".equals(str5)); // 不要使用“==”判断字符串相等

        if (str5 != null && str5.length() != 0) {
            System.out.println("str5 is not null");
        }

        String str6 = "just a test";
        int n = str6.length();
        int cpCount = str6.codePointCount(0, str6.length());
        System.out.println("n = " + n + "  " + "cpCount = " + cpCount);

    }
}
