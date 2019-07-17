package olddog.basic;


import java.io.Serializable;
import java.util.HashMap;

/**
 * HashMapDebuger
 *
 * @author yong.han
 * 2019/7/8
 */
public class HashMapDebuger {


    public static void main(String[] args) {
//        HashMap<Key, String> hm = new HashMap<>();
//        for (int i = 0; i < 999999999; i++) {
//            hm.put(Key.of("key_" + i), "value_"+i);
//        }
//        System.out.println("ok");


//        testHash1();
//        testHash2();
        testByte();
    }


    public static void testByte() {
        for (byte b = 0; b < Byte.MAX_VALUE; b++) {
            System.out.println(b ^ b >> 4);
        }
    }

    /**
     * 利用了位于运算来实现了对于（2的n次方减1的）模运算
     */
    public static void testHash1() {
        int n = 16;
        for (int i = 0; i < 100; i++) {
            System.out.println((n - 1) & i);
        }
    }

    public static void testHash2() {
        for (int hashcode = Integer.MAX_VALUE-100; hashcode < Integer.MAX_VALUE; hashcode++) {
            System.out.println(hashcode ^ (hashcode >>> 16));
        }

    }

    public static class Key implements Serializable {
        private String id;

        public static Key of(String id) {
            Key k = new Key();
            k.id = id;
            return k;
        }

        @Override
        public int hashCode() {
            return 40;
        }

        @Override
        public boolean equals(Object obj) {
            return this == obj || (obj instanceof Key && this.id.equals(((Key)obj).id));
        }
    }


    static void testTableSizeFor() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("" +i+ "->" + tableSizeFor(i));
        }
        System.out.println("MAXIMUM_CAPACITY -> " + MAXIMUM_CAPACITY);
    }

    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }


}
