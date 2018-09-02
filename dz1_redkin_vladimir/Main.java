package ru.geekbrains.java2.dz.dz1_redkin_vladimir;

import ru.geekbrains.java2.dz.dz1_redkin_vladimir.ObstaclePackage.Course;
import ru.geekbrains.java2.dz.dz1_redkin_vladimir.ObstaclePackage.Cross;
import ru.geekbrains.java2.dz.dz1_redkin_vladimir.ObstaclePackage.Wall;
import ru.geekbrains.java2.dz.dz1_redkin_vladimir.ObstaclePackage.Water;
import ru.geekbrains.java2.dz.dz1_redkin_vladimir.ParticipantPackage.*;

public class Main {

    public static void main(String[] args) {
        Animal[] animal={new Cat("Cat",100,10,0 ),
                    new Dog("Dog", 200,2,5),
                    new Human("Human", 300,3,10),
                    new Human("Human2", 250,2,11)};
        Course[] course={new Cross(150), new Wall(3), new Water(5)};

        for (Animal a:animal) {
            for (Course c : course) {
                if (!c.doIt(a)) break;
            }
        }

        for (Animal a:animal) {
            System.out.println(a.getName()+" on distance - "+a.showResults());


        }

    }
}
