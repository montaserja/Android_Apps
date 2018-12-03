package com.example.montaserja.arkanoid;

import android.graphics.Canvas;
import android.util.Log;

public class BrickCollection {

    private int rows,cols;
    private Brick[][] Bricks;
    private int screenHeight,screenWidth;
    private float brickHeight,brickWidth,space,screenCanDraw;
    private int count;
    public BrickCollection(int rows,int cols,int h,int w,float screenCanDraw){
        this.rows=rows;
        this.cols=cols;
        Bricks=new Brick[rows][cols];
        screenHeight=h;
        screenWidth=w;
        space=5;
        brickHeight=h/20;
        brickWidth=(w-(cols+1)*space)/cols;
        this.screenCanDraw=screenCanDraw;
        count=0;
        init();
    }

    public void init(){
        float left,right,up,bottom;
        for (int i=0;i<rows;i++){
            up=(((i+1)*space)+(i*brickHeight))+screenCanDraw;
            bottom=up+brickHeight;
            for (int j =0; j<cols;j++){
                left=((j+1)*space)+(j*brickWidth);
                right=(left+brickWidth);
                Bricks[i][j]=new Brick(left,right,up,bottom);
            }

        }
    }

    public int draw(Canvas canvas,Ball ball){

        for (int i=0;i<rows;i++){
            for (int j =0; j<cols;j++){

                Bricks[i][j].draw(canvas);
               if( Bricks[i][j].onBallTouch(ball)){
                   count++;
                   if(count!=(rows*cols)) {

                   return 1;
                   }
                   else{
                       return 2;
                   }

               }
            }
        }
        return 0;
    }

}
