package ru.geekbrains.java2.dz.dz1_redkin_vladimir.ParticipantPackage;

public class Human extends Animal {
    private String name;
    private int maxRunDist;
    private int maxJumpDist;
    private int maxSwimDist;
    private boolean onDistance;

    public Human(String name,int maxRunDist, int maxJumpDist, int maxSwimDist) {
        super(name,maxRunDist,maxJumpDist,maxSwimDist);
    }

}
