package com.edu4java.android.killthemall.killthemall_trainingv2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {


    private List<Sprite> sprites = new ArrayList<Sprite>();
    private Bitmap bmp;
    private Bitmap bmp1;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private long lastTouch;

    public GameView(Context context) {
        super(context);


        gameLoopThread= new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
              gameLoopThread.setRunning(true);
              gameLoopThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {}
        });


        sprites.add(createSprite(R.mipmap.descarga));
        sprites.add(createSprite(R.mipmap.orco));
        sprites.add(createSprite(R.mipmap.hombre));
        sprites.add(createSprite(R.mipmap.hombre2));


    }

    private Sprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this,bmp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        for (Sprite sprite : sprites){
            sprite.onDraw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis()-lastTouch>300) {
            lastTouch = System.currentTimeMillis();

            synchronized (getHolder()) {
                for (int i = sprites.size() - 1; i >= 0; i--) {
                    Sprite sprite = sprites.get(i);
                    if (sprite.isCollition(event.getX(), event.getY())) {
                        sprites.remove(sprite);
                        break;
                    }
                }

            }
        }    
        return true;
    }

}
