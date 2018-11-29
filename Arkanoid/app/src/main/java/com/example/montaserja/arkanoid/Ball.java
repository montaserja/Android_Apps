package com.example.montaserja.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {

    private int screenHeight,screenWidth;
    private float cx,cy,radius,dx,dy;
    Paint drawPen;

    public Ball(int h,int w){
        this.screenHeight=h;
        this.screenWidth=w;
        drawPen=new Paint(Paint.ANTI_ALIAS_FLAG);
        radius=15f;
        drawPen.setColor(Color.GREEN);
        drawPen.setStyle(Paint.Style.FILL);
        cx=screenWidth/2;
        cy=screenHeight-((screenHeight/10)+radius);
        dx=0;
        dy=0;

    }



    public void draw(Canvas canvas){
        canvas.drawCircle(cx,cy,radius,drawPen);
    }
    public void setLocation(float dx, float dy)
    {
        setDx(dx);
        setDy(dy);
    }

    public boolean move(float x,float y,float paddleLeft,float paddleRight , float paddleY,float padleBottom){
        this.cx+= dx;
        this.cy+= dy;

        // check if ball out of left or right side
        if((cx-radius)<=0 || (cx+radius)>=screenWidth )
        {
            this.dx = -x;
        }

        // check if ball out of bottom or up side
        if((cy+radius)>=screenHeight || (cy-radius)<=0)
        {
            this.dy = -y;
        }

        if(this.cy+radius== paddleY){
            if(this.cx<paddleRight && this.cx > paddleLeft){
                this.dy=-dy;
            }
        }else if(this.cy+radius> padleBottom){
            return false;
        }
        return true;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public void setDy(float dy) {
        this.dy = dy;
    }

    public float getDx() {
        return dx;
    }

    public float getDy() {
        return dy;
    }

    public float getCx() {
        return cx;
    }

    public float getCy() {
        return cy;
    }

    public float getRadius() {
        return radius;
    }
}
