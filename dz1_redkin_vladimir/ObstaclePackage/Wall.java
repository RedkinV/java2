package ru.geekbrains.java2.dz.dz1_redkin_vladimir.ObstaclePackage;

import ru.geekbrains.java2.dz.dz1_redkin_vladimir.ParticipantPackage.Team;

public class Wall implements Course{
    private int distance;

    public Wall(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean doIt(Team t) {
        t.jump(distance);
        if(t.showResults()) return true;
        else return false;
    }
}
