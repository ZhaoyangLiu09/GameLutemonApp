package com.lutemon.app;

import java.util.ArrayList;

public class Home extends Storage {

    private ArrayList<Lutemon> inHome = new ArrayList<>();

    private static Home home = null;

    private Home() {
    }

    public void moveToHome(ArrayList<Lutemon> lutemons) {

        TrainingArea trainingAreaInstance = TrainingArea.getInstance();
        ArrayList<Lutemon> trainingLutemons = trainingAreaInstance.getList();

        Battlefield battlefieldInstance = Battlefield.getInstance();
        ArrayList<Lutemon> battlefieldLutemons = battlefieldInstance.getList();

        for (Lutemon lutemon : lutemons) {
            if (lutemon.getLocation() == Location.TRAINING_AREA) {
                trainingLutemons.remove(lutemon);
            }
            else if (lutemon.getLocation() == Location.BATTLEFIELD) {
                battlefieldLutemons.remove(lutemon);
            }

            if (!inHome.contains(lutemon)) {
                lutemon.setLocation(Location.HOME);
                lutemon.healToMaxHealth();
                inHome.add(lutemon);
            }
        }
    }


    public void createNewLutemon (Lutemon lutemon) {

        inHome.add(lutemon);
    }

    public static Home getInstance() {
        if (home == null) {
            home = new Home();
        }
        return home;
    }

    @Override
    public ArrayList<Lutemon> getList() {
        return inHome;
    }
}



