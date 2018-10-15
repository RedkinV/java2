package ru.geekbrains.java2.game_gui;

import javax.swing.*;
import java.util.Random;

public class Game_1 {

    private final int M=3;
    private Hero[] team1=new Hero[M];
    private Hero[] team2=new Hero[M];
    private final String NEWLINE ="\n";

    private Hero[] team1Arr = new Hero[]{   new Warrior(250, "Тигрил", 50, 0),
            new Assasin(150, "Акали", 70, 0),
            new Doctor(120, "Жанна", 0, 60)};
    private Hero[] team2Arr = new Hero[]{   new Warrior(290, "Минотавр", 60, 0),
            new Assasin(160, "Джинкс", 90, 0),
            new Doctor(110, "Зои", 0, 80)};


    public boolean addMember(Hero hero,Hero[] team){
        if(team.length!=M) throw new ArrayIndexOutOfBoundsException("размер массива героев не равен М");
        for (int i = 0; i <M ; i++) {
            if (team[i]==null) {
                team[i]=hero;
                return true;
            }
        }
        return false;
    }
    public boolean areTeamsFull(){
        for (int i = 0; i <M ; i++) {
            if (team1[i]==null) return false;
        }
        for (int i = 0; i <M ; i++) {
            if (team2[i]==null) return false;
        }
        return true;
    }

    public boolean begin(JTextArea jta){
        Random randomStep = new Random();
        Random randomHealing = new Random();
        int round = 3;

        if (team1.length==0||team2.length==0) return false;

         for (int j = 0; j < round; j++) {
            for (int i = 0; i < team1.length; i++) {
                if(randomStep.nextInt(2) == 0) {
                    if(team1[i] instanceof Doctor) {
                        jta.append(team1[i].healing(team1[randomHealing.nextInt(2)])+NEWLINE);
                    } else {
                        jta.append(team1[i].hit(team2[i])+NEWLINE);
                    }
                } else {
                    if(team2[i] instanceof Doctor) {
                        jta.append(team2[i].healing(team2[randomHealing.nextInt(2)])+NEWLINE);
                    } else {
                        jta.append(team2[i].hit(team1[i])+NEWLINE);
                    }
                }
            }
        }
        jta.append("-----------------"+NEWLINE);
        System.out.println("---------------");

        for (Hero t1: team1) {
            System.out.println(t1.info());
        }

        for (Hero t2: team2) {
            System.out.println(t2.info());
        }
        return true;
    }

    public Hero[] getTeam1() {
        return team1;
    }

    public Hero[] getTeam2() {
        return team2;
    }
    public String[] getAllTeamNames(Hero[] hArr){
        String[] str=new String[hArr.length];

        for (int i = 0; i < hArr.length; i++) {
            str[i]=hArr[i].getName();
        }
        return str;
    }

    public Hero[] getTeam1Arr() {
        return team1Arr;
    }

    public Hero[] getTeam2Arr() {
        return team2Arr;
    }
}
