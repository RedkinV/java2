package ru.geekbrains.java2.lesson1;

public class Main {
    public static void main(String[] args) {
        Competitor[] competitors={new Dog("Bobik"), new Cat("Mursik"), new Human("Peter")};
        Obstacle[] obstacles={new Wall(3), new River(50), new Cross(400) };
        Team team=new Team("Roket",competitors,obstacles);

        team.start();
        team.results();
        team.winners();
    }
}
