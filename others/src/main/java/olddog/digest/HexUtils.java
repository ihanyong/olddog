package olddog.digest;

/**
 * HexUtils
 *
 * @author yong.han
 * 2019/3/14
 */
public class HexUtils {

    private static final char[] DIGITS = "0123456789ABCDEF".toCharArray();

    public static char[] hexEncode(byte[] bts) {
        char[] cs = new char[bts.length << 1];

        int j = 0;
        for (int i = 0; i < bts.length; i++) {
            cs[j++] = DIGITS[(0xF0 & bts[i]) >>> 4];
            cs[j++] = DIGITS[(0x0F & bts[i])];
        }
        return cs;
    }

    public static byte[] hexDecode(char[] cs) {
        if (cs.length % 2 == 1) {
            throw new IllegalArgumentException("char 数组长度应该为偶数");
        }

        byte[] result = new byte[cs.length >> 1];

        int j = 0;
        for (int i = 0; i < result.length; i++) {
            int t = Character.digit(cs[j++], 16) << 4;
            t = t | (Character.digit(cs[j++], 16));
            result[i] = (byte) (t & 0xFF);
        }

        return result;
    }

}
