package ch04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamExample {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beek", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        List<String> threeHighCaloricDishNames = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());
        System.out.println(threeHighCaloricDishNames);

        List<String> highCaloricDished = menu.stream()
                .filter(calorie -> calorie.getCalories() > 300)
                .map(Dish::getName)
                .collect(toList());
        System.out.println(highCaloricDished);

        //=======section04======//
        /**
         * filter, add, limit : 서로 연결되어 파이프라인을 형성 (=중간 연산)
         * collect : 파이프라인을 실행한 다음에 담는다 (=최종 연상)
         */
        List<String> names = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        /**
         * 1. 중간 연산 : filter or sorted와 같은 중간 연산은 다른 스트림을 반환.
         * 따라서 연달아 중간 연산을 연결해 질의를 만들 수 있다.
         * 중간 연산을 합친 다음에 합쳐진 중간 연산을 최종 연산으로 한번에 처리하기 때문이다.
         *
         * 아래를 보면 menu에는 총 9개의 인스턴스가 있는데 3개만 출력되는 것을 볼 수 있다. (마지막에 filter, map, limit을 한번에 연산)
         */
        List<String> namesOver = menu.stream()
                .filter(dish -> {
                    System.out.println("filtering:" + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping: " + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());
        System.out.println(namesOver);

        /**
         * 2. 최종연산
         * forEach는 소스의 각 요리에 람다를 적용한 다음에 void를 반환하는 최종 연산.
         *
         * 즉, 연달아 질의를 만들 수 있으면 중간 연산, 최종적으로 스트림이 아닌 어떤 형태(ex) String, long)을 반환하면 최종연산이다.
         */
        menu.stream().forEach(System.out::println);

        // Quiz 4-2



    }
}
