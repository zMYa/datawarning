package com.feihe.util;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * 通过Http get post 获取数据
 * @author 若水
 *
 */
public class HttpClientUtil {
	
	 //get接口掉方法
    public static String get(String str){
        HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 120000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 120000);
        
        URL url = null;
        URI uri = null;
		try {
			url = new URL(str);
			uri = new URI(url.getProtocol(), url.getHost(), url.getPath(), url.getQuery(), null);
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        HttpGet httpGet = new HttpGet(str);

        String entityStr = null;
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity entity = httpResponse.getEntity();
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            System.out.println("statusCode:"+statusCode);
            entityStr = EntityUtils.toString(entity,"gbk");
            System.out.println("响应返回内容:"+entityStr);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
        	httpGet.abort();
        }

        return entityStr;
    }

    //post提交调用方法
    public static String post(String url,List<NameValuePair> nvps){
        HttpClient httpClient = new DefaultHttpClient();
     // 设置超时时间
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 120000);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 120000);
        
        HttpPost httpPost = new HttpPost(url);
     // 构造消息头
        //httpPost.setHeader("Content-type", "application/json; charset=utf-8");
        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader("Connection", "Close");
        String sessionId = getSessionId();
        httpPost.setHeader("SessionId", sessionId);
     // 构建消息实体
        //StringEntity entity = new StringEntity(text, Charset.forName("UTF-8"));
        //entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        //entity.setContentType("application/json");

        try {
        	httpPost.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));
            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==HttpStatus.SC_OK){
            	HttpEntity resEntity = response.getEntity();
            	String entityStr = EntityUtils.toString(resEntity,"UTF-8");
            	return entityStr;
            }
            System.out.println(statusCode);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    
 // 构建唯一会话Id
    public static String getSessionId(){
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23) + str.substring(24);
    }

    public static void main(String[] args) throws Exception {
        //String callInterface = callInterface();
        //System.out.println("调用成功："+callInterface);
    	//callInterface();

    }
}
