package ch03;

import ch03.sec06.Fruit;

public class Apple implements Fruit {

    private int weight = 0;
    private Color color;
    private String country;

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Apple() {

    }

    public Apple(int weight) {
        this.weight = weight;
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

    public String getCountry() {
        return country;
    }

    @SuppressWarnings("boxing")
    @Override
    public String toString() {
        return String.format("Apple{color=%s, weight=%d}", color, weight);
    }


}
