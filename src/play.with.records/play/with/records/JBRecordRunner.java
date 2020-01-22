//JAVAC_OPTIONS --enable-preview -source 14 -Xlint:preview
//JAVA_OPTIONS --enable-preview
package play.with.records;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

class Person {

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class RecordRunner {

    public static void main(String[] args) {

        Person first = new Person("Albi", 25);
        Person second = new Person("Clint Eastwood", 89);
        Person youngAgain = new Person("Victoria", 36);
        Person fourth = new Person("John", 33);
        Person fifth = new Person("Tom", 53);

        List<Person> people = List.of(first, second, youngAgain, fourth, fifth);

        people.forEach(System.out::println);

        System.out.println(first.name());
        System.out.println(second.age());

        System.out.println("Is %s wise? %s".formatted(first.name(), first.isWise()));
        System.out.println("Is %s wise? %s".formatted(second.name(), second.isWise()));

        people.stream()
                .sorted(Comparator.comparingInt(person -> yearsTo100(person.age())))
                .forEach(System.out::println);

        System.out.println(counter);

        counter = 0;

        people.stream()
                .map(person -> new PersonYears(person, yearsTo100(person.age())))
                .sorted(Comparator.comparingInt(PersonYears::years))
                .map(PersonYears::person)
                .forEach(System.out::println);

        System.out.println(counter);

    }

    static int counter = 0;

    record PersonYears (Person person, int years) { }

    static int yearsTo100 (int age) {
        counter++;
        return 100 - age;
    }

    record Person (String name, int age) {
        public Person {
            if (age > 135) {
                throw new IllegalArgumentException("Sorry, age (%d) is not supported".formatted(age));
            }
        }

        boolean isWise () {
            return age > 80;
        }
    }

}
