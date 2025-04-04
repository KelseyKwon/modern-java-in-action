package ch05;

import ch04.Dish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class StreamApplicationExample {
    public static void main(String[] args) {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));

        //=========Slicing=========//
        /**
         * Predicate를 이용한 slicing
         * Using takewhile, dropwhile
         *
         * dropwhile : ㅊㅓ음으로 거짓이 되는 지점까지 발견된 요소를 버린다.
         */
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(toList());

        List<Dish> slicedMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(toList());

        List<Dish> slicedMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(toList());

        /**
         * Stream 축소
         */
        List<Dish> dished = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(toList());

        /**
         * 요소 건너뛰기
         */
        List<Dish> dishes = specialMenu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(3)
                .collect(toList());

        // quiz 5-1
        List<Dish> getTwoMeatDish = specialMenu.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .limit(2)
                .collect(toList());

        //=========Mapping========//
        List<String> words = Arrays.asList("Moder", "Java", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());

        // DishName의 각 단어의 char 길이 얻기
        List<Integer> lengthOfDishName = specialMenu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        System.out.println(lengthOfDishName);

        // 스트림 평면화 (String Array를 char Array로 바꾸기)
        List<String> words2 = Arrays.asList("Hello", "World");
        List<String[]> uniqueChars = words2.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());
        System.out.println(uniqueChars);
    }
}
