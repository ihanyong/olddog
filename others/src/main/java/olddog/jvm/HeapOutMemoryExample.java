package olddog.jvm;

/**
 * HeapOutMemoryExample
 *
 * @author yong.han
 * 2019/3/22
 */
public class HeapOutMemoryExample {
    private HeapOutMemoryExample next = null;
    private long[] i = new long[100000];

    public static void main(String[] args) {
        HeapOutMemoryExample head = new HeapOutMemoryExample();
        HeapOutMemoryExample current = head;
        for (int i = 0; i < Long.MAX_VALUE; i++) {
            current.next = new HeapOutMemoryExample();
            current = current.next;
        }
    }
}
