package com.edu4java.android.killthemall.killthemall_trainingv2;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Sprite2 {
    private int height;
    private int width;
    private int x = 0;
    private GameView gameView;
    private Bitmap bmp;
    private long lastx;

    public Sprite2(GameView gameView, Bitmap bmp) {
        this.gameView=gameView;
        this.bmp=bmp;
        this.width=bmp.getWidth();
        this.height=bmp.getHeight();
    }

    public void update(int lr) {
        if (lr==1){x=x-20;}
        else x=x+20;
    }

    public void onDraw(Canvas canvas) {

            canvas.drawBitmap(bmp, (gameView.getWidth() / 2) - width + x, gameView.getHeight() - height, null);

    }

    public boolean isLeftTouch(float x2) {
        return x2 < gameView.getWidth()/2;
    }
}


