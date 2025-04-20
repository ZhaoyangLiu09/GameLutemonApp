package com.lutemon.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BattlefieldAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private ArrayList<Lutemon> selectedLutemons = new ArrayList<>();

    public BattlefieldAdapter(Context context, ArrayList<Lutemon> lutemons){
        this.context = context;
        this.lutemons = lutemons;
        this.selectedLutemons = new ArrayList<>();
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.battle_lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.battleLutemonText.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
        
        // Set Lutemon's image
        holder.lutemonBattleImage.setImageResource(lutemon.getImageResource());

        holder.resetRadioButtons();

        holder.selectedToBattle.setOnCheckedChangeListener(null);

        holder.selectedToBattle.setChecked(selectedLutemons.contains(lutemon));

        holder.selectedToBattle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                selectedLutemons.add(lutemon);
            }
            else {
                selectedLutemons.remove(lutemon);
            }
        });


    }

    public ArrayList<Lutemon> getSelectedLutemons() {

        return selectedLutemons;
    }

    public void clearSelection() {
        selectedLutemons.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {

        return lutemons.size();
    }




}



