package olddog.reflect;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * ReflectionUtils
 *
 * @author yong.han
 * 2018/12/18
 */
public class ReflectionUtils {

    public static void main(String[] args) {
        Address ad = new Address();
        ad.setCode(300016);
        ad.setName("ad name");

        Person p = new Person();
        p.setName("person name");
        p.setAge((short) 28);
        p.setAddress(ad);


        System.out.println(p);
        System.out.println(ad);

        Address ad2 = getFeild(p, "address");
        System.out.println(ad2);
    }


    public static <T> T getFeild(Object target, String name) {
        Field field = org.springframework.util.ReflectionUtils.findField(target.getClass(), name);
        org.springframework.util.ReflectionUtils.makeAccessible(field);
        return (T) org.springframework.util.ReflectionUtils.getField(field, target);
    }


    @Data
    public static class Person {
        private String name;
        private short age;
        private Address address;
    }

    @Data
    public static class Address {
        private String name;
        private long code;
    }
}

