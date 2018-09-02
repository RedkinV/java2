package ru.geekbrains.java2.dz.dz1_redkin_vladimir.ObstaclePackage;

import ru.geekbrains.java2.dz.dz1_redkin_vladimir.ParticipantPackage.Team;

public class Water implements Course{
    private int distance;

    public Water(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean doIt(Team t) {
        t.swim(distance);
        if(t.showResults()) return true;
        else return false;
    }
}
