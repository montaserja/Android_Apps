package com.example.montaserja.toetactic;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    Button[] buttons;
    Button playAgain;
    String [] Game;
    GameBoard gameBoard;
    boolean win,noting;
    int xWins,oWins,Draws;
    TextView xTxt,oTxt,dTxt,turnTxt;
    MediaPlayer ring;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        buttons = new Button[9];

        playAgain=findViewById(R.id.PlayAgain_btn);
        xTxt=findViewById(R.id.xScoreTxt);
        oTxt=findViewById(R.id.oScoreTxt);
        dTxt=findViewById(R.id.dScoreTxt);
        turnTxt=findViewById(R.id.turnTxt);
        noting=true;
        Game=new String[9];
        gameBoard=new GameBoard();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);


        xWins=0;
        oWins=0;
        Draws=0;

        win=false;



        for(int i=0; i<buttons.length; i++) {
            {
                String buttonID = "btn" + (i+1);

                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i] = ((Button) findViewById(resID));
                buttons[i].setOnClickListener(this);
            }
        }
        playAgain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int index = 0;

        for (int i = 0; i < buttons.length; i++)
        {
            if (buttons[i].getId() == v.getId())
            {

                index = i;
                gameBoard.Played(index);
                gameBoard.CheckTheGame();
                buttons[index].setText(gameBoard.GetTheGame(index));

                break;
            }
        }
        if(!gameBoard.win) {
            buttons[index].setEnabled(false);
        }

       if(playAgain.getId()==v.getId()){
            ring.stop();
            for (int i = 0; i < buttons.length; i++){
                buttons[i].setEnabled(true);
                buttons[i].setText("");
                buttons[i].setTextColor(Color.BLACK);
            }
            playAgain.setEnabled(false);

            win=false;
            gameBoard=new GameBoard();
        }
        if(gameBoard.TheGameEnds()==1 || gameBoard.TheGameEnds()==-1){
            int arr[]=gameBoard.getWins();
            for(int i=0;i<arr.length;i++){
                buttons[arr[i]].setTextColor(Color.BLUE);
            }
            for (int i = 0; i < buttons.length; i++)
            {
                buttons[i].setEnabled(false);
            }
            playAgain.setEnabled(true);
            if(gameBoard.TheGameEnds()==1) {
                turnTxt.setText("O Win's");
                oWins++;
                oTxt.setText(String.valueOf(oWins));
            }
            else {
                turnTxt.setText("X Win's");
                xWins++;
                xTxt.setText(String.valueOf(xWins));
            }
            Toast.makeText(this, "Game Over", Toast.LENGTH_LONG).show();
            ring= MediaPlayer.create(this,R.raw.tada);
            ring.start();
        }
        if(gameBoard.TheGameEnds()==0){
            turnTxt.setText("Draw-No Winners!");
                Toast.makeText(this, "Game Over", Toast.LENGTH_LONG).show();
            ring= MediaPlayer.create(this,R.raw.lose);
            ring.start();
                Draws++;
            dTxt.setText(String.valueOf(Draws));
            playAgain.setEnabled(true);
        }
        if(gameBoard.TheGameEnds()==9) {
            if (gameBoard.Turn()) {
                turnTxt.setText("X Turn");
            } else {
                turnTxt.setText("O Turn");
            }
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        SharedPreferences sp = getSharedPreferences("MyPref" , Context.MODE_PRIVATE);
        xWins=Integer.parseInt(sp.getString("XWins", "0"));
        oWins=Integer.parseInt(sp.getString("OWins", "0"));
        Draws=Integer.parseInt(sp.getString("Draws", "0"));
        xTxt.setText(String.valueOf(xWins));
        oTxt.setText(String.valueOf(oWins));
        dTxt.setText(String.valueOf(Draws));

    }






    @Override
    protected void onStop() {
        super.onStop();

        // save data into sheardPrefrences
        SharedPreferences sp = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("OWins",  oTxt.getText().toString());
        editor.putString("XWins",  xTxt.getText().toString());
        editor.putString("Draws",  dTxt.getText().toString());
        // editor.putInt("lastPicIndex", currentPicIndex);
        editor.commit();

        Log.d("debug", ">>>>>>>>>> onPause()");
    }






    }

