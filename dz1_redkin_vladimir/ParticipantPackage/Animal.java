package ru.geekbrains.java2.dz.dz1_redkin_vladimir.ParticipantPackage;

public class Animal implements  Team{
    private String name;
    private int maxRunDist;
    private int maxJumpDist;
    private int maxSwimDist;
    private boolean onDistance;

    public Animal(String name,int maxRunDist, int maxJumpDist, int maxSwimDist) {
        this.name=name;
        this.maxRunDist = maxRunDist;
        this.maxJumpDist = maxJumpDist;
        this.maxSwimDist = maxSwimDist;
        this.onDistance = true;
    }

    @Override
    public void swim(int distance) {
        if(distance<=maxSwimDist) {
            System.out.println(name+" переплыл препятствие успешно");

        } else onDistance=false;
    }

    @Override
    public void jump(int distance) {
        if(distance<=maxJumpDist) {
            System.out.println(name+" перепрыгнул препятствие успешно");

        } else onDistance=false;
    }

    @Override
    public void run(int distance) {
        if(distance<=maxRunDist) {
            System.out.println(name+" пробежал дистанцию успешно");

        } else onDistance=false;

    }

    @Override
    public boolean showResults() {
        return onDistance;
    }

    public String getName() {
        return name;
    }
}

