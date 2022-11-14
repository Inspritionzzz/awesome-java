package com.bupt.awesomejava.utils;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Map;

public class GetComputerInfo {
    /**
     * 获取用户名和 mac 地址
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");
        System.out.println("当前电脑用户名" + userName);

        StringBuilder sb = new StringBuilder();
        Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        byte[] mac = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = allNetInterfaces.nextElement();
            if (netInterface.isLoopback() || netInterface.isVirtual() || netInterface.isPointToPoint() || !netInterface.isUp()) {
                continue;
            } else {
                mac = netInterface.getHardwareAddress();
                if (mac != null) {
                    for (int i = 0; i < mac.length; i++) {
                        sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "\n"));
                    }
                }
            }
        }
        System.out.println(sb.toString());
    }

}
