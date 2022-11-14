package com.bupt.awesomejava.scaffold.httpcomponent;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HTTPRequestUtils {
    /**
     *
     * @param url
     * @return
     */
    public String doGet(String url) {
        Logger logger = LogManager.getLogger(HTTPRequestUtils.class);
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            // httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Throwable t) {
            // e.printStackTrace();
            logger.error("发送http请求失败：" + httpGet.getURI(), t);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                // e.printStackTrace();
                logger.error("关闭http请求资源失败：" + httpGet.getURI(), e);
            }
        }
        return responseContent;
    }



    /**
     * post请求,参数是json格式;
     * @param url
     * @param jsonStr
     * @param charset
     * @return
     */
    public String sendPostJson(String url, String jsonStr, String charset) {
        // HttpClient httpClient = null;
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            // 经测试默认链接支持10e4的链接数;
            httpClient = HttpClients.createDefault();

            httpPost = new HttpPost(url);
            // 设置post参数格式;
            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader("Connection", "Close");
            httpPost.setHeader("apikey","csc-ecomm-web-inner-test");
            StringEntity entity = new StringEntity(jsonStr.toString(), Charset.forName(charset));
            entity.setContentEncoding("UTF-8");  // 设置编码格式
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            // 存储消息实体
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * post请求,参数是json格式;
     * sendPostJson在链接数过多(10e4)的时候产生关闭连接的情况
     * 使用PoolingHttpClientConnectionManager并根据输出要求设置了maxtotal和defaultmaxperroute值;
     * @param url
     * @param jsonStr
     * @param charset
     * @return
     */
    public String sendPostJsonPro(String url, String jsonStr, String charset) {
        // PoolingHttpClientConnectionManager httpClient = null;
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            // httpClient = new PoolingHttpClientConnectionManager();
            httpPost = new HttpPost(url);
            // 设置post参数格式;
            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader("Connection", "Close");
            httpPost.setHeader("apikey","csc-ecomm-web-inner-test");
            StringEntity entity = new StringEntity(jsonStr.toString(), Charset.forName(charset));
            entity.setContentEncoding("UTF-8");  // 设置编码格式
            // 发送Json格式的数据请求
            entity.setContentType("application/json");
            // 存储消息实体
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * post请求,参数是form-data格式;
     * @param url
     * @param map
     * @param charset
     * @return
     */
    public String sendPostFormData(String url, Map<String,String> map, String charset) {
        // HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            // httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);

            httpPost.setHeader("Content-type", "application/json;charset=utf-8");
            httpPost.setHeader("Connection", "Close");

            // 设置参数
            List<NameValuePair> list = new ArrayList<>();
            Iterator iterator = map.entrySet().iterator();

            while(iterator.hasNext()) {
                Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }

            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if(resEntity != null) {
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}

