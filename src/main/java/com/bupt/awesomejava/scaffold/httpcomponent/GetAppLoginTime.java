package com.bupt.awesomejava.scaffold.httpcomponent;

import cn.hutool.core.date.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetAppLoginTime {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("请指定时间区间!!!格式示例：2022-06-28 00:00:00 2022-06-28 01:00:00");
            // return;
        } else {
            System.out.println("您调用main方法时指定的参数  " + args.length + " 个参数如下：");
            for (String s : args) {
                System.out.println(s);
            }
        }

        System.out.println("开始补数...");
        // Map<String, Object> paramResult = requestParam("2022-04-01 22:33:23", "2022-06-29 22:33:23");
        // DateTime dateTime = new DateTime("2022-04-01 22:33:23", DatePattern.NORM_DATETIME_FORMAT);
        Map<String, Object> paramResult = requestParam(new DateTime(args[0], DatePattern.NORM_DATETIME_FORMAT).toString(),
                new DateTime(args[1], DatePattern.NORM_DATETIME_FORMAT).toString());

        List<String> dateArraySplitByHour = (List<String>) paramResult.get("dateArraySplitByHour");
        for(int i = 0; i < dateArraySplitByHour.size() - 1; i++) {
            System.out.println(dateArraySplitByHour.get(i) + "=============" + dateArraySplitByHour.get(i + 1));
            requestAndSave(dateArraySplitByHour.get(i), dateArraySplitByHour.get(i + 1));
            // System.out.println(dateArraySplitByHour.get(i) + "-" + dateArraySplitByHour.get(i + 1));
        }
    }

    /**
     * 时间切片函数：生成每天的开始time和结束time;
     * @param startTaskTime
     * @param endTaskTime
     * @return
     */
    public static Map<String, Object> requestParam(String startTaskTime, String endTaskTime) {
//        startTaskTime = "2022-04-01 22:33:23";
//        endTaskTime = "2022-06-29 22:33:23";

        Map<String, Object> paramResult = new HashMap<>();
        Date startTaskDate = DateUtil.parse(startTaskTime);
        Date beginOfTask = DateUtil.beginOfDay(startTaskDate);
        Date beginOfTaskEnd = DateUtil.endOfDay(startTaskDate);

        Date endTaskDate = DateUtil.parse(endTaskTime);
        Date endOfTask = DateUtil.endOfDay(endTaskDate);
        System.out.println("任务执行时间区间: " + beginOfTask + "-" + endOfTask);

        // 以小时为单位切片;
        long betweenHour = DateUtil.between(beginOfTask, endOfTask, DateUnit.HOUR);
        List<String> startOfDayList = new ArrayList<>();
        for(int i = 0; i < betweenHour + 2; i ++) {
            DateTime newDate1 = DateUtil.offset(beginOfTask, DateField.HOUR, 1 * i);
            startOfDayList.add(newDate1.toString());
        }

        // 以天为单位切片
        long betweenDay = DateUtil.between(beginOfTask, endOfTask, DateUnit.DAY);
        List<String> endOfDayList = new ArrayList<>();
        for(int i = 0; i < betweenDay; i ++) {
            DateTime newDate2 = DateUtil.offsetDay(beginOfTaskEnd, i);
            endOfDayList.add(newDate2.toString());
        }

        paramResult.put("dateArraySplitByHour", startOfDayList);
        paramResult.put("dateArraySplitByDay", endOfDayList);

        Date newDate = DateUtil.offset(beginOfTask, DateField.HOUR, 2);
        System.out.println(newDate);
        return paramResult;
    }

    /**
     * 根据每天的起始和结束时间构造post请求,并解析返回的json数据,最后存入数据库;
     * @param startOfDay
     * @param endOfDay
     */
    public static void requestAndSave(String startOfDay, String endOfDay) {

//        String destTableName = "SJZX128.crmcounter.dbo.t_counter_jyapp_login_rtime_backup";

        String destTableName = "crmcounter.dbo.t_counter_jyapp_login_rtime_backup";

       // String interfaceUrl = "http://10.17.64.200:8080/daas/ws/es/agrzrq/newapp/query_innerinf";

        String interfaceUrl = "http://10.17.64.200:8080/daas/ws/es/agrzrq/newapp/query_innerinf";

//        {"str_zjzh":null,"@timestamp":"2022-06-29 09:01:23"}
//        {"str_zjzh":null,"@timestamp":"2022-06-29 09:02:30"}
//        {"str_zjzh":"35367213","@timestamp":"2022-06-29 09:00:23"}
//        {"str_zjzh":"35596923","@timestamp":"2022-06-29 09:00:26"}
//        {"str_zjzh":"35317122","@timestamp":"2022-06-29 09:00:28"}
//        {"str_zjzh":"28655346","@timestamp":"2022-06-29 09:00:27"}
//        {"total":"17290","data":[{"str_zjzh":null,"@timestamp":"2022-06-29 09:00:29"}...]}

        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = null;

        try {
            // 1.连接数据库并读取数据;
            // jdbc:sqlserver://127.0.0.1:1433;DatabaseName=model;
            // String url = "jdbc:sqlserver://10.237.1.250:1433;selectMethod=cursor;";
            String url = "jdbc:sqlserver://10.16.232.139:1433;selectMethod=cursor;";

            // 连接数据库;
            // Connection conn = DriverManager.getConnection(url, "sa", "85130268");
            conn = DriverManager.getConnection(url, "sa", "c65178899Sc");
            // 创建一个 Statement 对象来将 SQL 语句发送到数据库;
            // Statement stat = conn.createStatement();

            sql = "delete from " + destTableName + " where rectime between '"+ startOfDay +"' and '"+ endOfDay +"'";
            System.out.println(sql);
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();

            String requstUrl = interfaceUrl + "?starttime={1}&endtime={2}"; //??????
            requstUrl = requstUrl.replace("{1}", startOfDay);
            requstUrl = requstUrl.replace("{2}", endOfDay);
            requstUrl = requstUrl.replaceAll(" ", "%20");
            System.out.println(requstUrl);

            CloseableHttpClient httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(requstUrl);
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String retSrc = EntityUtils.toString(entity);
            Pattern pattern = Pattern.compile("[0-9]+");
            Matcher isNum = null;

            if(retSrc.contains("没有相关数据")) {
                return;
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                JSONObject jsonObj = JSON.parseObject(retSrc);
                //System.out.println(jsonObj.toString());
                JSONArray jsonArray = jsonObj.getJSONArray("data");
                if(!jsonArray.isEmpty()) {
                    sql = "insert into "+ destTableName + " (fundid, rectime) values (?,?)";
                    stmt = conn.prepareStatement(sql);

                    for(int i = 0; i < jsonArray.size(); i++){
                        JSONObject obj = jsonArray.getJSONObject(i);
                        if(obj.getString("str_zjzh") != null) {
                            isNum = pattern.matcher(obj.getString("str_zjzh"));
                            if (!isNum.matches()) {
                                continue;
                            }
                            try {
                                stmt.setLong(1, Long.valueOf(obj.getString("str_zjzh")));
                                stmt.setTimestamp(2, new Timestamp(sdf.parse(obj.getString("@timestamp")).getTime()) );
                                stmt.addBatch();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    stmt.executeBatch();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("批量任务执行完成:" + startOfDay + "-" + endOfDay);
    }

}
