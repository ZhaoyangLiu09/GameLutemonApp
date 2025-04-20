package com.lutemon.app;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    TextView name, attack, defence, health, experience, location, nameForMovingAct, locationforMovingAct, battleLutemonText, inTheBattleText;
    Button moveLutemon, toBattleButton;
    RadioGroup selectedDestination;
    RadioButton toHome, toTraining, toBattlefield, selectedLutemon, selectedToBattle;
    ImageView lutemonImage, lutemonMoveImage, lutemonBattleImage;

    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.txtName);
        attack = itemView.findViewById(R.id.txtAttack);
        defence = itemView.findViewById(R.id.txtDefence);
        health = itemView.findViewById(R.id.txtHealth);
        experience = itemView.findViewById(R.id.txtExperience);
        location = itemView.findViewById(R.id.txtLocation);
        moveLutemon = itemView.findViewById(R.id.btnMove);
        nameForMovingAct = itemView.findViewById(R.id.txtNameMv);
        locationforMovingAct = itemView.findViewById(R.id.txtCurrentLocation);
        selectedDestination = itemView.findViewById(R.id.rgDestination);
        toHome = itemView.findViewById(R.id.rbToHome);
        toTraining = itemView.findViewById(R.id.rbToTraining);
        toBattlefield = itemView.findViewById(R.id.rbToBattlefield);
        selectedLutemon = itemView.findViewById(R.id.rbSelectedToMove);
        battleLutemonText = itemView.findViewById(R.id.txtBattleArenaLutemon);
        toBattleButton = itemView.findViewById(R.id.btnToBattle);
        inTheBattleText = itemView.findViewById(R.id.txtBattleResults);
        selectedToBattle = itemView.findViewById(R.id.rbToBattle);
        
        // 获取图像视图
        lutemonImage = itemView.findViewById(R.id.imgLutemon);
        lutemonMoveImage = itemView.findViewById(R.id.imgLutemonMove);
        lutemonBattleImage = itemView.findViewById(R.id.imgLutemonBattle);
    }
    
    public void setOnCheckedChangeListener(RadioButton.OnCheckedChangeListener listener) {
        selectedLutemon.setOnCheckedChangeListener(listener);
    }

    public void resetRadioButtons() {
        if (selectedLutemon != null) {
            selectedLutemon.setChecked(false);
        }
        if (selectedToBattle != null) {
            selectedToBattle.setChecked(false);
        }
    }
}

