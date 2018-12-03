package com.example.montaserja.arkanoid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Debug;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameView extends View {
    private static final int MAX_TARGET_BALLS = 7;

    // states
    private enum State {GET_READY, PLAYING, GAME_OVER};



    private float fx, fy;       // for finger touch location
    private int bgColor;        // bg color
    private Paint penInfo, penMsg;
    private int score,lifes,speedX,speedY;
    private Paddle paddle;
    private Ball ball;
    private BrickCollection Bricks;
    private int canvasWidth;
    private int canvasHeight;
    private boolean isPressing,left,right;
    private boolean ballHasntMoved;
    private float afterTextsY;
    private int [] speed;
    private String gameOver;
    private Context context;
   private MediaPlayer ring;
    // current state
    private State state;
    public GameView(Context context , AttributeSet attrs) {
        super(context, attrs);
        lifes=3;
        score=0;
        afterTextsY=100;
        this.context=context;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(bgColor);
        penInfo.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Score: " +score, 50, 100, penInfo);
        penInfo.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Lifes: " +lifes, canvas.getWidth()-50, afterTextsY, penInfo);
        if(isPressing)
        {
            if(left){
                paddle.setx(-10);
            }
            if(right){
                paddle.setx(10);
            }
        }
        ball.draw(canvas);
        paddle.draw(canvas);
        int check=Bricks.draw(canvas,ball);
        if(check==1){
            ring= MediaPlayer.create(context,R.raw.blop);
            ring.start();
            score+=5*lifes;
        }else if(check==2){
            state=State.GAME_OVER;
            gameOver="GAME OVER - You Win!";
        }
        switch (state)
        {
            case GET_READY:
                canvas.drawText("Click to PLAY!", canvasWidth/2, canvasHeight/2, penMsg);
                break;

            case PLAYING:
               if( ball.move(ball.getDx(),ball.getDy(),paddle.getUpCx(),paddle.getBottomCx(),paddle.getUpCy(),paddle.getBottomCy())==false){
                   if(lifes>1){
                       lifes--;

                       initGame();
                   }else{
                       lifes--;
                       state=State.GAME_OVER;
                   }
               }
                if(ballHasntMoved) {
                    ball.setLocation(speedX, -5);
                    ballHasntMoved=false;
                }
                break;

            case GAME_OVER:
                canvas.drawText(gameOver, canvasWidth/2, canvasHeight/2, penMsg);
                break;
        }
        invalidate();

    }

    private void initGame()
    {
        state = State.GET_READY;

        speed=new int[3];
        speed[0]=5;
        speed[1]=-5;
        Random r = new Random();
        int i1 = r.nextInt(2);

        speedX=speed[i1];

gameOver="GAME OVER - You Loss!";

        // paint for info text
        penInfo = new Paint(Paint.ANTI_ALIAS_FLAG);
            penInfo.setColor(Color.YELLOW);
        penInfo.setTextSize(50);
        penInfo.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        // paint for messages text
        penMsg = new Paint(Paint.ANTI_ALIAS_FLAG);
        penMsg.setTextAlign(Paint.Align.CENTER);
        penMsg.setColor(Color.GREEN);
        penMsg.setStyle(Paint.Style.STROKE);
        penMsg.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        penMsg.setTextSize(100);
        bgColor = Color.DKGRAY;
        isPressing=false;
        left=false;
        right=false;
        ballHasntMoved=true;
        state=State.GET_READY;

        paddle=new Paddle(canvasHeight,canvasWidth);
        ball=new Ball(canvasHeight,canvasWidth);


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        canvasWidth = w;
        canvasHeight = h;
        Bricks=new BrickCollection(5,7,canvasHeight,canvasWidth,afterTextsY+50);
        initGame();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        fx = event.getX();
        fy = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(state!=State.GAME_OVER) {
                    isPressing = true;
                    if (state == State.PLAYING) {
                        if (fx < canvasWidth / 2) {
                            left = true;
                            right = false;
                        } else if (fx > canvasWidth / 2) {
                            right = true;
                            left = false;
                        }
                    } else {
                        state = State.PLAYING;
                        paddle.setState(0);

                    }
                }else{
                    lifes=3;
                    score=0;
                    Bricks=new BrickCollection(5,7,canvasHeight,canvasWidth,afterTextsY+50);
                    initGame();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                break;

            case MotionEvent.ACTION_UP:

                isPressing=false;
                left=false;
                right=false;

                break;

        }
        return true;
    }
}
