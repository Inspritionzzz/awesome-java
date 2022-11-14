package com.bupt.awesomejava.scaffold;

import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaRunPython {
    public static void main(String[] args) {
        // f1();
        // f2();
        f3();
    }

    /**
     * 使用jython直接执行python代码;
     */
    public static void f1() {
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.exec("str = 'just a test';");
        pythonInterpreter.exec("print str;");
    }

    /**
     * 使用jython直接执行python脚本;
     */
    public static void f2() {
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        pythonInterpreter.execfile("C:\\JavaProject\\awesome-java\\src\\main\\java\\com\\bupt\\awesomejava\\scaffold\\python_script\\pydemo1.py");
    }

    /**
     * 调用python脚本;
     */
    public static void f3() {
        Process proc;
        try {
            // 大括号中,第一个双引号里是python解释器的路径,第二个双引号里是要执行的python文件的路径
            String[] a = new String[]{"C:\\Users\\zhangcy4\\AppData\\Local\\Programs\\Python\\Python38\\python.exe",
                    "C:\\JavaProject\\awesome-java\\src\\main\\java\\com\\bupt\\awesomejava\\scaffold\\python_script\\pydemo1.py"};
            proc = Runtime.getRuntime().exec(a);
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), "GBK"));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
