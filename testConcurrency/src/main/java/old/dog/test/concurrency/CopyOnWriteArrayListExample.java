package old.dog.test.concurrency;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayListExample
 *
 * @author yong.han
 * 2019/6/5
 */
public class CopyOnWriteArrayListExample {



    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>(new Integer[]{2,3,4,5});
        System.out.println(list.lastIndexOf(4));

    }




}
