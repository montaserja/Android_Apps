package com.example.montaserja.toetactic;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InstructionsActivity extends AppCompatActivity implements View.OnClickListener{

    Button startbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        startbtn=findViewById(R.id.stbtn);
        startbtn.setOnClickListener(this);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    }

    @Override
    public void onClick(View v) {
        Intent GameActivity = new Intent(this, GameActivity.class );
        startActivity(GameActivity);
    }
}
