package com.bms.system.http;

import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author : zhaoyi
 * @version : 1.0
 * @description :
 * @date : 2015-12-01 10:16
 */
public class HttpUtil
{
    private static PoolingHttpClientConnectionManager connectionManager = null;

    private static HttpClientBuilder httpBuilder = null;

    private static RequestConfig requestConfig = null;

    private static int MAXCONNECTIONS = 10;

    private static int DEFAULTMAXCONNECTIONS = 5;

    static {
        // 设置http的状态参数
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAXCONNECTIONS);
        connectionManager.setDefaultMaxPerRoute(DEFAULTMAXCONNECTIONS);
        httpBuilder = HttpClients.custom();
        httpBuilder.setConnectionManager(connectionManager);
    }

    public static CloseableHttpClient getConnection()
    {
        return httpBuilder.build();
    }

    public static HttpUriRequest getRequestMethod(String url, Map<String, String> params, String method)
    {
        List<NameValuePair> requestParams = new ArrayList<>();
        Set<Map.Entry<String, String>> entrySet = params.entrySet();
        for (Map.Entry<String, String> entry : entrySet)
        {
            requestParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        HttpUriRequest requestUri = null;
        if ("POST".equals(method))
        {
            requestUri = RequestBuilder.post().setUri(url)
                    .addParameters(requestParams.toArray(new BasicNameValuePair[requestParams.size()]))
                    .setConfig(requestConfig).build();
        }
        else if ("GET".equals(method))
        {
            requestUri = RequestBuilder.get().setUri(url)
                    .addParameters(requestParams.toArray(new BasicNameValuePair[requestParams.size()]))
                    .setConfig(requestConfig).build();

        }

        return requestUri;
    }

    /**
     * http get 请求
     *
     * @param url 请求地址
     * @return 返回值
     */
    public static String httpGet(String url)
    {
        String result = null;
        try
        {
            // 获取当前客户端对象
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT).build();
            CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
            // 根据地址获取请求
            HttpGet request = new HttpGet(url);

            // 通过请求对象获取响应对象
            HttpResponse response = httpClient.execute(request);

            // 判断网络连接状态码是否正常(0--200都数正常)
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
            {
                result = EntityUtils.toString(response.getEntity());
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public static String httpPost(String url, Map<String, String> params)
    {
        String result = null;
        try
        {
            HttpPost post = new HttpPost(url);


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * https 请求，包括GET和POST方式
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式：GET POST
     * @param outputStr     请求参数
     * @return 返回值，String类型
     */
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr)
    {
        StringBuffer buffer = new StringBuffer();
        try
        {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr)
            {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null)
            {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            return buffer.toString();
        }
        catch (ConnectException ce)
        {
            System.out.println("Weixin server connection timed out.");
        }
        catch (Exception e)
        {
            System.out.println("https request error:{}" + e);
        }
        return null;
    }

    /**
     * https 请求，包括GET和POST方式，针对返回值为json，封装成json对象
     *
     * @param requestUrl    请求地址
     * @param requestMethod 请求方式：GET POST
     * @param outputStr     请求参数
     * @return 返回值，JSONObject类型
     */
    public static JSONObject httpReq(String requestUrl, String requestMethod, String outputStr)
    {
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try
        {
            // 创建SSLContext对象，并使用我们指定的信任管理器初始化
            TrustManager[] tm = {new MyTrustManager()};
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
            sslContext.init(null, tm, new java.security.SecureRandom());
            // 从上述SSLContext对象中得到SSLSocketFactory对象
            SSLSocketFactory ssf = sslContext.getSocketFactory();

            URL url = new URL(requestUrl);
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
            httpUrlConn.setSSLSocketFactory(ssf);

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            if ("GET".equalsIgnoreCase(requestMethod))
                httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr)
            {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null)
            {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
            jsonObject = JSONObject.fromObject(buffer.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
