package com.lutemon.app;

import java.util.ArrayList;

public class Battlefield extends Storage {

    private ArrayList<Lutemon> inBattlefield = new ArrayList<>();

    private static Battlefield battlefield = null;

    private Battlefield() {
    }

    public static Battlefield getInstance() {
        if (battlefield == null) {
            battlefield = new Battlefield();
        }
        return battlefield;
    }

    public void moveToBattlefield(ArrayList<Lutemon> lutemons) {

        Home homeInstance = Home.getInstance();
        ArrayList<Lutemon> homeLutemons = homeInstance.getList();

        TrainingArea trainingAreaInstance = TrainingArea.getInstance();
        ArrayList<Lutemon> trainingLutemons = trainingAreaInstance.getList();

        for (Lutemon lutemon : lutemons) {
            if (lutemon.getLocation() == Location.HOME) {
                homeLutemons.remove(lutemon);
            }
            else if (lutemon.getLocation() == Location.TRAINING_AREA) {
                trainingLutemons.remove(lutemon);
            }

            if (!inBattlefield.contains(lutemon)) {
                lutemon.setLocation(Location.BATTLEFIELD);
                inBattlefield.add(lutemon);
            }
        }
    }


    @Override
    public ArrayList<Lutemon> getList() {
        return inBattlefield;
    }
}
