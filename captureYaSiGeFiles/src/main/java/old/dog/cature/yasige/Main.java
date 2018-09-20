package old.dog.cature.yasige;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Main
 *
 * @author yong.han
 * 2018/9/20
 */
public class Main {


    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost request = new HttpPost();


        CloseableHttpResponse response = httpClient.execute(request);

        HttpEntity entity = response.getEntity();
        entity.getContent();

        httpClient.close();
    }

}
