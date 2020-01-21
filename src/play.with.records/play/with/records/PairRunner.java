package play.with.records;

import javafx.util.Pair;

import java.util.List;

public class PairRunner {

    public static void main(String[] args) {

        Pair<String, Integer> first = new Pair<>("Albi", 25);
        Pair<String, Integer> second = new Pair<>("Clint Eastwood", 89);

        List.of(first, second).forEach(System.out::println);

        System.out.println(first.getKey());
        System.out.println(second.getValue());

    }

}
