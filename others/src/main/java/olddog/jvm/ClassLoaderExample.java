package olddog.jvm;

/**
 * ClassLoaderExample
 *
 * @author yong.han
 * 2019/7/4
 */
public class ClassLoaderExample {

    private long val;
    private long[] vals = new long[3];


    public static void main(String[] args) {

        ClassLoaderExample example = new ClassLoaderExample();

        ClassLoader cl1 = example.getClass().getClassLoader();
        Class ar = example.vals.getClass();
        ClassLoader cl2 = example.vals.getClass().getClassLoader();


        System.out.println(cl1);
        System.out.println(cl2);
        System.out.println(ar);






    }


}
