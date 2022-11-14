package com.bupt.awesomejava.scaffold.experiment;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.http.server.HttpServerRequest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.regex.Matcher;

public class experiment {
    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));

    }

    public void fileIO() {
        BufferedInputStream in = FileUtil.getInputStream("d:/test.txt");
        BufferedOutputStream out = FileUtil.getOutputStream("d:/test2.txt");
        long copySize = IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);
    }

    public void filePathProcess() {
        String realPath = "src\\" + File.separator + "test";
        realPath = realPath.replaceAll("\\\\", Matcher.quoteReplacement(File.separator));

        String str = File.separator;
        str = str.replaceAll("\\\\", Matcher.quoteReplacement(File.separator));
        System.out.println(str);
        System.out.println(realPath);
    }

}
