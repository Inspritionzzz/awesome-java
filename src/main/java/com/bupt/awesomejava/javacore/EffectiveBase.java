package com.bupt.awesomejava.javacore;

import lombok.var;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Date;
import java.util.Scanner;

/**
 * @Description: array
 * Created by jason on 2022/11/11 9:08
 */

public class EffectiveBase {
    public static final double CM_PER_INCH_1 = 2.54;   // 可以在其他类中使用

    public static void main(String[] args) throws IOException {
        // floatType();
        // charType();
        // mathMethod();
        // stringType();
        inAndOut();
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

        String str = new String(codePoints, 0, codePoints.length); // 把码点数转换为一个字符串

        // 构建字符串
        StringBuilder builder = new StringBuilder();
        builder.append("aaa");
        builder.append("bbb");
        String strBuilder = builder.toString();
    }

    /**
     * 输入输出
     */
    static void inAndOut() throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("please type some String");
        String name = in.nextLine();    // 读取一行
        System.out.println(name);
        String firstName = in.next();   // 以空格符换行
        System.out.println(firstName);
        System.out.println("please type some Integer");
        String age = in.next();
        System.out.println(age);
//        Console console = System.console();  // 必须每次读取一行
//        String username = console.readLine("User name: ");
//        char[] passwd = console.readPassword("Password: ");
//        System.out.println(username + " " + passwd);

        double x = 10000.0 / 3.0;
        System.out.println(x);
        System.out.printf("%8.2f", x);
        System.out.println();
        // f 表示浮点数，s 表示字符串，d 表示十进制整数
        System.out.printf("Hello, %s. Next year, you'll be %d ", "jason", 18);
        System.out.printf("%,.2f", 1000.0 / 3.0);
        String message = String.format("Hello, %s. Next year, you'll be %d ", "jason", 18);
        System.out.println();
        // 日期和时间格式化输出
        System.out.printf("%tc", new Date());
        System.out.println();
        System.out.printf("%tF", new Date());
        System.out.println();
        System.out.printf("%1$s %2$tB %2$te, %2$tY", "Due date:", new Date());
        System.out.println();
        System.out.printf("%s %tB %<te, %<tY", "Due date:", new Date());
        System.out.println();
        System.out.println(System.getProperty("user.dir"));
        Scanner readFile = new Scanner(Path.of( System.getProperty("user.dir") + "\\src\\myfile.txt"), StandardCharsets.UTF_8);
        while (readFile.hasNext()) {
            System.out.println(readFile.next());
        }
        PrintWriter printWriter = new PrintWriter(System.getProperty("user.dir") + "\\src\\myfile2.txt");
        char[] chars = "just some test".toCharArray();
        printWriter.write(chars, 0, chars.length);
        printWriter.write("just some test", 0, 5);
    }

}
