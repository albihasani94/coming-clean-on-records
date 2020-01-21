package play.with.records;

import java.util.List;

public class PersonRunner {

    public static void main(String[] args) {

        Person first = new Person("Albi", 25);
        Person second = new Person("Clint Eastwood", 89);

        List.of(first, second).forEach(System.out::println);

    }

}
