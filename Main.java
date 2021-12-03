package com.company;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

enum wheels{
    UNCYCLE,
    TWO_WHEELED,
    THREE_WHEEL
}

class Workshop{
    static void repair(Bicycles bicycles){
        wheels typeOfBicycle = bicycles.getTypeOfBicycle();
        String repair;
        switch (typeOfBicycle){
            case UNCYCLE:
                repair = "Ремонт одноколёсного велосипеда!";
                break;
            case TWO_WHEELED:
                repair = "Ремонт двухколёсного велосипеда!";
                break;
            case THREE_WHEEL:
                repair = "Ремонт трёхколёсного велосипеда!";
                break;
            default:
                repair = "Мы не можем чинить этот тип велосипеда";
        }
        System.out.println(repair);
    }
}

class Service{
    protected String buying = "Вы купили велосипед!";
}

class Bicycles extends Service{
    private wheels typeOfBicycle;
    private float wheelDiameter;
    private String repair;

    private static ArrayList<Bicycles> bicycles = new ArrayList<>();

    public Bicycles(wheels typeOfBicycle, float wheelDiameter) {
        this.typeOfBicycle = typeOfBicycle;
        this.wheelDiameter = wheelDiameter;
        bicycles.add(this);
    }

    public wheels getTypeOfBicycle() {
        return typeOfBicycle;
    }

    public float getWheelDiameter() {
        return wheelDiameter;
    }

    private static ArrayList<Bicycles> getAllBicycles() {
        return bicycles;
    }

    public void buyBicycle(){
        System.out.println(super.buying);
    }

    public static void sort(){
        float diameters[] = new float[getAllBicycles().size()];

        for(int i = 0; i < getAllBicycles().size(); i++){
            diameters[i] = getAllBicycles().get(i).wheelDiameter;
        }

        for(int i = 0; i < diameters.length; i++){
            for(int j = 0; j < diameters.length - 1; j++){
                if(diameters[j] > diameters[j + 1]){
                    float tmp = diameters[j];
                    diameters[j] = diameters[j + 1];
                    diameters[j + 1] = tmp;
                }
            }
        }

        for(int i = 0; i < diameters.length; i++){
            System.out.println(i + 1 + " велосипед имеет диаметор: " + diameters[i]);
        }
    }

    public static void repairUncycle(){
        int counter = 0;
        ArrayList<Bicycles> bicycles = getAllBicycles();
        for(int i = 0; i < bicycles.size(); i++){
            if(bicycles.get(i).typeOfBicycle == wheels.UNCYCLE) counter++;
        }
        System.out.println("Отремантировано " + counter + " одноколёсных велосипедов.");
    }
}

public class Main {
    public static void main(String[] args) {
        Bicycles bike_1 = new Bicycles(wheels.UNCYCLE, 10f);
        Bicycles bike_2 = new Bicycles(wheels.TWO_WHEELED, 12.4f);
        Bicycles bike_3 = new Bicycles(wheels.THREE_WHEEL, 16.5f);
        Bicycles.sort();
        Bicycles.repairUncycle();
        Workshop.repair(bike_1);
        Workshop.repair(bike_2);
        Workshop.repair(bike_3);
    }
}