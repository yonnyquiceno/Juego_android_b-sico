package com.edu4java.android.killthemall.killthemall_trainingv2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameView extends SurfaceView {


    public List<Sprite> sprites = new ArrayList<Sprite>();
    public List<Sprite2> sprites2 = new ArrayList<Sprite2>();
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private long lastTouch;
    private boolean golpeado=false;
    private Sprite2 sprite2;

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
        sprites.add(createSprite(R.mipmap.orco));
        sprites2.add(createSprite2(R.mipmap.destroyer));




    }

    private Sprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite(this,bmp);

    }

    private Sprite2 createSprite2(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new Sprite2(this,bmp);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        escribir(canvas, String.valueOf(sprites.size()));
        for (Sprite sprite : sprites){
            sprite.onDraw(canvas);
        }
    }

    public void escribir(Canvas canvas, String text){
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setTextSize(80);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText(text, 200, 100, paint);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (System.currentTimeMillis()-lastTouch>100) {
            lastTouch = System.currentTimeMillis();

            synchronized (getHolder()) {

                for (int i = sprites.size() - 2; i >= 0; i--) {
                    Sprite sprite = sprites.get(i);
                    if (sprite.isCollition(event.getX(), event.getY())) {

                        int healt = sprite.golpe();
                        if (healt == 0){
                            sprites.remove(sprite);
                        }
                        break;
                    }

                }

                Sprite2 sprite2 =sprites2.get(0);

                if (sprite2.isLeftTouch(event.getX())){
                    sprite2.update();
                }

            }
        }    
        return true;
    }

}
