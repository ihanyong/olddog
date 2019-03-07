package olddog.cloneexmaple;

import java.util.Objects;

/**
 * Person
 *
 * @author yong.han
 * 2019/3/7
 */
public class Person implements Cloneable {
//public class Person {

    private int age;
    private String name;


    @Override
    protected Person clone() {
        try {
            Person p = (Person) super.clone();
//            Person p = new Person();
            p.setName(new String(this.name));
            p.setAge(this.age);

            return p;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    public String asString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
