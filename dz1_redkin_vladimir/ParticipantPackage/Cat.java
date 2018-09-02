package ru.geekbrains.java2.dz.dz1_redkin_vladimir.ParticipantPackage;

public class Cat extends Animal{
    private String name;
    private int maxRunDist;
    private int maxJumpDist;
    private int maxSwimDist;
    private boolean onDistance;

    public Cat(String name, int maxRunDist, int maxJumpDist, int maxSwimDist) {
        super(name, maxRunDist, maxJumpDist, maxSwimDist);
    }


    @Override
    public void swim(int distance) {
             System.out.println(name+" не умеет плавать");
             onDistance=false;
    }


}
