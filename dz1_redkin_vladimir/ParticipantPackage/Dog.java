package ru.geekbrains.java2.dz.dz1_redkin_vladimir.ParticipantPackage;

public class Dog extends Animal{
    private String name;
    private int maxRunDist;
    private int maxJumpDist;
    private int maxSwimDist;
    private boolean onDistance;


    public Dog(String name, int maxRunDist, int maxJumpDist, int maxSwimDist) {
        super(name, maxRunDist, maxJumpDist, maxSwimDist);
    }
}
