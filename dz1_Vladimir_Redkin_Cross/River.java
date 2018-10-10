package ru.geekbrains.java2.lesson1;

public class River extends Obstacle {
    private int distance;

    public River(int distance) {
        this.distance = distance;
    }

    @Override
    public void doIt(Competitor competitor) {
        competitor.swim(distance);

    }
}
