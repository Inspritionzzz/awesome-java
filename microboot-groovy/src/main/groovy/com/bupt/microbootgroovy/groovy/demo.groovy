package com.bupt.microbootgroovy.groovy

/**
 * @Description: groovy demo
 * Created by jason on 2022/10/11 9:22
 */

def x = 5;
println('just a test');
print(x)

running ({print("running...")})
caculate(10, 15, {k, v -> println("$k + $v = ${k + v}")})

def caculate(Integer num1, Integer num2, Closure closure) {
    closure(num1, num2)
}

def running(Closure closure) {
    closure()
    print("running start...")
}
