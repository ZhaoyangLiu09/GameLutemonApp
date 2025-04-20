package com.lutemon.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoveListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();
    private ArrayList<Lutemon> selectedLutemons = new ArrayList<>();

    public MoveListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
        this.selectedLutemons = new ArrayList<>();
    }


    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.move_lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.nameForMovingAct.setText(lutemons.get(position).getName() + " (" + lutemons.get(position).getColor() + ")");

        // 设置Lutemon的图像
        holder.lutemonMoveImage.setImageResource(lutemon.getImageResource());

        Location location = lutemons.get(position).getLocation();
        String locationString = "";
        switch (location) {
            case HOME:
                locationString = context.getString(R.string.kotona);
                break;
            case TRAINING_AREA:
                locationString = context.getString(R.string.harjoittelemassa);
                break;
            case BATTLEFIELD:
                locationString = context.getString(R.string.taistelukentalla);
                break;
        }

        holder.resetRadioButtons();

        holder.selectedLutemon.setOnCheckedChangeListener(null);

        holder.selectedLutemon.setChecked(selectedLutemons.contains(lutemon));
        holder.locationforMovingAct.setText(locationString);

        holder.selectedLutemon.setOnCheckedChangeListener((buttonView, isChecked) -> {
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


