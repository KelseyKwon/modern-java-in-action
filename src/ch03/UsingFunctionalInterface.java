package ch03;

import java.util.ArrayList;
import java.util.List;

public class UsingFunctionalInterface {
    @FunctionalInterface
    public interface Predicate<T> {
        boolean test(T t);
    }

    public <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
}
