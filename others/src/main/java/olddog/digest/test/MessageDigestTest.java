package olddog.digest.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestTest {
    public static void main(String[] args) {
        int count = 1_000_000;

//        test("MD5", count);
//        test("SHA-1", count);
//        test("SHA-256", count);

        String str1 = "{\"amount\":{\"qty\":10,\"unitUkid\":52129400336647002},\"condition\":{\"dispositionUkid\":-100,\"itemUkid\":22380400000001074,\"locationType\":\"\",\"locationUkid\":-110,\"mapUkid\":1,\"ownerUkid\":-100,\"proxyOrganizer\":-100,\"proxyUkid\":-100,\"qualityUkid\":1,\"useUkid\":-100},\"requestId\":\"201808300001\",\"userId\":20}";

        System.out.println(sha1(str1));

    }

    public static String sha1(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            return toHex(md.digest(string.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer(bytes.length * 2);

        for (int j = 0; j < bytes.length; j++) {
            sb.append(Hex[((bytes[j] & 240) >> 4)]).append(Hex[bytes[j] & 15]);
        }
        return sb.toString();
    }

    private static char[] Hex = "0123456789abcdef".toCharArray();

    public static void test(String algorithm, int count) {
        try {
            long time = System.currentTimeMillis();
            for (int i = 0; i <count; i++) {
                // new in loop for test
                MessageDigest md = MessageDigest.getInstance(algorithm);
                md.digest(("test"+1).getBytes());
            }
            System.out.println(String.format("running %s times %s, and cost time is %s",count, algorithm, (System.currentTimeMillis() - time)));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
