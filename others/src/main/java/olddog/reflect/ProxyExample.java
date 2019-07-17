package olddog.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * ProxyExample
 *
 * @author yong.han
 * 2019/7/17
 */
public class ProxyExample {


    public static void main(String[] args) {


        Person h = new Chinese("hany", 28, "han");

        Person proxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[] {Person.class}, new PersonHandler(h));

        System.out.println(proxy.getAge());
//        System.out.println(proxy.getMinzu());

    }

    public static class PersonHandler implements InvocationHandler {
        private Object target;

        public PersonHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            System.out.println(method.getName());
            return method.invoke(proxy, args);
        }

    }


    public interface Person {
        String getName();
        int getAge();
    }

    public static class Chinese implements Person {

        public Chinese(String name, int age, String minzu) {
            this.name = name;
            this.age = age;
            this.minzu = minzu;
        }

        private String name;
        private int age;
        private String minzu;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getMinzu() {
            return minzu;
        }

        public void setMinzu(String minzu) {
            this.minzu = minzu;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getAge() {
            return age;
        }
    }


}
