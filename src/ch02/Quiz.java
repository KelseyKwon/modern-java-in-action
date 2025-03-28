package ch02;

import java.util.Arrays;
import java.util.List;

public class Quiz {
    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(
                new Apple(80, Color.GREEN),
                new Apple(155, Color.GREEN),
                new Apple(120, Color.RED)
        );

        prettyPrintApple(inventory, new printEachApplesWeight());
        prettyPrintApple(inventory, new printHeavyApplesWeight());
    }
    public static void prettyPrintApple(List<Apple> inventory, ApplesPredicate predicate) {
        for (Apple apple : inventory) {
            String output = predicate.printApples(apple);
            System.out.println(output);
        }
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

    interface ApplesPredicate {
        String printApples(Apple apple);
    }

    static class printEachApplesWeight implements ApplesPredicate {

        @Override
        public String printApples(Apple apple) {
//            return String.valueOf(apple.weight);
            return "An apple of " + apple.getWeight() + "g";
        }
    }

    static class printHeavyApplesWeight implements ApplesPredicate {
        @Override
        public String printApples(Apple apple) {
//            if (apple.weight > 150) {
//                return "무거워";
//            }
//            return "가벼워";
            String characteristic = apple.getWeight() > 150 ? "heavy" : "light";
            return "A " + characteristic + " " + apple.getColor() + " apple";
        }
    }

}
