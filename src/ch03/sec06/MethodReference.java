package ch03.sec06;

import ch03.Apple;
import ch03.Color;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodReference {
    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static Fruit giveMeFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase())
                .apply(weight);
    }

    public static void main(String[] args) {
        // Apple(Integer weight)을 갖는 생성자로 형변환하여 list에 저장하지
        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new);

        // Apple(String color, Integer weight) 처럼 두 인수를 가진 생성자는 BiFunction 인터페이스로
        BiFunction<Color, Integer, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(Color.valueOf("GREEN"), 110);


    }

    private static List<Apple> map(List<Integer> list, Function<Integer, Apple> function) {
        List<Apple> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(function.apply(i));
        }
        return result;
    }
}
