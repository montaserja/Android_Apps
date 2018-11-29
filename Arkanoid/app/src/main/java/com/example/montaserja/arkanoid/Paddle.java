package com.example.montaserja.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Paddle {
    private enum State {GET_READY, PLAYING};

    private int screenHeight,screenWidth;
    private float upCx,upCy,bottomCx,bottomCy;
    Paint drawPen;
    private State state;
    private float lenght;


    public Paddle(int h,int w){
        this.screenHeight=h;
        this.screenWidth=w;
        drawPen=new Paint(Paint.ANTI_ALIAS_FLAG);
        state=State.GET_READY;
        this.lenght=w/7;
        upCy=(screenHeight-(screenHeight/10));
        bottomCy=(screenHeight-(screenHeight/13));
    }

    public void draw(Canvas canvas){
        drawPen.setColor(Color.BLUE);
        drawPen.setStyle(Paint.Style.FILL);

        if(state==State.PLAYING){
            if(upCx<=0){
                upCx=0;
                bottomCx=upCx+lenght;
            }
            if(bottomCx >= screenWidth){
                bottomCx=screenWidth;
                upCx=bottomCx-lenght;
            }
        }
        switch (state)
        {
            case GET_READY:
                upCx=(screenWidth/2)-(lenght/2);
                bottomCx=(screenWidth/2)+(lenght/2);

                canvas.drawRect(upCx,upCy,bottomCx,bottomCy,drawPen);
                break;

            case PLAYING:
                canvas.drawRect(upCx,upCy,bottomCx,bottomCy,drawPen);
                break;
        }

    }


    public void setx(int dir) {
        this.upCx=upCx+(dir);
        this.bottomCx=bottomCx+(dir);

    }

    public float getUpCx() {
        return upCx;
    }

    public float getBottomCx() {
        return bottomCx;
    }

    public float getUpCy() {
        return upCy;
    }

    public float getBottomCy() {
        return bottomCy;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setState(int s) {
        if(s==-1){
            state=State.GET_READY;
        }else if(s==0){
        state=State.PLAYING;
        }
        else{

        }

    }
}
