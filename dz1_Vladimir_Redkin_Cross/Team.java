package ru.geekbrains.java2.lesson1;

public class Team {
    private Competitor[] competitors;
    private Obstacle[] obstacles;
    private String teamName;

    public Team(String teamName,Competitor[] competitors, Obstacle[] obstacles) {
        this.teamName=teamName;
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

        System.out.println();
        System.out.println("Taem "+teamName+" results:");
        for (Competitor c: competitors) {
            c.info();
        }
    }
    public void winners(){
        System.out.println();
        System.out.println("Taem "+teamName+" winners:");
        for (Competitor c: competitors) {
            if (c.isOnDistance()) System.out.println(c.getName());
        }
    }
}
