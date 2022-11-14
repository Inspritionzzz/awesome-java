package com.bupt.awesomejava.scaffold.httpcomponent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class JSONUtils {

    public static void main(String[] args) {
        // parseJson();
        parseJson02();
    }

    public static void parseJson() {
        String response = "{\n" +
                "  \"status\": \"200\",\n" +
                "  \"message\": \"操作成功\",\n" +
                "  \"sign\": \"126b3397119c463c806db22354add3b4\",\n" +
                "  \"data\": {\n" +
                "    \"total\": 6,\n" +
                "    \"items\": [\n" +
                "      {\n" +
                "        \"name\": \"深圳市东桥再生资源有限公司\",\n" +
                "        \"regNo\": \"440301105984804\",\n" +
                "        \"inReason\": \"通过登记的住所或经营场所无法联系\",\n" +
                "        \"inDate\": \"2016-06-28\",\n" +
                "        \"outReason\": \"通过登记的住所（经营场所）可以重新取得联系\",\n" +
                "        \"outDate\": \"2016-07-26\",\n" +
                "        \"department\": \"深圳市市场和质量监督管理委员会盐田局\",\n" +
                "        \"province\": \"GD\",\n" +
                "        \"disabled\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"深圳市东桥再生资源有限公司\",\n" +
                "        \"regNo\": \"440301105984804\",\n" +
                "        \"inReason\": \"通过登记的住所或经营场所无法联系\",\n" +
                "        \"inDate\": \"2015-01-28\",\n" +
                "        \"outReason\": \"通过登记的住所（经营场所）可以重新取得联系\",\n" +
                "        \"outDate\": \"2016-03-22\",\n" +
                "        \"department\": \"深圳市市场和质量监督管理委员会盐田局\",\n" +
                "        \"province\": \"GD\",\n" +
                "        \"disabled\": \"0\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"深圳市东桥再生资源有限公司\",\n" +
                "        \"regNo\": \"440301105984804\",\n" +
                "        \"inReason\": \"通过登记的住所或者经营场所无法联系的\",\n" +
                "        \"inDate\": \"2016-06-28\",\n" +
                "        \"outReason\": \"列入经营异常名录3年内且依照《经营异常名录管理办法》第九条规定被列入经营异常名录的企业，提出通过登记的住所或者经营场所可以重新取得联系，申请移出\",\n" +
                "        \"outDate\": \"2016-07-26\",\n" +
                "        \"department\": \"深圳市市场和质量监督管理委员会盐田局\",\n" +
                "        \"province\": \"GD\",\n" +
                "        \"disabled\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"深圳市东桥再生资源有限公司\",\n" +
                "        \"regNo\": \"440301105984804\",\n" +
                "        \"inReason\": \"通过登记的住所或者经营场所无法联系的\",\n" +
                "        \"inDate\": \"2015-01-28\",\n" +
                "        \"outReason\": \"列入经营异常名录3年内且依照《经营异常名录管理办法》第九条规定被列入经营异常名录的企业，提出通过登记的住所或者经营场所可以重新取得联系，申请移出\",\n" +
                "        \"outDate\": \"2016-03-22\",\n" +
                "        \"department\": \"深圳市市场和质量监督管理委员会盐田局\",\n" +
                "        \"province\": \"GD\",\n" +
                "        \"disabled\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"深圳市东桥再生资源有限公司\",\n" +
                "        \"regNo\": \"440301105984804\",\n" +
                "        \"inReason\": \"通过登记的住所或经营场所无法取得联系\",\n" +
                "        \"inDate\": \"2016-06-28\",\n" +
                "        \"outReason\": \"通过登记的住所或经营场所可以重新取得联系\",\n" +
                "        \"outDate\": \"2016-07-29\",\n" +
                "        \"department\": \"盐田局\",\n" +
                "        \"province\": \"GD\",\n" +
                "        \"disabled\": \"1\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"name\": \"深圳市东桥再生资源有限公司\",\n" +
                "        \"regNo\": \"440301105984804\",\n" +
                "        \"inReason\": \"通过登记的住所或经营场所无法取得联系\",\n" +
                "        \"inDate\": \"2015-01-28\",\n" +
                "        \"outReason\": \"通过登记的住所或经营场所可以重新取得联系\",\n" +
                "        \"outDate\": \"2016-03-23\",\n" +
                "        \"department\": \"盐田局\",\n" +
                "        \"province\": \"GD\",\n" +
                "        \"disabled\": \"1\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}\n";
        System.out.println(response);

        JSONObject jsonObject = JSON.parseObject((JSON.parseObject(response).getString("data")));

        Integer total = Integer.parseInt(jsonObject.getString("total"));
        JSONArray jsonArray =  JSONObject.parseArray(jsonObject.getString("items"));
        Map<Integer, Map<String,Object>> jsonParseResult = new HashMap<>();
        Gson gson = new Gson();
        for(int i = 0; i < total; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map = gson.fromJson(jsonArray.getString(i), map.getClass());
            jsonParseResult.put(i,map);
        }
        System.out.println(jsonParseResult);
    }

    public static void parseJson02() {
        String response = "{\n" +
                "  \"status\": \"200\",\n" +
                "  \"message\": \"操作成功\",\n" +
                "  \"sign\": \"aa3efbcb76704ec6adf8ea7b705861bb\",\n" +
                "  \"data\": {\n" +
                "    \"execution\": [\n" +
                "      {\n" +
                "        \"in_reason\": \"被列入经营异常名录届满3年仍未履行相关义务的\",\n" +
                "        \"in_date\": \"2018-02-05\",\n" +
                "        \"in_department\": \"深圳市市场监督管理局罗湖局\",\n" +
                "        \"out_reason\": \"已注销\",\n" +
                "        \"out_date\": \"-\",\n" +
                "        \"out_department\": \"深圳市市场监督管理局罗湖局\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"executed_person\": [],\n" +
                "    \"tax_violation\": []\n" +
                "  }\n" +
                "}\n";

        JSONObject jsonObject = JSON.parseObject((JSON.parseObject(response).getString("data")));
        // JSONArray jsonArray =  JSONObject.parseArray(jsonObject.getString("execution"));
        Map<String, Map<String,String>> jsonParseResult = new HashMap<>();
        Gson gson = new Gson();
        for(int i = 0; i < jsonObject.size(); i++) {
            JSONArray jsonArray01 =  JSONObject.parseArray(jsonObject.getString("execution"));
            for(int j = 0; j < jsonArray01.size(); j++){
                Map<String, String> map = new HashMap<>();
                map = gson.fromJson(jsonArray01.getString(j),map.getClass());
                jsonParseResult.put("1",map);
            }
            JSONArray jsonArray02 =  JSONObject.parseArray(jsonObject.getString("executed_person"));
            for(int k = 0; k < jsonArray02.size(); k++){
                Map<String, String> map = new HashMap<>();
                map = gson.fromJson(jsonArray02.getString(k),map.getClass());
                jsonParseResult.put("2",map);
            }
            JSONArray jsonArray03 =  JSONObject.parseArray(jsonObject.getString("tax_violation"));
            for(int l = 0; l < jsonArray03.size(); l++){
                Map<String, String> map = new HashMap<>();
                map = gson.fromJson(jsonArray03.getString(l),map.getClass());
                jsonParseResult.put("3",map);
            }
        }
        System.out.println(jsonParseResult);
        System.out.println(jsonParseResult.get("executed_person"));

    }
}
