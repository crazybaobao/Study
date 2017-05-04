package com.example.wangyun.fuckj.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by wangyun on 2017/5/3.
 */

public class HttpUtil {
    public static HttpClient httpClient = new DefaultHttpClient();
    public static final String BSAE_URL = "http://www.baidu.com";

    public static String getRequest(final String url) throws Exception {
        FutureTask<String> task = new FutureTask<String>(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        HttpGet get = new HttpGet(url);
                        HttpResponse httpResponse = httpClient.execute(get);
                        if (httpResponse.getStatusLine().getStatusCode() == 200) {
                            String result = EntityUtils.toString(httpResponse.getEntity());
                            return result;
                        }
                        return null;
                    }
                }
        );
        new Thread(task).start();
        return task.get();
    }

}
