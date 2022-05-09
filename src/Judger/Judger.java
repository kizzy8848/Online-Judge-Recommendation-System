package Judger;

/**
 * judger连接判题接口
 */

import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URL;

public class Judger {
    public  String sendPost(JsonObject jsonObject) throws IOException {
        String body="";
        String url="http://212.64.64.108:8080/judge";
        String requestUrl="http://hnusit.cn:8080/judge";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestUrl);
        StringEntity stringEntity=new StringEntity(jsonObject.toString(),"utf-8");
        stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
        httpPost.setEntity(stringEntity);

        httpPost.setHeader("X-Judge-Server-Token", "b824cecedb22b06c3883b1f1dd9dd3150608fc24f8d0c16b0f85af8c8c761667");
        httpPost.setHeader("Content-Type","application/json; charset=utf-8");
        CloseableHttpResponse response=client.execute(httpPost);
        HttpEntity entity=response.getEntity();
        if(entity!=null){
            body = EntityUtils.toString(entity,"UTF-8");
        }
        EntityUtils.consume(entity);
        response.close();
        System.out.println(body);
        return body;
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public static String sendPost1() throws IOException {
        String body="";
        String requestUrl="http://212.64.64.108:8080/ping";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestUrl);
//        StringEntity stringEntity=new StringEntity(jsonObject.toString(),"utf-8");
//        stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
//        httpPost.setEntity(stringEntity);

        httpPost.setHeader("X-Judge-Server-Token", "b824cecedb22b06c3883b1f1dd9dd3150608fc24f8d0c16b0f85af8c8c761667");
        httpPost.setHeader("Content-Type","application/json; charset=utf-8");
        CloseableHttpResponse response=client.execute(httpPost);
        HttpEntity entity=response.getEntity();
        if(entity!=null){
            body = EntityUtils.toString(entity,"UTF-8");
        }
        EntityUtils.consume(entity);
        response.close();
        return body;
    }
    public  String sendPostByString(String jsonObject) throws IOException {
        String body="";
        String requestUrl="http://212.64.64.108:8080/judge";
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestUrl);
        StringEntity stringEntity=new StringEntity(jsonObject,"utf-8");
        stringEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,"application/json"));
        httpPost.setEntity(stringEntity);

        httpPost.setHeader("X-Judge-Server-Token", "b824cecedb22b06c3883b1f1dd9dd3150608fc24f8d0c16b0f85af8c8c761667");
        httpPost.setHeader("Content-Type","application/json; charset=utf-8");
        CloseableHttpResponse response=client.execute(httpPost);
        HttpEntity entity=response.getEntity();
        if(entity!=null){
            body = EntityUtils.toString(entity,"UTF-8");
        }
        EntityUtils.consume(entity);
        response.close();
        return body;
    }

    public  static void main(String[] args)throws Exception{
//        String jsonstr="{\"judger_version\":\"2.0.1\", \"hostname\": \"c45acd557074\", \"running_task_number\": \"2\", \"cpu_core\": \"1\", \"memory\":\"30.3\", \"action\": \"heartbeat\", \"cpu\": \"0\", \"service_url\": \"\"}";
//        JSONObject jsonObject= JSON.parseObject(jsonstr);
        String result=sendPost1();
        System.out.println(result);
    }
}
