package com.example.montaserja.toetactic;

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
    boolean win;
    int count,xWins,oWins,Draws;
    TextView xTxt,oTxt,dTxt,turnTxt;


    final String X="X";
    final String O="O";
    boolean xTurn=true;
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
        Game=new String[9];
        gameBoard=new GameBoard();
        win=false;
        count=0;
        xWins=0;
        oWins=0;
        Draws=0;

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
                count++;
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
            for (int i = 0; i < buttons.length; i++){
                buttons[i].setEnabled(true);
                buttons[i].setText("");
            }
            playAgain.setEnabled(false);
            count=0;
            win=false;
            gameBoard=new GameBoard();
        }
        if(gameBoard.TheGameEnds()==1 || gameBoard.TheGameEnds()==-1){
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
            Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
        }
        if(gameBoard.TheGameEnds()==0){
            turnTxt.setText("Draw-No Winners!");
                Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
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




    }

