package olddog;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/28.
 */
public class LoadTbbAccount {

    static ObjectMapper objectMapper = new ObjectMapper();
    //    static final mediatype
    static MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
    static OkHttpClient okHttpClient = new OkHttpClient();

    static String subsidyUrl = "http://localhost:8088/subsidy";

    public static void main(String[] args) throws IOException {
        subsidy();


    }

    public static void createUser() throws IOException {
//
//        private String bankName;
//        private String bankCard;
//        private String name;
//        private String phone;
//        private String idCard;

        Map<String, String> map = new HashMap<>();



        String userId = null;
        for (int i = 0; i < 100; i++) {
            userId = "userId_" + i;
            map.put("name", "name" + i);
            String json = objectMapper.writeValueAsString(map);

            RequestBody requestBody = RequestBody.create(mediaType, json);

            String url = "http://localhost:8088/user/create/" + userId + "/123456";
            Request request = new Request.Builder().url(url).post(requestBody).build();

            Response response = okHttpClient.newCall(request).execute();

            System.out.println(response.body().string());

        }


    }



    public static void subsidy() throws IOException {
//        "accountId":"1"
//                ,"orderId":"subsidy-order-0013"
//                ,"remarks":"subsidy-order-0001 remarks"
//                ,"amount":100

        Map<String, String> map = new HashMap<>();
        map.put("amount", "100");



        String orderId = null;
        String remarks = null;
        for (int i = 0; i < 20000; i++) {
            orderId = "s-oid-08281745-" + i;
            remarks = "remarkds " + orderId;

            map.put("accountId", String.valueOf(i%100+1));
            map.put("orderId",orderId);
            map.put("remarks", remarks);
            String json = objectMapper.writeValueAsString(map);

            RequestBody requestBody = RequestBody.create(mediaType, json);

            Request request = new Request.Builder().url(subsidyUrl).post(requestBody).build();

            Response response = okHttpClient.newCall(request).execute();

            System.out.println(response.body().string());

        }


    }

    protected static class Test implements Runnable {

        @Override
        public void run() {

        }
    }

}
