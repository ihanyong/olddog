package olddog.jvm;

import java.io.*;

/**
 * MethodAreaOutMemoryExample
 *
 * @author yong.han
 * 2019/3/22
 */
public class MethodAreaOutMemoryExample {

    private static byte[] clsbytes = null;

    public static void main(String[] args) throws ClassNotFoundException, IOException {
        try (InputStream inputStream = new FileInputStream("E:\\myspace\\studyCode\\olddog\\others\\target\\classes\\olddog\\jvm\\MethodAreaOutMemoryExample.class");
             ByteArrayOutputStream bout = new ByteArrayOutputStream()) {

            byte[] bs = new byte[1024];
            int count = 0;
            while (-1 != count) {
                count = inputStream.read(bs, 0, bs.length);
                if (count > 0) {
                    bout.write(bs, 0, count);
                }
            }

            clsbytes = bout.toByteArray();


            Class<?>[] clses = new Class[99_999_999];
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                System.out.println(i);
                clses[i] = new MyClassLoader().loadClass(MethodAreaOutMemoryExample.class.getName());
            }
        };

    }


    public static class MyClassLoader extends ClassLoader {
        public MyClassLoader() {
            super(null);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
//            return super.findClass(name);
            byte[] bytes = clsbytes;
            return defineClass(name, bytes, 0, bytes.length );
        }
    }
}

