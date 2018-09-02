package ru.geekbrains.java2.dz.dz1_redkin_vladimir.ObstaclePackage;

import ru.geekbrains.java2.dz.dz1_redkin_vladimir.ParticipantPackage.Team;

public class Cross implements Course{
    private int distance;

    public Cross(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean doIt(Team t) {
        t.run(distance);
        if(t.showResults()) return true;
        else return false;
    }
}
