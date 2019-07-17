package com.hanyong.examples.httpclient;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * HtmlDownload
 *
 * @author yong.han
 * 2019/6/11
 */
public class HtmlDownload {


    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpHost host = null;
            HttpGet req = new HttpGet("https://www.zhihu.com/search?type=content&q=%E8%BF%90%E8%90%A5%E6%95%B0%E6%8D%AE%E6%8A%93%E5%8F%96");

            req.addHeader("User-Agent", "PostmanRuntime/7.13.0");
            req.addHeader("Accept", "*/*");
            req.addHeader("Cache-Control", "no-cache");
            req.addHeader("Postman-Token", "508c5f25-6033-4e56-901a-dcc44326615d");
            req.addHeader("Host", "www.zhihu.com");
            req.addHeader("cookie", "_zap=8aed7e9c-abd3-4e7a-9f2f-7546296dd23d; _xsrf=dea31967-e595-4c35-a3f3-bf4dcff04067; d_c0=\"AECqGDFckQ-PToO3OHMxPltofBE_2b7h0Jo=|1560232990\"");
            req.addHeader("accept-encoding", "gzip, deflate");
            req.addHeader("Connection", "keep-alive");


            String resp = httpClient.execute(req, new BasicResponseHandler());

            System.out.println(resp.replaceAll("><", ">\n<"));
//            Stream.of((resp.split("/><"))).forEach(System.out::println);
//
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
