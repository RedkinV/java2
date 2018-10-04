package ru.geekbrains.java2.lesson1;

public interface Competitor {
    void run(int dist);
    void jump(int dist);
    void swim(int dist);
    boolean isOnDistance();
    void info();
}
