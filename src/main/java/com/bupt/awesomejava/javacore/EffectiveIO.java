package com.bupt.awesomejava.javacore;

import java.io.*;

public class EffectiveIO {
    public static void main(String[] args) {
        IoTest1();
        IoTest2();
    }

    /**
     * 创建IO流读取数据
     */
    public static void IoTest1() {
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                File srcFile = new File("D:\\crm-sight\\awesome-java\\src\\main\\java\\com\\bupt\\awesomejava\\javanote\\files\\test1.txt");
                File destFile = new File("D:\\crm-sight\\awesome-java\\src\\main\\java\\com\\bupt\\awesomejava\\javanote\\files\\test2.txt");
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                int len;
                while ((len = fis.read()) != -1) {
                    fos.write(len);
                }
                fos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }

    /**
     * 创建可缓冲的IO流读取数据
     */
    public static void IoTest2() {
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            FileInputStream fis = null;
            FileOutputStream fos = null;
            try {
                File srcFile = new File("D:\\crm-sight\\awesome-java\\src\\main\\java\\com\\bupt\\awesomejava\\javanote\\files\\test1.txt");
                File destFile = new File("D:\\crm-sight\\awesome-java\\src\\main\\java\\com\\bupt\\awesomejava\\javanote\\files\\test2.txt");
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                bis = new BufferedInputStream(fis);
                bos = new BufferedOutputStream(fos);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                bos.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (bos != null) {
                        bos.close();
                    }
                    if (fos != null) {
                        fos.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (bis != null) {
                        bis.close();
                    }
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


