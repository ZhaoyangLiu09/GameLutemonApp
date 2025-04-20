package com.lutemon.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class AddLutemonActivity extends AppCompatActivity {

    private EditText nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lutemon);

        nameInput = findViewById(R.id.txtNameInput);
    }

    public void addLutemon (View view) {
        String lutemonName = nameInput.getText().toString();
        String lutemonColor = "";

        RadioGroup selectedColor = findViewById(R.id.rgColorSelection);

        if (selectedColor.getCheckedRadioButtonId() == R.id.rbWhite) {
            lutemonColor = getString(R.string.valkoinen);
        }
        else if (selectedColor.getCheckedRadioButtonId() == R.id.rbGreen) {
            lutemonColor = getString(R.string.vihrea);
        }
        else if (selectedColor.getCheckedRadioButtonId() == R.id.rbPink) {
            lutemonColor = getString(R.string.pinkki);
        }
        else if (selectedColor.getCheckedRadioButtonId() == R.id.rbOrange) {
            lutemonColor = getString(R.string.oranssi);
        }
        else {
            lutemonColor = getString(R.string.musta);
        }

        Home.getInstance().createNewLutemon(new Lutemon(lutemonName, lutemonColor));

        Intent intent = new Intent(this, MainScreenActivity.class);
        startActivity(intent);
    }




}