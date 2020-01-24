package com.example.furniturefinal.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.furniturefinal.R;

public class ShipActivity extends AppCompatActivity {
private Button confirm =(Button)findViewById(R.id.ship_confirm);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ship);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAlert();
            }
            public void displayAlert() {
                new AlertDialog.Builder(ShipActivity.this).setMessage("Check Your Mail")
                        .setTitle("Email Invoice")
                        .setCancelable(true)
                        .setNeutralButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        finish();
                                    }
                                })
                        .show();
            }
        });


    }
}
