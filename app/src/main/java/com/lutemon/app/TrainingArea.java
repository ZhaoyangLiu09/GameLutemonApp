package com.lutemon.app;

import java.util.ArrayList;

public class TrainingArea extends Storage {

    private ArrayList<Lutemon> inTrainingArea = new ArrayList<>();

    private static TrainingArea trainingArea = null;

    private TrainingArea() {
    }

    public void moveToTrainingArea(ArrayList<Lutemon> lutemons) {
        Home homeInstance = Home.getInstance();
        ArrayList<Lutemon> homeLutemons = homeInstance.getList();

        Battlefield battlefieldInstance = Battlefield.getInstance();
        ArrayList<Lutemon> battlefieldLutemons = battlefieldInstance.getList();


        for (Lutemon lutemon : lutemons) {
            if (lutemon.getLocation() == Location.HOME) {
                homeLutemons.remove(lutemon);
            }
            else if (lutemon.getLocation() == Location.BATTLEFIELD) {
                battlefieldLutemons.remove(lutemon);
            }

            if (!inTrainingArea.contains(lutemon)) {
                lutemon.setLocation(Location.TRAINING_AREA);
                lutemon.addExperience();
                inTrainingArea.add(lutemon);
            }
        }
    }

    public static TrainingArea getInstance() {
        if (trainingArea == null) {
            trainingArea = new TrainingArea();
        }
        return trainingArea;
    }

    @Override
    public ArrayList<Lutemon> getList() {
        return inTrainingArea;
    }
}