package com.bupt.awesomejava.scaffold.httpcomponent;

import java.io.InputStream;

public class StringProcessTest {
    public static void main(String[] args) {
        String str = "test1-test2-test3";
        String[] strings = str.split("-");
        String str03 = str.replace("-",",");
        System.out.println(strings[1]);
        System.out.println(str03);

        InputStream is = StringProcessTest.class.getClassLoader().getResourceAsStream("applications.properties");
        System.out.println(is);
    }
}
