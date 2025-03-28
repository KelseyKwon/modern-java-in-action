package ch02;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringApples {

    public static void main(String... args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        List<Apple> greenApples = filterApplesByColor(inventory, Color.GREEN);
        System.out.println(greenApples);

        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);
        System.out.println(redApples);

        List<Apple> redApple1 = filterApples(inventory, new AppleGreenColorPredicate());
        System.out.println(redApple1);

        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApples);

        /**
         * PROB : 익명 클래스는 여전히 많은 공간을 차지함.
         * PROB : 많은 프로그래머가 익명 클래스의 사용에 익숙하지 않다.
         * SOL : 람다 표현식!
         */
        List<Apple> redApples3 = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return Color.RED.equals(apple.getColor());
            }
        });

        List<Apple> result = filterApples(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));

        List<Apple> redApples5 = filter(inventory, (Apple apple) -> Color.RED.equals(apple.getColor()));

        inventory.sort(new Comparator<Apple>() {
            public int compare(Apple a1, Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });
    }

    /**
     * 1단계 : 특정 색상에만 필터링 되는 반복문 만들기.
     * PROB : 농부가 좀 더 다양한 색으로 필터링하는 등의 변화에는 적절하게 대응할 수 없음
     * SOL : filterRedApples, filterGreenApples는 비슷한 작동을 하므로 코드를 추상화해서 작성한다!
     */
    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result =  new ArrayList<>(); // 사과 누적 리스트
        for (Apple apple : inventory) {
            if (apple.getColor() == Color.GREEN) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     * 2단계 : 색을 파라미터와 하도록 메서드에 파라미터를 추가한다. (요구사항에 더 유연하게 대응 가능)
     * 하지만 여기서 더 생각해보아야 하는 것 : 색 이외에도 무게로 구분하고 싶어.
     * 그러면 filterApplesByWeight로 작성할 수 있지만, filtering하는 과정이 중복된다.
     *
     * PROB : 중복되는 것을 하나로 합치기 위해서 flag등을 이용해 filterApples의 함수를 작성하면 무엇을 의미하는지... 불명확해짐.
     * SOL : 따라서 선택 조건을 결정하는 인터페이스를 정의하면 됨!
     */

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (apple.getColor() == color) {
                result.add(apple);
            }
        }
        return result;
    }

    /**
     *  3단계 : 선택 조건을 결정하는 인터페이스로 캡슐화하기. (추상적 조건으로 필터링!)
     *
     *  PROB : 이렇게 여러 클래스를 구현해서 인스턴스화하는 과정이 조금으 거추장 스러울 수 있음...
     *  SOL : 클래스의 선언과 인스턴스화를 동시에 하는 익명 클래스 사용 (하지만 이게 다 해결하는 것은 아니기 때문에 람다 표현식을 구현)
     */
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }


    enum Color {
        RED,
        GREEN
    }

    public static class Apple {
        private int weight = 0;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        @SuppressWarnings("boxing")
        @Override
        public String toString() {
            return String.format("Apple{color=%s, weight=%d}", color, weight);
        }
    }

    public interface ApplePredicate {
        boolean test(Apple apple);
    }

    static class AppleHeavyWeightPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    static class AppleGreenColorPredicate implements ApplePredicate {

        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.GREEN;
        }
    }

    static class AppleRedAndHeavyPredicate implements ApplePredicate{

        @Override
        public boolean test(Apple apple) {
            return apple.getColor() == Color.RED && apple.getWeight() > 150;
        }
    }

    public interface Predicate<T> {
        boolean test(T t);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public interface Comparator<T> {
        int compare(T o1, T o2);
    }
}