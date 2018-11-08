package com.example.montaserja.toetactic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    private Button play;
    private Button instroctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play =  findViewById(R.id.play_btn);
        instroctions =  findViewById(R.id.inst_btn);



        play.setOnClickListener(this);
        instroctions.setOnClickListener( this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.play_btn:
                Intent GameActivity = new Intent(this, GameActivity.class );
               startActivity(GameActivity);
                break;

            case R.id.inst_btn:
                Intent InstActivity= new Intent(this, InstructionsActivity .class );
                startActivity(InstActivity);
                break;
        }
    }
}
