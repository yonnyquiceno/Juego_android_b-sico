package com.edu4java.android.killthemall.killthemall_trainingv2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.widget.Toast;

import java.util.Random;

public class Sprite {
    private int height;
    private int width;
    private int x = 0;
    private int y = 0;
    private int xSpeed=20;
    private int ySpeed;
    private GameView gameView;
    private Bitmap bmp;
    private static final int bmp_columnas=13;
    private static final int bmp_filas=21;
    private int currentFrame;
    int[] DIRECTION_TO_ANIMATION_MAP = { 8, 9, 10, 11, 8  };


    public Sprite(GameView gameView, Bitmap bmp) {
        this.gameView=gameView;
        this.bmp=bmp;
        this.width=bmp.getWidth() / bmp_columnas;
        this.height=bmp.getHeight()/ bmp_filas;
        Random rnd= new Random();
        xSpeed=rnd.nextInt(30)-20;
        ySpeed=rnd.nextInt(30)-20;

    }

    private void update() {

        Random rnd= new Random();

        if (x >= gameView.getWidth() - width - xSpeed || x + xSpeed <= 0) {
            xSpeed = -xSpeed;
            ySpeed=rnd.nextInt(30)-20;
        }
        x = x + xSpeed;

        if (y >= gameView.getHeight() - height - ySpeed || y + ySpeed <= 0) {
            ySpeed = -ySpeed;
            xSpeed=rnd.nextInt(30)-20;
        }
        y = y + ySpeed;
        currentFrame = ++currentFrame % bmp_columnas;

        if (currentFrame<8) {
            currentFrame = ++currentFrame % bmp_columnas;
        }
        else currentFrame = 1;
    }

    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = (getAnimationRow())* height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x+ width, y+ height);
        canvas.drawBitmap(bmp, src, dst, null);
    }


    private int getAnimationRow() {
        double dirDouble = (Math.atan2(xSpeed, ySpeed) / (Math.PI / 2) + 2);
        int direction =( (int) Math.round(dirDouble) % bmp_filas);
        return DIRECTION_TO_ANIMATION_MAP[direction];}

    public boolean isCollition(float x2, float y2) {
          return x2 > x && x2 < x + width;
        //&& y2 > y && y2 < y + height;
    }
}