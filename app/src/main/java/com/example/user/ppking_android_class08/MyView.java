package com.example.user.ppking_android_class08;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MyView extends View{
    private Resources res ;
    private Context context;
    private int viewW , viewH;
    private boolean isInit;
    private Bitmap bmpBall;
    private Matrix matrix;
    private float ballW , ballH , ballX , ballY , dx , dy;
    Timer timer;


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        res = context.getResources();
        matrix = new Matrix();
        timer = new Timer();
        //建構式內還未畫出畫布，因此讀取寬高時會無資料，必須在OnDraw那邊讀取寬高才有資料
    }

    public void Init(){
        dx = dy = 10;
        viewW = getWidth();
        viewH = getHeight();
        bmpBall = BitmapFactory.decodeResource(res,R.drawable.pikachu);
        ballW = viewW/12f;
        ballH = ballW;
        matrix.postScale(ballW/bmpBall.getWidth(),ballH/bmpBall.getHeight());
        bmpBall = Bitmap.createBitmap(bmpBall,0,0,bmpBall.getWidth(),bmpBall.getHeight(),matrix,false);

        //1000/16 肉眼看到的fps
        timer.schedule(new timerTask(),1000,60);

        isInit = true;
    }

    private class timerTask extends TimerTask{
        @Override
        public void run() {
            if(ballX<0 || ballX + ballW >viewW){
                dx *= -1;
            }
            if(ballY<0 || ballY + ballH >viewH){
                dy *= -1;
            }
            ballX +=dx;
            ballY +=dy;
            postInvalidate();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInit){
            Init();
        }
//        setBackgroundColor(Color.YELLOW);
        //盡量在ondraw只做成像的動作
        canvas.drawBitmap(bmpBall,ballX,ballY,null);


    }

}
