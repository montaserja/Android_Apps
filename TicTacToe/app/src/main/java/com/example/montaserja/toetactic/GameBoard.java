package com.example.montaserja.toetactic;

import android.os.Debug;
import android.util.Log;

import static java.sql.DriverManager.println;

public class GameBoard {
    boolean win;
    String Game[];
    int Wins[];
    boolean xTurn;
    int counter;


    public GameBoard() {
        this.win = false;
        Game=new String[9];
        xTurn=true;
        counter=0;
        Wins=new int[3];

    }

    public void CheckTheGame() {

        checkRows();
        if(win==false)
        checkColumns();
        if(win==false)
        checkX();

    }

    private void checkRows(){
        int count = 1;
        for (int i = 1; i < Game.length; i++) {
            if (Game[i] != null && Game[i-1] !=null) {

                if(Game[i]==Game[i-1]) {
                    if(count<3) {
                        Wins[count - 1] = i - 1;
                        Wins[count] = i;
                    }
                    count++;
                }
                else
                {
                    Wins=new int[3];
                    count=1;
                    i+=(3-(i%3));
                }
                if(count==3 && i%3==2){
                    win=true;
                    break;
                }

            }else {
                Wins=new int[3];
                count = 1;
                i+=(3-(i%3));
            }

        }

    }
    private void checkColumns() {

        boolean notend = true;
        int count = 1;
        int i = 0;
        int col = 1;

        while (notend) {
            if (col < 4) {
                if (i < 6) {
                    if(Game[i]!=null && Game[i+3]!=null) {
                        if (Game[i] == Game[i + 3]) {
                            if(count<3) {
                                Wins[count - 1] = i;
                                Wins[count] = i+3;
                            }
                            count++;
                            i += 3;
                        } else {
                            Wins=new int[3];
                            count = 1;
                            i = col;
                            col++;

                        }

                        if (count == 3) {
                            notend = false;
                            win=true;
                            break;
                        }
                    }else{
                        Wins=new int[3];
                        count = 1;
                        i = col;
                        col++;

                    }
                }else
                    break;
            }else
                break;
        }


    }

    private void checkX(){
        if(Game[0]!=null && Game [4]!=null && Game[8]!=null) {
            if (Game[0] == Game[4] && Game[0] == Game[8]) {
                Wins[0]=0;Wins[1]=4;Wins[2]=8;
                win = true;
            }
        }
        if(Game[2]!=null && Game [4]!=null && Game[6]!=null) {
            if (Game[2] == Game[4] && Game[4] == Game[6]) {
                Wins[0]=2;Wins[1]=4;Wins[2]=6;
                win = true;

            }
        }
    }

    public int[] getWins(){
        return Wins;
    }

    public int TheGameEnds(){
        if(win==true){
            if(xTurn){
                return 1;
            }else
                return -1;
        }else if(counter==9){
            return 0;
        }
        else
            return 9;

    }

    public void Played(int pressed){
        counter++;
        if(xTurn){
            Game[pressed]="X";
        }else{
            Game[pressed]="O";
        }
        xTurn=!xTurn;
    }

    public String GetTheGame(int i){
        return Game[i];
    }
    public boolean Turn(){
        return xTurn;
    }
}
