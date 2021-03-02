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
    JLabel lblRes;
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

        JPanel grid = new JPanel(new GridLayout(3, 1, 5, 5) );

        JPanel flow1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel instructionsLeft = new JLabel(
                "Программа предназначена для отображения фигуры и рассчета площади закрашенной области");

        flow1.add(instructionsLeft);
        grid.add(flow1);

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
        lblRes =  new JLabel(" Площадь равна: " );
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(isDigit(radius.getText())){
                a = Integer.parseInt(radius.getText());
                if(a>0){
                areaCircle = new areaCircle(a);
                lblRes = new JLabel("Площадь равна: " + areaCircle.calculate());

                drawArea.setCircleSize(a); // вызываем изменение размером
            }else{
                JOptionPane.showMessageDialog(GUI.this, "Введите целое число больше нуля!");
            }}else {
                JOptionPane.showMessageDialog(GUI.this, "Введите целое число больше нуля!");
            }
            }
        });



        JPanel flow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        flow.add(lbl1);
        flow.add(radius);
        flow.add(start);
        grid.add(flow);
        grid.add(lblRes);

        //Lay out this demo.
        add(grid, BorderLayout.PAGE_START);
        add(scroller, BorderLayout.CENTER);

        panel.add(panelGraph, BorderLayout.CENTER);

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
