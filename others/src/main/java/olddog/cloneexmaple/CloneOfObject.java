package olddog.cloneexmaple;

/**
 * CloneOfObject
 *
 * @author yong.han
 * 2019/3/7
 */
public class CloneOfObject {


    public static void main(String[] args) {
        int age = 10;


        Person person = new Person();
        person.setAge(10);
        person.setName("HanYong'sDog");


        Person p2 = (Person) person.clone();

        isEqByRef(person, p2, "person and p2");
        isEqByLogic(person, p2, "person and p2");
        isEqByRef(person.getName(), p2.getName(), "name of person and p2");
        isEqByLogic(person.getName(), p2.getName(), "name of person and p2");


    }

    public static void isEqByRef(Object obj, Object another, String name) {

        if (obj == another) {
            System.out.println(name + " ===> ref eq");
        } else {
            System.out.println(name + " ===> ref NOT eq");
        }
    }
    public static void isEqByLogic(Object obj, Object another, String name) {

        if (obj.equals(another)) {
            System.out.println(name + " ===> logic eq");
        } else {
            System.out.println(name + " ===> logic NOT eq");
        }
    }
}
