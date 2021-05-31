package com.nbnfsoft.admin.utils;

import java.util.Iterator;
import java.util.Map;

import com.nbnfsoft.admin.common.constant.HttpStatus;
import com.nbnfsoft.admin.config.OkHttpConfig;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author:louyi
 * @Description：
 * @Date:Create in 14:31 2020-06-03
 */
public class OkHttpUtil {
    private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

    /**
     * 根据map获取get请求参数
     *
     * @param queries
     * @return
     */
    public static StringBuffer getQueryString(String url, Map<String, String> queries) {
        StringBuffer sb = new StringBuffer(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        return sb;
    }

    /**
     * 调用okhttp的newCall方法
     *
     * @param request
     * @return
     */
    private static String exec(Request request) {
        Response response = null;
        try {
            OkHttpClient okHttpClient = SpringUtils.getBean(OkHttpConfig.class).okHttpClient();
            response = okHttpClient.newCall(request).execute();
            int status = response.code();
            if (status == HttpStatus.SUCCESS && response.isSuccessful()) {
                return response.body().string();
            } else {
                logger.error("okhttp3 put error >> status = {}", status);
            }
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {}", ExceptionUtils.getStackTrace(e));
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return "";
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static String get(String url, Map<String, String> queries) {
        StringBuffer sb = getQueryString(url, queries);
        Request request = new Request.Builder()
                .url(sb.toString())
                .build();
        return exec(request);
    }

    /**
     * post
     *
     * @param url    请求的url
     * @param params post form 提交的参数
     * @return
     */
    public static String postFormParams(String url, Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        //添加参数
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        return exec(request);
    }


    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"}
     * 参数一：请求Url
     * 参数二：请求的JSON
     * 参数三：请求回调
     */
    public static String postJsonParams(String url, String jsonParams) {
        RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return exec(request);
    }

    /**
     * Post请求发送xml数据....
     * 参数一：请求Url
     * 参数二：请求的xmlString
     * 参数三：请求回调
     */
    public static String postXmlParams(String url, String xml) {
        RequestBody requestBody = RequestBody.create(xml, MediaType.parse("application/xml; charset=utf-8"));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        return exec(request);
    }

    /**
     * Post请求发送xml数据....
     * 参数一：请求Url
     * 参数二：请求的xmlString
     * 参数三：请求回调
     */
    public static String postTextXmlParams(String url, String xml) {
        RequestBody requestBody = RequestBody.create(xml, MediaType.parse("text/xml; charset=utf-8"));
        Request request = new Request.Builder().addHeader("SOAPAction", "http://tempuri.org/IHisApplay/RunService")
                .url(url)
                .post(requestBody)
                .build();
        return exec(request);
    }

    /**
     * Post请求发送xml数据....
     * 参数一：请求Url
     * 参数二：请求的xmlString
     * 参数三：请求回调
     */
    public static String postTextXmlParams(String url, String xml,String header) {
        RequestBody requestBody = RequestBody.create(xml, MediaType.parse("text/xml; charset=utf-8"));
        Request request = new Request.Builder().addHeader("SOAPAction", header)
                .url(url)
                .post(requestBody)
                .build();
        return exec(request);
    }
}
