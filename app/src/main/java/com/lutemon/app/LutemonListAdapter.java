package com.lutemon.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {

    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    //public LutemonListAdapter(Context applicationContext, ArrayList<Lutemon> allLutemons, RadioGroup moveDestination) {
//
    //}

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        holder.name.setText(lutemons.get(position).getName() + " (" + lutemons.get(position).getColor() + ")");
        holder.attack.setText(context.getString(R.string.hyokkays) + lutemons.get(position).getAttack());
        holder.defence.setText(context.getString(R.string.puolustus) + lutemons.get(position).getDefence());
        holder.health.setText(context.getString(R.string.elama) + lutemons.get(position).getHealth() + " / " + lutemons.get(position).getMaxHealth());
        holder.experience.setText(context.getString(R.string.kokemus) + lutemons.get(position).getExperience());
        
        // 设置Lutemon的图像
        holder.lutemonImage.setImageResource(lutemons.get(position).getImageResource());

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
        holder.location.setText(locationString);

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}


