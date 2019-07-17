package olddog.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * JVMMemoryDebuger
 *
 * @author yong.han
 * 2019/7/10
 */
public class JVMMemoryDebuger {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("starting...");
        List<byte[]> list = new ArrayList<>(100000);

        for (int i = 0; i < 100000; i++) {
            byte[] bs = new byte[1024];
            if (i % 10 == 0) {
                list.add(bs);
            }
        }
        System.out.println("end!");


    }



}
