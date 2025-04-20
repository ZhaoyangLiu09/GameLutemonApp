package com.lutemon.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class MoveLutemonActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;
    private Button moveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_lutemon);

        ArrayList<Lutemon> allLutemons = new ArrayList<>();


        Home homeLutemons = Home.getInstance();
        allLutemons.addAll(homeLutemons.getList());

        TrainingArea trainingAreaLutemons = TrainingArea.getInstance();
        allLutemons.addAll(trainingAreaLutemons.getList());

        Battlefield battlefieldLutemons = Battlefield.getInstance();
        allLutemons.addAll(battlefieldLutemons.getList());

        recyclerView = findViewById(R.id.rvMoveList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MoveListAdapter(getApplicationContext(), allLutemons));

        moveButton = findViewById(R.id.btnMove);

        moveButton.setOnClickListener(view -> moveLutemon());
    }




    public void moveLutemon() {
        RadioGroup rgMoveDestination = findViewById(R.id.rgDestination);
        MoveListAdapter adapter = (MoveListAdapter) recyclerView.getAdapter();
        ArrayList<Lutemon> selectedLutemons = adapter.getSelectedLutemons();

        if (selectedLutemons.isEmpty()) {
            System.out.println(getString(R.string.ei_siirrettavia_lutemoneja));
        } else {
            if (rgMoveDestination.getCheckedRadioButtonId() == R.id.rbToHome) {
                Home.getInstance().moveToHome(selectedLutemons);
            } else if (rgMoveDestination.getCheckedRadioButtonId() == R.id.rbToTraining) {
                TrainingArea.getInstance().moveToTrainingArea(selectedLutemons);
            } else if (rgMoveDestination.getCheckedRadioButtonId() == R.id.rbToBattlefield) {
                Battlefield.getInstance().moveToBattlefield(selectedLutemons);
            }

            selectedLutemons.clear();
            adapter.notifyDataSetChanged();
            adapter.clearSelection();



        }
    }


}