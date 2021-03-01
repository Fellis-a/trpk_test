package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI extends JFrame {
    JPanel panel;
    JPanel panelButton;
    JPanel panelTask;
    JPanel panelGraph;
    JLabel lbl1;
    JLabel lblMain;
    JTextField radius;
    JButton start;
    private Dimension area;
    areaCircle areaCircle;
    int a;

    private void gui_setting() {
        //основная панель
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel instructionsLeft = new JLabel(
                "Программа предназначена для отображения фигуры и рассчета площади закрашенной области");
        JLabel instructionsRight = new JLabel(
                "                        ");
        JPanel instructionPanel = new JPanel(new GridLayout(0,1));
        instructionPanel.setFocusable(true);
        instructionPanel.add(instructionsLeft);
        instructionPanel.add(instructionsRight);

        //панель параметров
        //panelButton = new JPanel();
        //panelButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //панель с графиком
        panelGraph = new JPanel();
        panelGraph.setLayout(new GridLayout(1,1));
        DrawArea drawArea = new DrawArea();



        //Put the drawing area in a scroll pane.
        JScrollPane scroller = new JScrollPane(drawArea);
        scroller.setPreferredSize(new Dimension(200,200));

        //Lay out this demo.
        //add(instructionPanel, BorderLayout.PAGE_START);
        //add(scroller, BorderLayout.CENTER);

        //panelTask = new JPanel();
        //panelTask.setLayout(new FlowLayout(FlowLayout.CENTER));
        //lblMain = new JLabel("Программа предназначена для отображения фигуры и рассчета площади закрашенной области");


        panelGraph.add(drawArea);


        lbl1 = new JLabel("Введите радиус окружности");
            radius = new JTextField("", 5);



        start = new JButton("Рассчитать!");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(isDigit(radius.getText())){
                a = Integer.parseInt(radius.getText());
                if(a>0){
                areaCircle = new areaCircle(a);
                JOptionPane.showMessageDialog(GUI.this,
                        "Площадь равна: " + areaCircle.calculate());
                drawArea.setCircleSize(a); // вызываем изменение размером
            }else{
                JOptionPane.showMessageDialog(GUI.this, "Введите целое число больше нуля!");
            }}else {
                JOptionPane.showMessageDialog(GUI.this, "Введите целое число больше нуля!");
            }
            }
        });

        instructionPanel.add(start);

        //Lay out this demo.
        add(instructionPanel, BorderLayout.PAGE_START);
        add(scroller, BorderLayout.CENTER);

        //panelTask.add(lblMain);
        instructionPanel.add(lbl1);
        instructionPanel.add(radius);
        instructionPanel.add(start);

        //panel.add(panelTask, BorderLayout.PAGE_START);
        panel.add(panelGraph, BorderLayout.CENTER);
        //panel.add(panelButton, BorderLayout.PAGE_END);

        getContentPane().add(panel);
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public GUI() throws HeadlessException {
        super("Форма");
        this.gui_setting();
        this.setResizable(true);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
