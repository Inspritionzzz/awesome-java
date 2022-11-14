package com.bupt.awesomejava.scaffold.httpcomponent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class PhoneAttrSearchUtil extends Thread {

    private static Logger logger = LogManager.getLogger(PhoneAttrSearchUtil.class);

    /* 致胜查询手机归属地接口测试地址 */
    public static final String ISTP_PHONE_ATTR_DEV = "http://10.237.121.82/inner/util/common/phoneAttrSearch";

    /* 致胜查询手机归属地接口生产地址 */
    public static final String ISTP_PHONE_ATTR_PRO = "http://10.17.192.129/inner/util/common/phoneAttrSearch";

    // 企业股权结构信息查询生产环境示例;
    // http://10.17.192.129/inner/info/corp/businesslicense?type=1&key=681302839
    // http://10.237.121.21:8095/info/corp/businesslicense?type=1&key=681302839

    public static void main(String[] args) {

//        PhoneAttrSearchUtil phoneAttrSearchUtil1 = new PhoneAttrSearchUtil();
//        Thread thread1 = new Thread(phoneAttrSearchUtil1, "thread01");
//
//        PhoneAttrSearchUtil phoneAttrSearchUtil2 = new PhoneAttrSearchUtil();
//        Thread thread2 = new Thread(phoneAttrSearchUtil2, "thread02");
//
//        thread1.start();
//        thread2.start();

        PhoneAttrSearchUtil phoneAttrSearchUtil = new PhoneAttrSearchUtil();
        phoneAttrSearchUtil.testTaskMultiThread();
//      phoneAttrSearchUtil.testTaskSingleThread();
    }
    public void testTaskMultiThread() {
        /**
         * 双线程:
         * 任务开始时间:2022-03-24 at 18:03:23 CST
         * 任务结束时间:2022-03-24 at 18:04:40 CST
         */

        new myTask(1).start();
        new myTask(2).start();
    }

    public void testTaskSingleThread() {
        /**
         * 单线程:
         * 任务结束时间:2022-03-24 at 17:59:49 CST
         * 任务结束时间:2022-03-24 at 18:02:23 CST
         */
        new myTask(3).start();
    }


    //    public static void main(String[] args) throws Exception {
//        HTTPRequestUtils connection = new HTTPRequestUtils();
//        /**
//         * 致胜接口查询手机号码归属地的数据格式:
//         * {
//         *      "phone":"15158154622",
//         *      "ip":"",
//         *      "mac":"",
//         *      "operway":"",
//         *      "transid":"",
//         *      "custid":"",
//         *      "fundid":"",
//         *      "custorgid":"1"
//         * }
//         *
//         * json字符串格式:
//         * String jsonStr="{\"phone\":\"15634333933\",\"custorgid\":\"1\"}";
//         */
//
//        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
//        String taskBeginTime = "任务开始时间:" + formatter.format(new Date(System.currentTimeMillis()));
//
//        // 1.连接数据库并读取数据;
//        // jdbc:sqlserver://127.0.0.1:1433;DatabaseName=model;
//        String url = "jdbc:sqlserver://10.237.1.250:1433;selectMethod=cursor;";
//        // String url = "jdbc:sqlserver://10.16.232.139";
//        // 连接测试数据库;
//        Connection conn = DriverManager.getConnection(url, "sa", "85130268");
//        // Connection conn = DriverManager.getConnection(url, "sa", "c65178899Sc");
//        // 创建一个 Statement 对象来将 SQL 语句发送到数据库;
//        Statement stat = conn.createStatement();
//        // 执行SQL:需要查询客户号、手机号、custorgid;
//        // ResultSet resultSet = stat.executeQuery("select top 100 * from crmcounter.dbo.t_counter_khfl with(nolock)");
//        ResultSet resultSet = stat.executeQuery("select top 1000000 custid,phone from test.dbo.cust_phone");
//
//        // ResultSet resultSet = stat.executeQuery("select custid,phone from crmcounter.dbo.cust_phone");
//        // Map<String,String> custPhoneInfo = new HashMap<>();
//
//        Map<String,String> custPhoneInfo = new HashMap<>();
//        while (resultSet.next()) {
//
//            // custPhoneInfo.put(resultSet.getString("custid"),resultSet.getString("phone"));
//            // custPhoneInfo.put("phone","15634333933"); // 测试数据;
//            // 构建json请求参数;
//            custPhoneInfo.put("phone",resultSet.getString("phone"));
//            custPhoneInfo.put("custorgid","1");
//            String jsonStr = JSON.toJSONString(custPhoneInfo,true);
//            System.out.println(jsonStr);
//            String response = connection.sendPostJson(ISTP_PHONE_ATTR_DEV,jsonStr,"UTF-8");
//            // 解析数据:{"data":{"province":"山东","city":"山东","company":"中国联通网络"},"errmsg":"成功","status":200}
//            JSONObject jsonObject = JSON.parseObject(response);
//            System.out.println(jsonObject);
//            JSONObject jsonObject2 = JSON.parseObject(jsonObject.getString("data"));  // json第一层解析;
//
//            String province = null;
//            String city = null;
//            String company = null;
//            String recdate = null;
//            int flag = 0;
//
//            String sql = "insert into test.dbo.cust_phone_location(custid,phone,province,city,company,flag,recdate) " +
//                    "values(?,?,?,?,?,?,?)";
//
////          String sql = "insert into crmcounter.dbo.cust_phone_location(custid,phone,province,city,company,flag,recdate) " +
////                                                           "values(?,?,?,?,?,?,?)";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            //if (jsonObject2.getString("status").equalsIgnoreCase("200")) { // 异常处理;
//            province = jsonObject2.getString("province");
//            city = jsonObject2.getString("city");
//            company = jsonObject2.getString("company");
//            recdate = "20220128";
//            flag = 1;
//            //} else {
////                province = "";
////                city = "";
////                company = "";
////                recdate = "20220128";
////                flag = 0;
//            //}
//
//            ps.setString(1,resultSet.getString("custid"));
//            ps.setString(2,resultSet.getString("phone"));
//            ps.setString(3,province);
//            ps.setString(4,city);
//            ps.setString(5,company);
//            ps.setInt(6,flag);
//            ps.setString(7,recdate);
//            custPhoneInfo.clear();
//
//            ps.executeUpdate();
//        }
//
//        System.out.println("验证数据集:" + custPhoneInfo.size());
//        System.out.println(custPhoneInfo.toString());
//
//        // 6.关闭数据库连接;
//        stat.close();
//        conn.close();
//        System.out.println("任务结束时间:" + formatter.format(new Date(System.currentTimeMillis())));
//    }
class myTask extends Thread {
    private int tid;
    public myTask(int tid) {
        this.tid = tid;
    }
    @Override
    public void run() {
        int count = 0;
        HTTPRequestUtils connection = new HTTPRequestUtils();
        /**
         * 致胜接口查询手机号码归属地的数据格式:
         * {
         *      "phone":"15158154622",
         *      "ip":"",
         *      "mac":"",
         *      "operway":"",
         *      "transid":"",
         *      "custid":"",
         *      "fundid":"",
         *      "custorgid":"1"
         * }
         *
         * json字符串格式:
         * String jsonStr="{\"phone\":\"15634333933\",\"custorgid\":\"1\"}";
         */

        try {
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            String taskBeginTime = formatter.format(new Date(System.currentTimeMillis()));

            // 1.连接数据库并读取数据;
            // jdbc:sqlserver://127.0.0.1:1433;DatabaseName=model;
            String url = "jdbc:sqlserver://10.237.1.250:1433;selectMethod=cursor;";
            // String url = "jdbc:sqlserver://10.16.232.139";
            // 连接测试数据库;
            Connection conn = DriverManager.getConnection(url, "sa", "85130268");
            // Connection conn = DriverManager.getConnection(url, "sa", "c65178899Sc");
            // 创建一个 Statement 对象来将 SQL 语句发送到数据库;
            Statement stat = conn.createStatement();
            // 执行SQL:需要查询客户号、手机号、custorgid;
            // ResultSet resultSet = stat.executeQuery("select top 100 * from crmcounter.dbo.t_counter_khfl with(nolock)");

            ResultSet resultSet = null;
            if(this.tid == 1){
                resultSet = stat.executeQuery("select custid,phone from test.dbo.cust_phone_tid where tid between 1 and 10000");
            }
            if(this.tid == 2){
                resultSet = stat.executeQuery("select custid,phone from test.dbo.cust_phone_tid where tid between 10001 and 20000");
            }
            if(this.tid == 3){
                resultSet = stat.executeQuery("select custid,phone from test.dbo.cust_phone_tid where tid between 1 and 20000");
            }
            // ResultSet resultSet = stat.executeQuery("select custid,phone from crmcounter.dbo.cust_phone");
            // Map<String,String> custPhoneInfo = new HashMap<>();

            Map<String,String> custPhoneInfo = new HashMap<>();
            while (resultSet.next()) {
                // custPhoneInfo.put(resultSet.getString("custid"),resultSet.getString("phone"));
                // custPhoneInfo.put("phone","15634333933"); // 测试数据;
                // 构建json请求参数;
                custPhoneInfo.put("phone",resultSet.getString("phone"));
                custPhoneInfo.put("custorgid","1");
                String jsonStr = JSON.toJSONString(custPhoneInfo,true);
                System.out.println(jsonStr);
                String response = connection.sendPostJson(ISTP_PHONE_ATTR_DEV,jsonStr,"UTF-8");
                // 解析数据:{"data":{"province":"山东","city":"山东","company":"中国联通网络"},"errmsg":"成功","status":200}
                JSONObject jsonObject = JSON.parseObject(response);
                // System.out.println(jsonObject);
                JSONObject jsonObject2 = JSON.parseObject(jsonObject.getString("data"));  // json第一层解析;
                if(this.tid == 1){
                    System.out.println(response.toString() + "请求次数:" + count++ + "线程T1");
                }
                if(this.tid == 2){
                    System.out.println(response.toString() + "请求次数:" + count++ + "线程T2");
                }
                if(this.tid == 3){
                    System.out.println(response.toString() + "请求次数:" + count++ + "线程T3");
                }

            }
            // 6.关闭数据库连接;
            stat.close();
            conn.close();
            System.out.println("任务开始时间:" + taskBeginTime);
            System.out.println("任务结束时间:" + formatter.format(new Date(System.currentTimeMillis())));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


    public void httpPerformanceTest() throws Exception {
        int count = 0;
        HTTPRequestUtils connection = new HTTPRequestUtils();
        /**
         * 致胜接口查询手机号码归属地的数据格式:
         * {
         *      "phone":"15158154622",
         *      "ip":"",
         *      "mac":"",
         *      "operway":"",
         *      "transid":"",
         *      "custid":"",
         *      "fundid":"",
         *      "custorgid":"1"
         * }
         *
         * json字符串格式:
         * String jsonStr="{\"phone\":\"15634333933\",\"custorgid\":\"1\"}";
         */

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        String taskBeginTime = formatter.format(new Date(System.currentTimeMillis()));

        // 1.连接数据库并读取数据;
        // jdbc:sqlserver://127.0.0.1:1433;DatabaseName=model;
        String url = "jdbc:sqlserver://10.237.1.250:1433;selectMethod=cursor;";
        // String url = "jdbc:sqlserver://10.16.232.139";
        // 连接测试数据库;
        Connection conn = DriverManager.getConnection(url, "sa", "85130268");
        // Connection conn = DriverManager.getConnection(url, "sa", "c65178899Sc");
        // 创建一个 Statement 对象来将 SQL 语句发送到数据库;
        Statement stat = conn.createStatement();
        // 执行SQL:需要查询客户号、手机号、custorgid;
        // ResultSet resultSet = stat.executeQuery("select top 100 * from crmcounter.dbo.t_counter_khfl with(nolock)");
        ResultSet resultSet = stat.executeQuery("select top 1000 custid,phone from test.dbo.cust_phone");

        // ResultSet resultSet = stat.executeQuery("select custid,phone from crmcounter.dbo.cust_phone");
        // Map<String,String> custPhoneInfo = new HashMap<>();

        Map<String,String> custPhoneInfo = new HashMap<>();
        while (resultSet.next()) {
            // custPhoneInfo.put(resultSet.getString("custid"),resultSet.getString("phone"));
            // custPhoneInfo.put("phone","15634333933"); // 测试数据;
            // 构建json请求参数;
            custPhoneInfo.put("phone",resultSet.getString("phone"));
            custPhoneInfo.put("custorgid","1");
            String jsonStr = JSON.toJSONString(custPhoneInfo,true);
            System.out.println(jsonStr);
            String response = connection.sendPostJson(ISTP_PHONE_ATTR_DEV,jsonStr,"UTF-8");
            // 解析数据:{"data":{"province":"山东","city":"山东","company":"中国联通网络"},"errmsg":"成功","status":200}
            JSONObject jsonObject = JSON.parseObject(response);
            // System.out.println(jsonObject);
            JSONObject jsonObject2 = JSON.parseObject(jsonObject.getString("data"));  // json第一层解析;
            System.out.println(response.toString() + "请求次数:" + count++);
        }

        // 6.关闭数据库连接;
        stat.close();
        conn.close();
        System.out.println("任务结束时间:" + taskBeginTime);
        System.out.println("任务结束时间:" + formatter.format(new Date(System.currentTimeMillis())));

    }
}
