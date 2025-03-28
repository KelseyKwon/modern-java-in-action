package ch03.sec07;

import ch03.Apple;
import ch03.Color;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Sorting {
    public static void main(String... args) {
        // 1
        List<Apple> inventory = new ArrayList<>();
        inventory.addAll(Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        ));

        // 1단계 : 코드 전달. 객체 안에 동작을 포함시키는 방식으로.
        inventory.sort(new AppleComparator());

        //2단계 : 익명 클래스 사용
        inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        });

        //3단계 : 람다 표현식.
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
        Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
        inventory.sort(Comparator.comparing(apple -> apple.getWeight()));

        //4 단계 : 메서드 참조 사용.
        inventory.sort(Comparator.comparing(Apple::getWeight));

        //=======================sec08=======================//
        /**
         * 1. Comparator 조합.
         */
        //역정렬 -> 여기서의 Prob : 무게가 같은 두 사과가 존재한다면?
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());

        // Sol : 두번째 Comparator을 만들기. (thenComparing)
        inventory.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getCountry));

        /**
         * 2. Predicate 조합
         */
        Predicate<Apple> redApple = apple -> "red".equals(apple.getColor());
        Predicate<Apple> notRedApple = redApple.negate();
        Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
        Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(apple -> apple.getWeight() > 150)
                .or(apple -> Color.GREEN.equals(apple.getColor()));

        /**
         * 3. Function 조합
         */
        // 1. andThen (f 하고 나서 g, 즉 g(f(x))
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g);
        int result = h.apply(1);

        // 2. compose (g하고 f, 즉 f(g(x))
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> g1 = x -> x * 2;
        Function<Integer, Integer> h1 = f1.compose(g1);
        int result1 = h1.apply(1);
    }

    public static class AppleComparator implements Comparator<Apple> {
        // 1단계 : 코드 전달. 객체 안에 동작을 포함시키는 방식으로.
        @Override
        public int compare(Apple o1, Apple o2) {
            return o1.getWeight() - o2.getWeight();
        }
    }

}
