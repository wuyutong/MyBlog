package com.wyatt.myblog.view.read;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wyatt.myblog.R;
import com.wyatt.myblog.util.ScreenUtil;

import java.lang.ref.WeakReference;

/**
 * Created by Wyatt on 2017/11/16/016.
 */

public class ReadView1 extends View implements Runnable{
    private Paint mPaint;
    private int raduis = 10;
    private float x = 0;
    private float y = 0;
    private Context mContext;

    public ReadView1(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public ReadView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ReadView1, 0, 0);
        try {
            init(array.getColor(R.styleable.ReadView1_bg_color,Color.BLUE));
            raduis = array.getInteger(R.styleable.ReadView1_radius, 10);
        }finally {
            array.recycle();
        }
        star();

    }

    public ReadView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
    }


    private void init(@ColorInt int color) {
        mPaint = new Paint();
        mPaint.setColor(color);
    }

    float p1,p2,p3;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        p1 = -x;
        p2 = ScreenUtil.getScreenWidth(mContext)-x;
        p3 = x;
//        canvas.drawRect(x-ScreenUtil.getScreenWidth(mContext),0, x,ScreenUtil.getScreenHeight(mContext),mPaint);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(x,0, ScreenUtil.getScreenWidth(mContext)+x,y,mPaint);
//        canvas.drawColor(Color.parseColor("#aaaaaaaa"));
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x = event.getX();
            System.out.println("READVIEW   "+x);
            invalidate();
        }
        return true;
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                y = y+0.5f;
                invalidate();
            }
        }
    };

    public static class MyHandler extends Handler{
        private WeakReference<Context> reference;
        public MyHandler(Context context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    }


    private void star() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(5);
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(200);
                Message msg = new Message();
                msg.what = 1;
                handler.sendMessage(msg);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
