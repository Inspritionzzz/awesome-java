package com.bupt.awesomejava.javacore;

public class EffectiveString {
    public static void main(String[] args) {
        codeOptimization();
    }

    public static void codeOptimization() {
        // 1.1 字符串拼接
        String userName = null;
        String age = null;
        String address = null;
        String sex = null;
        String roleId = null;
        String url = "http://susan.sc.cn?userName=" + userName
                                         + "&age=" + age
                                         + "&address=" + address
                                         + "&sex=" + sex
                                         + "&roledId=" + roleId;
        System.out.println("1.1: " + url);
        // 1.2 使用StringBuilder优化(效率低,不建议在循环中使用)
        StringBuilder urlBuilder = new StringBuilder("http://susan.sc.cn?");
        urlBuilder.append("userName=")
                .append(userName)
                .append("&age=")
                .append(age)
                .append("&address=")
                .append(address)
                .append("&sex=")
                .append(sex)
                .append("&roledId=")
                .append(roleId);
        System.out.println("1.2: " + urlBuilder);
        // 1.3 使用StringFormat优化(效率低,不建议在循环中使用)
        String requestUrl = "http://susan.sc.cn?userName=%s&age=%s&address=%s&sex=%s&roledId=%s";
        String urlStringFormat = String.format(requestUrl, userName, age, address, sex, roleId);
        System.out.println("1.3: " + urlStringFormat);
    }

}
