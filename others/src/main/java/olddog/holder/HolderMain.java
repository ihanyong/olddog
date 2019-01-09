package olddog.holder;

import lombok.extern.slf4j.Slf4j;

/**
 * HolderMain
 *
 * @author yong.han
 * 2018/12/29
 */
@Slf4j
public class HolderMain {

    public static void main(String[] args) {
        testString();

    }

    public static void testString() {
        String first = "first";
        String second = "second";
        Holder<String> holder = new Holder<>(second);
        log.info("before:first:{}", first);
        log.info("before:second:{}", second);
        log.info("before:holder:{}", holder);

        changeString(first);
        changeString(holder);

        log.info("after:first:{}", first);
        log.info("after:second:{}", second);
        log.info("after:holder:{}", holder);

    }

    public static void changeString(String string) {
        string += "_changeString_string";
    }

    public static void changeString(Holder<String> holder) {
        String string = holder.getValue();
        string += "_changeString_string";

        holder.setValue(string);

    }

    public static void testObject(String[] args) {
        User user1 = new User();
        user1.setName("user1");
        user1.setAge(10);

        User user2 = new User();
        user2.setName("user2");
        user2.setAge(20);

        Holder<User> user2Holder = new Holder<>(user2);

        log.info("before change. user1 : {}", user1);
        log.info("before change. user2 : {}", user2);
        log.info("before change. user2Holder : {}", user2Holder);

        change(user1);
        change(user2Holder);



        log.info("after change. user1 : {}", user1);
        log.info("after change. user2 : {}", user2);
    }

    private static void change(User user) {
        log.info("method of change user before: ", user);
        user.setName(user.getName() + "changeUser");
        user.setAge(user.getAge() + 100);
        log.info("method of change user after: ", user);

    }

    private static void change(Holder<User> userHolder) {
        User user = userHolder.getValue();

        log.info("method of change user holder before: ", user);
        log.info("method of change user holder before: ", userHolder);
        user.setName(user.getName() + "changeUser");
        user.setAge(user.getAge() + 100);
        log.info("method of change user holder after: ", user);
        log.info("method of change user holder after: ", userHolder);
    }


    public static class User {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public static class Holder<T> {
        public Holder(T value) {
            this.value = value;
        }

        private T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Holder{" +
                    "value=" + value +
                    '}';
        }
    }
}
