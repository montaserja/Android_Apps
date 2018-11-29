package com.example.montaserja.arkanoid;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Brick {
    private float left,right,up,down;
    private Paint pen;
    private boolean notDestroy;
    private boolean stop;

    public Brick(float left,float right,float up,float down){
        this.left=left;
        this.right=right;
        this.up=up;
        this.down=down;
        notDestroy=true;
        stop=true;
        pen=new Paint(Paint.ANTI_ALIAS_FLAG);
        pen.setColor(Color.RED);
        pen.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas){
        if(notDestroy) {
            canvas.drawRect(left, up, right, down, pen);
        }
    }


    public boolean onBallTouch(Ball ball){
        //ball.getCy()+ball.getRadius()== this.up ||
       // Log.d(">>>>  ", "cy " + (ball.getCy() + ball.getRadius()) + " up " + this.up);
if(stop) {
    if (ball.getCy() + ball.getRadius() >= this.up && ball.getCy() - ball.getRadius() <= this.down) {
        if (ball.getCx()-ball.getRadius() <= right && ball.getCx()+ball.getRadius() >= left) {
            notDestroy = false;
            stop=false;
            ball.setDy(-ball.getDy());
            left = -1;
            right = 0;
            up = -1;
            down = 0;
            return true;
        }
    }

   /* if (ball.getCx() + ball.getRadius() >= this.left || ball.getCx() - ball.getRadius() <= this.right) {
        if (ball.getCy() >= up && ball.getCy() <= down) {
            notDestroy = false;
            stop=false;
            ball.setDx(-ball.getDx());
            left = -1;
            right = 0;
            up = -1;
            down = 0;
            Log.d(">right&left  ", "" + notDestroy);
        }
    }*/
}



        return false;
    }
}
