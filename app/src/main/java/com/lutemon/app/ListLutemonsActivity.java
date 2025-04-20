package com.lutemon.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ListLutemonsActivity extends AppCompatActivity {

    private Storage storage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_lutemons);

        ArrayList<Lutemon> allLutemons = new ArrayList<>();


        Home homeLutemons = Home.getInstance();
        allLutemons.addAll(homeLutemons.getList());

        TrainingArea trainingAreaLutemons = TrainingArea.getInstance();
        allLutemons.addAll(trainingAreaLutemons.getList());

        Battlefield battlefieldLutemons = Battlefield.getInstance();
        allLutemons.addAll(battlefieldLutemons.getList());

        recyclerView = findViewById(R.id.rvLutemonList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new LutemonListAdapter(getApplicationContext(), allLutemons));
    }

}