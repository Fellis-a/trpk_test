package com.company;

import static java.lang.Math.*;

public class areaCircle {

    double a;
    double areaC;
    double area;
    double areaS;
    double b;


    public areaCircle(double a) {
        this.a = a;
    }
    public double calculate(){
        areaC  = Math.pow(a,2) * PI;
        areaS = (5/2) * Math.pow(a, 2) * sin(PI/5) ;
        return areaC-areaS;
    }
}
