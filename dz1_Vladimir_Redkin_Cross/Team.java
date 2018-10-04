package ru.geekbrains.java2.lesson1;

public class Team {
    private Competitor[] competitors;
    private Obstacle[] obstacles;

    public Team(Competitor[] competitors, Obstacle[] obstacles) {
        this.competitors = competitors;
        this.obstacles = obstacles;
    }

    public void start(){
        for (Competitor c: competitors) {
            for (Obstacle o: obstacles) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
        }
    }

    public void results(){
        for (Competitor c: competitors) {
            c.info();
        }
    }
}
