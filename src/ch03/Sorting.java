package ch03;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class Sorting {
    public static void main(String[] args) {
        List<String> str1 = Arrays.asList("a", "b", "A", "B");
        str1.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

        List<String> str2 = Arrays.asList("a", "b", "A", "B");
        str2.sort(String::compareToIgnoreCase);


        ToIntFunction<String> stringToInt = (Integer::parseInt);

    }
}
