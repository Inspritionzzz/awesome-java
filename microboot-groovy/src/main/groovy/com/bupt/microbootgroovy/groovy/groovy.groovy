package com.bupt.microbootgroovy.groovy

import java.time.format.DateTimeFormatter

class groovy {
    static void main(String[] args) {
        // 定义变量时不建议定义数据类型
        // 换行可以不加 ;
        def x = 5;
        println('just a test');
        print(x)
        print(this.f1("just a test"))
        def groovy = new groovy();
        groovy.f2("ttt")

        def sdf = new DateTimeFormatter("yyyyMMdd")
    }

    static def f1(string) {
        "the String is $string"
    }

    def f2(string) {
        "the String is $string"
    }



    static def caculate(Integer num1, Integer num2, Closure closure) {
        closure(num1, num2)
    }


}
