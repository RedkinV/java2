package ru.geekbrains.java2.lesson1;

public class Human implements Competitor {
    private String name;
    private int maxSwimDist;
    private int maxRunDist;
    private int maxJumpHeight;
    private boolean onDistance;

    public Human(String name) {
        this.name = name;
        this.maxSwimDist = 100;
        this.maxRunDist = 500;
        this.maxJumpHeight = 3;
        this.onDistance=true;
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void info(){
        System.out.println(name+" OnDistance status: "+onDistance);
    }

    @Override
    public void run(int dist) {
        if (dist<=maxRunDist) System.out.println(name +" пробежал дистанцию");
        else {
            System.out.println(name +" НЕ пробежал дистанцию");
            onDistance=false;
        }
    }

    @Override
    public void jump(int dist) {
        if (dist<=maxJumpHeight) System.out.println(name +" перепрыгнул стену");
        else {
            System.out.println(name +" НЕ перепрыгнул стену");
            onDistance=false;
        }
    }

    @Override
    public void swim(int dist) {
        if (dist<=maxSwimDist) System.out.println(name +" проплыл дистанцию");
        else {
            System.out.println(name +" НЕ проплыл дистанцию");
            onDistance=false;
        }

    }
}
