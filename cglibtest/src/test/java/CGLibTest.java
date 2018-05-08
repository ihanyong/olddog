/**
 * Created by hanyong on 2018/3/5.
 */

import com.olddog.cglibtest.SampleClass;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author hanyong
 * @Date 2018/3/5
 */
public class CGLibTest {

    @Test
    public void testFiexedValue() {
        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback((FixedValue) () -> "hello cglib");

        SampleClass sp = (SampleClass) enhancer.create();
        assertEquals("hello cglib", sp.test(""));
        assertEquals("hello cglib", sp.toString());
    }



}
