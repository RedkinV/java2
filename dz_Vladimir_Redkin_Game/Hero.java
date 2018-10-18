package ru.geekbrains.java2.game_gui;

import javax.swing.*;
import java.util.Random;

abstract class Hero {

    protected int health;
    protected String name;
    protected int damage;
    protected int addHeal;

    public Hero(int health, String name, int damage, int addHeal) {
        this.health = health;
        this.name = name;
        this.damage = damage;
        this.addHeal = addHeal;
    }

    abstract String hit(Hero hero);

    abstract String healing(Hero hero);

    String getName(){
        return this.name;
    }

    String causeDamage(int damage) {
        if(health < 0) {
            return "Герой уже мертвый!";
        } else {
            health -= damage;
        }
        return"";
    }

    public int getHealth() {
        return health;
    }

    void addHealth(int health) {
        this.health += health;
    }

    public String info() {

        return name + " " + (health < 0 ? "Герой мертвый" : health) + " " + damage;
    }
}

class Warrior extends Hero {

    public Warrior(int health, String type, int damage, int addHeal) {
        super(health, type, damage, addHeal);
    }

    @Override
    String hit(Hero hero) {
        if (hero != this) {
            if(health < 0) {
                return "Герой погиб и бить не может!";
            } else {
                hero.causeDamage(damage);
            }
            return (this.name + " нанес урон " + hero.name);
        }
        return "Герой не может сам себя бить.";
    }

    @Override
    String healing(Hero hero) {
        return ("Войны не умеют лечить!");
    }
}

class Assasin extends Hero {

    int cricitalHit;
    Random random = new Random();

    public Assasin(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
        this.cricitalHit = random.nextInt(20);
    }

    @Override
    String hit(Hero hero) {
        if (hero != this) {
            if(health < 0) {
                return ("Герой погиб и бить не может!");
            } else {
                hero.causeDamage(damage + cricitalHit);
            }
            return (this.name + " нанес урон " + hero.name);
        }
        return "Герой не может сам себя бить.";
    }

    @Override
    String healing(Hero hero) {
        return ("Убийцы не умеют лечить!");
    }
}

class Doctor extends Hero {

    public Doctor(int heal, String name, int damage, int addHeal) {
        super(heal, name, damage, addHeal);
    }

    @Override
    String hit(Hero hero) {
        return ("Доктор не может бить!");
    }

    @Override
    String healing(Hero hero) {
        hero.addHealth(addHeal);
        return "Доктор подлечил "+hero.name;
    }
}


class Game {
    public static void main(String[] args) {

        JFrame frame= new HeroFrame("Hero Game");
        frame.setSize(1300,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);;



    }
}
