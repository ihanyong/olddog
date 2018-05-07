package olddog;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/1.
 */
public class WxpaySignMain {

    static  Logger logger = LoggerFactory.getLogger(WxpaySignMain.class);
    public static void main(String[] args) throws Exception {
////        WXPay wxPay = getWxpay();
//
//        String xml = notifyXML();
//        System.out.println(xml);


//        checkSign();



        refundTest("4000322001201709080939468194", "wr201709081744535240003284267785", "102", "102");


    }

    public static void refundTest(String transaction_id, String out_refund_no, String total_fee, String refund_fee) throws Exception {
        Map<String, String> refundMap = new HashMap<>();

        refundMap.put("transaction_id", transaction_id);
        refundMap.put("out_refund_no",out_refund_no);

        refundMap.put("total_fee", total_fee);
        refundMap.put("refund_fee", refund_fee);

        refundTest(refundMap);
    }

    public static void refundTest(Map<String, String> refundMap) throws Exception {
        logger.info("refund request map {}", refundMap);
        WXPay wxPay = getWxpay();
        Map<String, String> result = wxPay.refund(refundMap);
        logger.info("refund response map {}", result);
    }



    protected static void checkSign() throws Exception {
        Map<String, String> map = new HashMap<>();

        map.put("appId", "wx3152b532f0e8406b");
        map.put("nonceStr", "3683819c08d54fb5a1c7f83a1f0ddcdb");
        map.put("package", "prepay_id=wx20170908114504641afe75430474537381");
        map.put("timeStamp", "1504842305");
        map.put("signType", WXPayConstants.MD5);

        String sign = WXPayUtil.generateSignature(map, "5asdf30921asd33ADGf21gsfdtru564h");

        System.out.println(sign);

    }



    protected static String notifyXML() throws Exception {


        String appid= "wx621b7a60d53a02ea";
        String mch_id= "1447200602";
        String openid= "oQOWcxIv0NEweMlcqNLgLBGfohIg";
        String out_trade_no= "wt201709091440457210007980217540";
        String time_end= "20170909140000";
        String total_fee= "1000";
        String transaction_id= "1004400740201409030005092195";

        Map<String, String> map = genNotifyMap(appid, mch_id, openid, out_trade_no, time_end, total_fee, transaction_id);

        String xml = WXPayUtil.generateSignedXml(map, "0950e02954ofdgk342457POrwersdf43");
        return xml;
    }


    protected static Map<String, String> genNotifyMap(String appId, String mch_id, String openid, String out_trade_no, String time_end,  String total_fee, String transaction_id) {
        Map<String, String> map = new HashMap<>();

        map.put("appid", appId);
        map.put("mch_id", mch_id);
        map.put("openid", openid);
        map.put("out_trade_no", out_trade_no);
        map.put("time_end", time_end);
        map.put("total_fee", total_fee);
        map.put("transaction_id", transaction_id);

        map.put("bank_type", "CFT");
        map.put("fee_type", "CNY");
        map.put("is_subscribe", "Y");
        map.put("nonce_str", WXPayUtil.generateNonceStr());

        map.put("result_code", "SUCCESS");
        map.put("return_code", "SUCCESS");


        map.put("trade_type", "JSAPI");


        return map;
    }

    protected static WXPay getWxpay() throws IOException {

        return new WXPay(new TWXPayConfig());

    }

    public static class TWXPayConfig implements WXPayConfig {

        private String appId="wx3152b532f0e8406b";
        private String mchId="1488431572";
        private String key="5asdf30921asd33ADGf21gsfdtru564h";
        private int httpConnectTimeOutMs=2000;
        private int httpReadTimeOutMs=2000;
        private String notifyUrl="http://sjyxtest.yiqiguang.com/tbbpayqa/pay/notify/recieve/wxjsapipay";

        private String certPath="E:\\tbb_cert_test\\apiclient_cert.p12";

        private byte[] certData;

        protected  TWXPayConfig() throws IOException {
            File file = new File(certPath);
            try (InputStream certStream = new FileInputStream(file)) {
                this.certData = new byte[(int) file.length()];
                certStream.read(this.certData);
            } catch (IOException e) {
//                    logger.error("can not read cert file : {}", this.certPath, e);
                throw e;
            }
        }



        @Override
        public String getAppID() {
            return this.appId;
        }

        @Override
        public String getMchID() {
            return this.mchId;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public InputStream getCertStream() {
            ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
            return certBis;
        }

        @Override
        public int getHttpConnectTimeoutMs() {
            return this.httpConnectTimeOutMs;
        }

        @Override
        public int getHttpReadTimeoutMs() {
            return this.httpReadTimeOutMs;
        }

        //            @Override
        public String getNotifyUrl() {
            return notifyUrl;
        }
    }


}
