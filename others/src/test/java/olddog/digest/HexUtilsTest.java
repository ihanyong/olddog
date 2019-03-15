package olddog.digest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertArrayEquals;

/**
 * HexUtilsTest
 *
 * @author yong.han
 * 2019/3/14
 */
public class HexUtilsTest {
    public static class Tuple2 {
        char[] f0;
        byte[] f1;

        public static Tuple2 of(char[] f0, byte[] f1) {
            Tuple2 tuple2 = new Tuple2();
            tuple2.f0 = f0;
            tuple2.f1 = f1;
            return tuple2;
        }
    }

    private static Tuple2[] simples = new Tuple2[]{
            Tuple2.of(new char[]{'0', '1'}, new byte[]{1})
            ,Tuple2.of(new char[]{'0', '1', '2', '3', '4', '5'}, new byte[]{1, 35, 69})
            , Tuple2.of(new char[]{'F', 'D', 'E', 'C', 'B', 'A'}, new byte[]{-3, -20, -70})
            , Tuple2.of(new char[]{'0', 'F', 'E', '8', '9', 'C'}, new byte[]{15, -24, -100})
            , Tuple2.of(new char[]{'9', 'C'}, new byte[]{-100})
            , Tuple2.of(new char[]{'0', '1', '2', '3', '4', '5','F', 'D', 'E', 'C', 'B', 'A','0', 'F', 'E', '8', '9', 'C'}, new byte[]{1, 35, 69,-3, -20, -70,15, -24, -100})
    };

    @Test
    public void testHexEncode() {
        for (Tuple2 simple : simples) {
            assertArrayEquals(simple.f0, HexUtils.hexEncode(simple.f1));
        }
    }

    @Test
    public void testHexDecode() {
        for (Tuple2 simple : simples) {
            assertArrayEquals(simple.f1, HexUtils.hexDecode(simple.f0));
        }
    }


    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @Test
    public void testHexDecode_exception() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("char 数组长度应该为偶数");
        HexUtils.hexDecode(new char[]{'a','a','a'});

    }
}
