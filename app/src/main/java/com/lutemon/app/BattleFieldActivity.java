package com.lutemon.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class BattleFieldActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView txtBattleResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_field);

        ArrayList<Lutemon> battlefieldLutemons = Battlefield.getInstance().getList();
        txtBattleResults = findViewById(R.id.txtBattleResults);
        recyclerView = findViewById(R.id.rvBattleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BattlefieldAdapter(getApplicationContext(), battlefieldLutemons));

        View ToBattleButton = findViewById(R.id.btnToBattle);
        ToBattleButton.setOnClickListener(view -> battle());



    }

    public void battle() {
        BattlefieldAdapter bfAdapter = (BattlefieldAdapter) recyclerView.getAdapter();
        ArrayList<Lutemon> selectedLutemons = bfAdapter.getSelectedLutemons();
        txtBattleResults.setText("");
        if (selectedLutemons.size() != 2) {
            txtBattleResults.setText(getString(R.string.valitse_kaksi_lutemonia));
        }
        else {
            Collections.shuffle(selectedLutemons, new Random());
            Lutemon lutemon1 = selectedLutemons.get(0);
            Lutemon lutemon2 = selectedLutemons.get(1);

            txtBattleResults.append("\n" + getString(R.string.aloittajaksi_arvottiin) + " " + lutemon1.getName());

            boolean battleEnded = false;

            while (!battleEnded) {
                txtBattleResults.append("\n" + lutemon1.getName() + " " + getString(R.string.hyokkaa));
                int lutemon2Health = (lutemon2.getHealth() + lutemon2.getDefence()) - (lutemon1.getAttack() + lutemon1.getExperience());
                lutemon2.setHealth(lutemon2Health);

                if (lutemon2Health <= 0) {
                    txtBattleResults.append("\n" + lutemon2.getName() + " " + getString(R.string.kuoli));
                    txtBattleResults.append("\n" + lutemon1.getName() + " " + getString(R.string.voitti));
                    lutemon1.addExperience();
                    Battlefield.getInstance().getList().remove(lutemon2);
                    battleEnded = true;
                    break;
                }
                else {
                    txtBattleResults.append("\n" + lutemon2.getName() + " " + getString(R.string.selvisi_hyokkayksesta));
                    txtBattleResults.append("\n" + lutemon2.getName() + getString(R.string.elama_on) + " " + lutemon2.getHealth() + " / " + lutemon2.getMaxHealth());
                }
                txtBattleResults.append("\n" + lutemon2.getName() + " " + getString(R.string.hyokkaa));
                int lutemon1Health = (lutemon1.getHealth() + lutemon1.getDefence()) - (lutemon2.getAttack() + lutemon2.getExperience());
                lutemon1.setHealth(lutemon1Health);

                if (lutemon1Health <= 0) {
                    txtBattleResults.append("\n" + lutemon1.getName() + " " + getString(R.string.kuoli));
                    txtBattleResults.append("\n" + lutemon2.getName() + " " + getString(R.string.voitti));
                    lutemon2.addExperience();
                    Battlefield.getInstance().getList().remove(lutemon1);
                    battleEnded = true;
                    break;
                }
                else {
                    txtBattleResults.append("\n" + lutemon1.getName() + " " + getString(R.string.selvisi_hyokkayksesta));
                    txtBattleResults.append("\n" + lutemon1.getName() + getString(R.string.elama_on) + " " + lutemon1.getHealth() + " / " + lutemon1.getMaxHealth());
                }
            }

            txtBattleResults.append("\n" + getString(R.string.taistelu_paattyi));

            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
                if (viewHolder instanceof LutemonViewHolder) {
                    ((LutemonViewHolder) viewHolder).resetRadioButtons();
                }
            }
            selectedLutemons.clear();
            bfAdapter.clearSelection();
            bfAdapter.notifyDataSetChanged();
        }
    }
}