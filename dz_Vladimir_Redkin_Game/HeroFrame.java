package ru.geekbrains.java2.game_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HeroFrame extends JFrame {


    private final String NEWLINE ="\n";
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();

    private JLabel instructions = new JLabel("Выберите по 3 война в команду.");

    private JLabel team1Label = new JLabel("Набери команду: ");
    private JComboBox team1ComboBox;
    private JButton team1ChooseBtn = new JButton("Зачислить");
    private JTextArea team1TextArea = new JTextArea();
    private JTextArea statusT1 =new JTextArea("");

    private JLabel team2Label = new JLabel("Набери команду: ");
    private JComboBox team2ComboBox;
    private JButton team2ChooseBtn = new JButton("Зачислить");
    private JTextArea team2TextArea = new JTextArea();
    private JTextArea statusT2 =new JTextArea("");

    private JButton fightBtn = new JButton("Fight!");
    private JTextArea resultsTextArea = new JTextArea();
    private JScrollPane sp=new JScrollPane(resultsTextArea);

    Game_1 game1 =new Game_1();



    public HeroFrame(String nameFrame) {

        panel1.add(team1Label);
        team1ComboBox = new JComboBox(game1.getAllTeamNames(game1.getTeam1Arr()));
        panel1.add(team1ComboBox);
        ChooseButtonListener1 cbl1 = new ChooseButtonListener1();
        team1ChooseBtn.addActionListener(cbl1);
        panel1.add(team1ChooseBtn);
        team1TextArea.setPreferredSize(new Dimension(200,60));
        team1TextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel1.add(team1TextArea);
        panel1.add(statusT1);
        panel1.setPreferredSize(new Dimension(400,50));
        panel1.setBorder(BorderFactory.createTitledBorder("Team1"));

        panel2.setPreferredSize(new Dimension(400,50));
        panel2.add(team2Label);
        team2ComboBox = new JComboBox(game1.getAllTeamNames(game1.getTeam2Arr()));
        panel2.add(team2ComboBox);
        ChooseButtonListener2 cbl2 = new ChooseButtonListener2();
        team2ChooseBtn.addActionListener(cbl2);
        panel2.add(team2ChooseBtn);
        team2TextArea.setPreferredSize(new Dimension(200,60));
        team2TextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel2.add(team2TextArea);
        panel2.add(statusT2);
        panel2.setBorder(BorderFactory.createTitledBorder("Team2"));

        FightButtonListener fbl= new FightButtonListener();
        fightBtn.addActionListener(fbl);
        panel3.add(fightBtn);
        sp.setPreferredSize(new Dimension(250,150));
        panel3.add(sp);
        panel3.setBorder(BorderFactory.createTitledBorder("Results"));


        this.setTitle(nameFrame);
        this.add(panel1, BorderLayout.WEST);
        this.add(panel2, BorderLayout.EAST);
        this.add(panel3, BorderLayout.SOUTH);

    }



    class ChooseButtonListener1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            statusT1.setText(Integer.toString(team1ComboBox.getSelectedIndex()));
            Hero teamMember=game1.getTeam1Arr()[team1ComboBox.getSelectedIndex()];
            if (game1.addMember(teamMember, game1.getTeam1())) {
                team1TextArea.append((String) team1ComboBox.getSelectedItem()+ NEWLINE);
                statusT1.setText("ok");
            } else statusT1.setText("команда заполнилась. мест нет");

        }
    }
    class ChooseButtonListener2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            statusT2.setText(Integer.toString(team2ComboBox.getSelectedIndex()));
            Hero teamMember=game1.getTeam2Arr()[team2ComboBox.getSelectedIndex()];
            if (game1.addMember(teamMember, game1.getTeam2())) {
                team2TextArea.append((String) team2ComboBox.getSelectedItem()+ NEWLINE);
                statusT2.setText("ok");
            } else statusT2.setText("команда заполнилась. мест нет");

        }
    }

    class FightButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!game1.areTeamsFull()) {
                resultsTextArea.setText("Сперва заполни команды!");
            } else {
                resultsTextArea.setText("Поехали!"+NEWLINE+NEWLINE);
                game1.begin(resultsTextArea);
                for (Hero t1: game1.getTeam1()) {
                   resultsTextArea.append(t1.info()+NEWLINE);

                }

                for (Hero t2: game1.getTeam2()) {
                    resultsTextArea.append(t2.info()+NEWLINE);
                }

            }

        }
    }

    public JTextArea getResultsTextArea() {
        return resultsTextArea;
    }
}
