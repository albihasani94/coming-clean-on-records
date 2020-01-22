//JAVAC_OPTIONS --enable-preview -source 14 -Xlint:preview
//JAVA_OPTIONS --enable-preview
package play.with.records;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class JBRecordRunner {

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
