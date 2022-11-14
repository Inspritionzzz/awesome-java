package com.bupt.awesomejava.scaffold.httpcomponent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ISTPCollectDataJob {

    public static final String ISTP_enterprise_getBasicInfo_DEV =
            "http://10.237.121.82/inner/info/qixin/enterprise/getBasicInfo";

    public static final String ISTP_enterprise_getBasicInfo_PRO =
            "http://10.16.192.68/inner/info/qixin/enterprise/getBasicInfo";

    public static final String ISTP_enterprise_getAbnormalsInfo_DEV =
            "http://10.237.121.82/inner/info/qixin/enterprise/getAbnormals";

    public static final String ISTP_enterprise_getAbnormalsInfo_PRO =
            "http://10.16.192.68/inner/info/qixin/enterprise/getAbnormals";

    public static final String ISTP_enterprise_getSeriousIllegal_DEV =
            "http://10.237.121.82/inner/info/qixin/enterprise/getSeriousIllegalByName";

    public static final String ISTP_enterprise_getSeriousIllegal_PRO =
            "http://10.16.192.68/inner/info/qixin/enterprise/getSeriousIllegalByName";

    /**
     * 企业工商照面基本信息
     * @throws Exception
     */
    public void getEnterpriseBasicInfo() throws Exception {
        /*
            http请求:
            {
                "keyword": "小米科技有限责任公司",
                "appkey": "fb3fe5de-8ce7-4bf2-a9ea-a19f5fb561e0",
                "secret_key": "f7bbe70a-db06-4f10-9e13-f2788c249f3d"
            }

            http响应:
            {
                "status": "200",
                "message": "操作成功",
                "sign": "2e4407a32694bc5b76fbb5bdf4bcc829",
                "data": {
                    "id": "534472fd-7d53-4958-8132-d6a6242423d8",
                    "name": "小米科技有限责任公司",
                    "format_name": "小米科技有限责任公司",
                    "econKind": "有限责任公司(自然人投资或控股)",
                    "econKindCode": "1130",
                    "registCapi": "185000 万人民币",
                    "currency_unit": "CNY",
                    "type_new": "01",
                    "historyNames": [
                        "北京小米科技有限责任公司"
                    ],
                    "address": "北京市海淀区西二旗中路33号院6号楼6层006号",
                    "regNo": "110108012660422",
                    "scope": "技术开发；货物进出口、技术进出口、代理进出口；销售通讯设备、厨房用品、卫生用品（含个人护理用品）、日用杂货、化妆品、医疗器械Ⅰ类、Ⅱ类、避孕器具、玩具、体育用品、文化用品、服装鞋帽、钟表眼镜、针纺织品、家用电器、家具（不从事实体店铺经营）、花、草及观赏植物、不再分装的包装种子、照相器材、工艺品、礼品、计算机、软件及辅助设备、珠宝首饰、食用农产品、宠物食品、电子产品、摩托车、电动车、自行车及零部件、智能卡、五金交电（不从事实体店铺经营）、建筑材料（不从事实体店铺经营）；维修仪器仪表；维修办公设备；承办展览展示活动；会议服务；筹备、策划、组织大型庆典；设计、制作、代理、发布广告；摄影扩印服务；文艺演出票务代理、体育赛事票务代理、展览会票务代理、博览会票务代理；手机技术开发；手机生产、手机服务；从事互联网文化活动；出版物零售；出版物批发；销售第三类医疗器械；销售食品；零售药品；广播电视节目制作；经营电信业务。（市场主体依法自主选择经营项目，开展经营活动；从事互联网文化活动、出版物批发、出版物零售、销售食品、经营电信业务、广播电视节目制作、零售药品、销售第三类医疗器械以及依法须经批准的项目，经相关部门批准后依批准的内容开展经营活动；不得从事国家和本市产业政策禁止和限制类项目的经营活动。）",
                    "termStart": "2010-03-03",
                    "termEnd": "2030-03-02",
                    "belongOrg": "北京市海淀区市场监督管理局",
                    "operName": "雷军",
                    "title": "法定代表人",
                    "startDate": "2010-03-03",
                    "endDate": "-",
                    "checkDate": "2021-04-27",
                    "status": "存续",
                    "new_status": "存续",
                    "orgNo": "551385082",
                    "creditNo": "91110108551385082Q",
                    "districtCode": "110108",
                    "actualCapi": "185000 万人民币",
                    "categoryNew": "0115601",
                    "domain": "M7590",
                    "tags": [],
                    "revoke_reason": "-",
                    "revoke_date": "-"
                }
        }
        */

        HTTPRequestUtils connection = new HTTPRequestUtils();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        String taskBeginTime = formatter.format(new Date(System.currentTimeMillis()));

        // 1.连接数据库并读取数据;
        // jdbc:sqlserver://127.0.0.1:1433;DatabaseName=model;
        String url = "jdbc:sqlserver://10.237.1.250:2433;selectMethod=cursor;";
        // String url = "jdbc:sqlserver://10.16.232.139";
        // 连接测试数据库;
        Connection conn = DriverManager.getConnection(url, "sa", "85130268");
        // Connection conn = DriverManager.getConnection(url, "sa", "c65178899Sc");
        // 创建一个 Statement 对象来将 SQL 语句发送到数据库;
        Statement stat = conn.createStatement();
        // 执行SQL:需要查询客户号、手机号、custorgid;
        // ResultSet resultSet = stat.executeQuery("select top 100 * from crmcounter.dbo.t_counter_khfl with(nolock)");
        ResultSet resultSet = stat.executeQuery("select top 10 keyword, appkey, secret_key " +
                                                    "from test.dbo.enterprise_basic_info_source");

        // ResultSet resultSet = stat.executeQuery("select custid,phone from crmcounter.dbo.cust_phone");
        // Map<String,String> custPhoneInfo = new HashMap<>();
        Map<String,String> enterpriseInfo = new HashMap<>();
        while (resultSet.next()) {

            // custPhoneInfo.put(resultSet.getString("custid"),resultSet.getString("phone"));
            // custPhoneInfo.put("phone","15634333933"); // 测试数据;
            // 构建json请求参数;
            enterpriseInfo.put("keyword",resultSet.getString("keyword"));
            enterpriseInfo.put("appkey",resultSet.getString("appkey"));
            enterpriseInfo.put("secret_key",resultSet.getString("secret_key"));

            String jsonStr = JSON.toJSONString(enterpriseInfo,true);
            System.out.println("request json:" + jsonStr);

            String response = connection.sendPostJson(ISTP_enterprise_getBasicInfo_DEV,jsonStr,"UTF-8");
            // 解析数据:{"data":{"province":"山东","city":"山东","company":"中国联通网络"},"errmsg":"成功","status":200}
            JSONObject jsonObject = JSON.parseObject(response);

            System.out.println("response:" + response);
            System.out.println("response data:" + jsonObject);
            String sql = "insert into aml.dbo.istp_enterprise_basic_info_target" +
                         "(keyword,appkey,secret_key,id,name,format_name,econKind,econKindCode,registCapi,currency_unit," +
                         "type_new,historyNames,address,regNo,scope,termStart,termEnd,belongOrg,operName,title,startDate," +
                         "endDate,checkDate,status,new_status,orgNo,creditNo,districtCode,actualCapi,categoryNew,domain," +
                         "tags,revoke_reason,revoke_date,response)" +
                         "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            JSONObject param = jsonObject.getJSONObject("data");
            Map<String, String> responseDataMap = new HashMap<>();
            System.out.println(param);
            Iterator iterator = param.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String val = param.getString(key);
                System.out.println("[key="+key+" val="+val+"]");
                responseDataMap.put(key, val);
            }
            System.out.println(responseDataMap.get("format_name"));
            ps.setString(1,resultSet.getString("keyword"));
            ps.setString(2,resultSet.getString("appkey"));
            ps.setString(3,resultSet.getString("secret_key"));
            ps.setString(4,responseDataMap.get("id"));
            ps.setString(5,responseDataMap.get("name"));
            ps.setString(6,responseDataMap.get("format_name"));
            ps.setString(7,responseDataMap.get("econKind"));
            ps.setString(8,responseDataMap.get("econKindCode"));
            ps.setString(9,responseDataMap.get("registCapi"));
            ps.setString(10,responseDataMap.get("currency_unit"));
            ps.setString(11,responseDataMap.get("type_new"));
            ps.setString(12,responseDataMap.get("historyNames"));
            ps.setString(13,responseDataMap.get("address"));
            ps.setString(14,responseDataMap.get("regNo"));
            ps.setString(15,responseDataMap.get("scope"));
            ps.setString(16,responseDataMap.get("termStart"));
            ps.setString(17,responseDataMap.get("termEnd"));
            ps.setString(18,responseDataMap.get("belongOrg"));
            ps.setString(19,responseDataMap.get("operName"));
            ps.setString(20,responseDataMap.get("title"));
            ps.setString(21,responseDataMap.get("startDate"));
            ps.setString(22,responseDataMap.get("endDate"));
            ps.setString(23,responseDataMap.get("checkDate"));
            ps.setString(24,responseDataMap.get("status"));
            ps.setString(25,responseDataMap.get("new_status"));
            ps.setString(26,responseDataMap.get("orgNo"));
            ps.setString(27,responseDataMap.get("creditNo"));
            ps.setString(28,responseDataMap.get("districtCode"));
            ps.setString(29,responseDataMap.get("actualCapi"));
            ps.setString(30,responseDataMap.get("categoryNew"));
            ps.setString(31,responseDataMap.get("domain"));
            ps.setString(32,responseDataMap.get("tags"));
            ps.setString(33,responseDataMap.get("revoke_reason"));
            ps.setString(34,responseDataMap.get("revoke_date"));
            ps.setString(35,response);

            enterpriseInfo.clear();
            ps.executeUpdate();

            System.out.println(responseDataMap);

        }
        // 6.关闭数据库连接;
        stat.close();
        conn.close();
        System.out.println("任务开始时间:" + taskBeginTime);
        System.out.println("任务结束时间:" + formatter.format(new Date(System.currentTimeMillis())));
    }

    /**
     * 经营异常信息
     * @throws Exception
     */
    public void getEnterpriseAbnormalsInfo() throws Exception {
        /*
            http请求:
            {
                "keyword": "深圳市东桥再生资源有限公司",
                "skip":"2",
                "appkey": "fb3fe5de-8ce7-4bf2-a9ea-a19f5fb561e0",
                "secret_key": "f7bbe70a-db06-4f10-9e13-f2788c249f3d"
            }

            http响应:
            {
                "status": "200",
                "message": "操作成功",
                "sign": "289e059b10a8ffff5413f0cc3902efb9",
                "data": {
                    "total": 6,
                    "items": [
                        {
                            "name": "深圳市东桥再生资源有限公司",
                            "regNo": "440301105984804",
                            "inReason": "通过登记的住所或经营场所无法取得联系",
                            "inDate": "2016-06-28",
                            "outReason": "通过登记的住所或经营场所可以重新取得联系",
                            "outDate": "2016-07-29",
                            "department": "盐田局",
                            "province": "GD",
                            "disabled": "1"
                        },
                        {
                            "name": "深圳市东桥再生资源有限公司",
                            "regNo": "440301105984804",
                            "inReason": "通过登记的住所或经营场所无法联系",
                            "inDate": "2015-01-28",
                            "outReason": "通过登记的住所（经营场所）可以重新取得联系",
                            "outDate": "2016-03-22",
                            "department": "深圳市市场和质量监督管理委员会盐田局",
                            "province": "GD",
                            "disabled": "0"
                        },
                        {
                            "name": "深圳市东桥再生资源有限公司",
                            "regNo": "440301105984804",
                            "inReason": "通过登记的住所或者经营场所无法联系的",
                            "inDate": "2015-01-28",
                            "outReason": "列入经营异常名录3年内且依照《经营异常名录管理办法》第九条规定被列入经营异常名录的企业，提出通过登记的住所或者经营场所可以重新取得联系，申请移出",
                            "outDate": "2016-03-22",
                            "department": "深圳市市场和质量监督管理委员会盐田局",
                            "province": "GD",
                            "disabled": "1"
                        },
                        {
                            "name": "深圳市东桥再生资源有限公司",
                            "regNo": "440301105984804",
                            "inReason": "通过登记的住所或经营场所无法取得联系",
                            "inDate": "2015-01-28",
                            "outReason": "通过登记的住所或经营场所可以重新取得联系",
                            "outDate": "2016-03-23",
                            "department": "盐田局",
                            "province": "GD",
                            "disabled": "1"
                        }
                    ]
                }
            }
         */

        HTTPRequestUtils connection = new HTTPRequestUtils();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        String taskBeginTime = formatter.format(new Date(System.currentTimeMillis()));

        // 1.连接数据库并读取数据;
        // jdbc:sqlserver://127.0.0.1:1433;DatabaseName=model;
        String url = "jdbc:sqlserver://10.237.1.250:2433;selectMethod=cursor;";
        // String url = "jdbc:sqlserver://10.16.232.139";
        // 连接测试数据库;
        Connection conn = DriverManager.getConnection(url, "sa", "85130268");
        // Connection conn = DriverManager.getConnection(url, "sa", "c65178899Sc");
        // 创建一个 Statement 对象来将 SQL 语句发送到数据库;
        Statement stat = conn.createStatement();
        // 执行SQL:需要查询客户号、手机号、custorgid;
        // ResultSet resultSet = stat.executeQuery("select top 100 * from crmcounter.dbo.t_counter_khfl with(nolock)");
        ResultSet resultSet = stat.executeQuery("select top 10 keyword, skip, appkey,secret_key " +
                                                    "from aml.dbo.istp_enterprise_abnormals_info_source");

        // ResultSet resultSet = stat.executeQuery("select custid,phone from crmcounter.dbo.cust_phone");
        // Map<String,String> custPhoneInfo = new HashMap<>();
        Map<String,String> enterpriseInfo = new HashMap<>();
        while (resultSet.next()) {

            // 构建json请求参数;
            enterpriseInfo.put("keyword",resultSet.getString("keyword"));
            enterpriseInfo.put("skip",resultSet.getString("skip"));
            enterpriseInfo.put("appkey",resultSet.getString("appkey"));
            enterpriseInfo.put("secret_key",resultSet.getString("secret_key"));

            String jsonStr = JSON.toJSONString(enterpriseInfo,true);
            System.out.println(jsonStr);

            String response = connection.sendPostJson(ISTP_enterprise_getAbnormalsInfo_DEV,jsonStr,"UTF-8");
            JSONObject jsonObject = JSON.parseObject(response);

            String sql = "insert into aml.dbo.istp_enterprise_abnormals_info_target" +
                         "(keyword,skip,appkey,secret_key,total,items,response)" +
                         "values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            JSONObject param = jsonObject.getJSONObject("data");
            Map<String, String> responseDataMap = new HashMap<>();
            Iterator iterator = param.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String val = param.getString(key);
                responseDataMap.put(key, val);
            }
            ps.setString(1,resultSet.getString("keyword"));
            ps.setString(2,resultSet.getString("skip"));
            ps.setString(3,resultSet.getString("appkey"));
            ps.setString(4,resultSet.getString("secret_key"));
            ps.setString(5,responseDataMap.get("total"));
            ps.setString(6,responseDataMap.get("items"));
            ps.setString(7,responseDataMap.get("response"));
            enterpriseInfo.clear();
            ps.executeUpdate();
        }
        // 6.关闭数据库连接;
        stat.close();
        conn.close();
        System.out.println("任务开始时间:" + taskBeginTime);
        System.out.println("任务结束时间:" + formatter.format(new Date(System.currentTimeMillis())));
    }

    /**
     * 严重违法信息
     * @throws Exception
     */
    public void getEnterpriseSeriousIllegalByName() throws Exception {
        /**
         * http请求：
         * {
         *     "name": "深圳市领富行电子有限公司",
         *     "appkey": "fb3fe5de-8ce7-4bf2-a9ea-a19f5fb561e0",
         *     "secret_key": "f7bbe70a-db06-4f10-9e13-f2788c249f3d"
         * }
         *
         * http响应:
         * {
         *     "status": "200",
         *     "message": "操作成功",
         *     "sign": "4a9e8ceca5bccf02a6671710224b0676",
         *     "data": {
         *         "execution": [
         *             {
         *                 "in_reason": "被列入经营异常名录届满3年仍未履行相关义务的",
         *                 "in_date": "2018-02-05",
         *                 "in_department": "深圳市市场监督管理局罗湖局",
         *                 "out_reason": "已注销",
         *                 "out_date": "2019-06-25",
         *                 "out_department": "深圳市市场监督管理局罗湖局",
         *                 "is_history": 0
         *             }
         *         ],
         *         "executed_person": [],
         *         "tax_violation": []
         *     }
         * }
         */

        HTTPRequestUtils connection = new HTTPRequestUtils();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        String taskBeginTime = formatter.format(new Date(System.currentTimeMillis()));

        // 1.连接数据库并读取数据;
        // jdbc:sqlserver://127.0.0.1:1433;DatabaseName=model;
        String url = "jdbc:sqlserver://10.237.1.250:2433;selectMethod=cursor;";
        // String url = "jdbc:sqlserver://10.16.232.139";
        // 连接测试数据库;
        Connection conn = DriverManager.getConnection(url, "sa", "85130268");
        // Connection conn = DriverManager.getConnection(url, "sa", "c65178899Sc");
        // 创建一个 Statement 对象来将 SQL 语句发送到数据库;
        Statement stat = conn.createStatement();
        // 执行SQL:需要查询客户号、手机号、custorgid;
        // ResultSet resultSet = stat.executeQuery("select top 100 * from crmcounter.dbo.t_counter_khfl with(nolock)");
        ResultSet resultSet = stat.executeQuery("select top 10 name, appkey, secret_key " +
                "from aml.dbo.istp_enterprise_seriousillegal_info_source");

        // ResultSet resultSet = stat.executeQuery("select custid,phone from crmcounter.dbo.cust_phone");
        // Map<String,String> custPhoneInfo = new HashMap<>();
        Map<String,String> enterpriseInfo = new HashMap<>();
        while (resultSet.next()) {
            // custPhoneInfo.put(resultSet.getString("custid"),resultSet.getString("phone"));
            // custPhoneInfo.put("phone","15634333933"); // 测试数据;
            // 构建json请求参数;
            enterpriseInfo.put("name",resultSet.getString("name"));
            enterpriseInfo.put("appkey",resultSet.getString("appkey"));
            enterpriseInfo.put("secret_key",resultSet.getString("secret_key"));
            String jsonStr = JSON.toJSONString(enterpriseInfo,true);

            System.out.println(jsonStr);

            String response = connection.sendPostJson(ISTP_enterprise_getSeriousIllegal_DEV,jsonStr,"UTF-8");

            JSONObject jsonObject = JSON.parseObject(response);
            System.out.println(jsonObject);
            String sql = "insert into aml.dbo.istp_enterprise_seriousillegal_info_target" +
                    "(name,appkey,secret_key,execution,executed_person,tax_violation,response)" +
                    "values(?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            // 存储json中data的基本信息
            JSONObject param = jsonObject.getJSONObject("data");
            Map<String, String> responseDataMap = new HashMap<>();
            Iterator iterator = param.keySet().iterator();
            while (iterator.hasNext()){
                String key = (String) iterator.next();
                String val = param.getString(key);
                responseDataMap.put(key, val);
            }

            // 解析json中data的具体信息
            ps.setString(1,resultSet.getString("name"));
            ps.setString(2,resultSet.getString("appkey"));
            ps.setString(3,resultSet.getString("secret_key"));
            ps.setString(4,responseDataMap.get("execution"));
            ps.setString(5,responseDataMap.get("executed_person"));
            ps.setString(6,responseDataMap.get("tax_violation"));
            ps.setString(7,responseDataMap.get("response"));
            enterpriseInfo.clear();
            ps.executeUpdate();

        }
        // 6.关闭数据库连接;
        stat.close();
        conn.close();
        System.out.println("任务开始时间:" + taskBeginTime);
        System.out.println("任务结束时间:" + formatter.format(new Date(System.currentTimeMillis())));
    }
}
