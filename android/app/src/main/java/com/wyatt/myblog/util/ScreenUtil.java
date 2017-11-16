package com.wyatt.myblog.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import java.lang.reflect.Method;

public class ScreenUtil
{

    /**
     * 获取手机屏幕宽度
     *
     * @param context
     * @return
     */
    @SuppressLint("NewApi")
    @SuppressWarnings("deprecation")
    public static int getScreenWidth(Context context)
    {
        //屏幕宽度
        int screenWidth = 0;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();

        //获取手机系统版本信息
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {//  sdk version>=API3
            Point outSize = new Point();
            defaultDisplay.getSize(outSize);
            screenWidth = outSize.x;
        } else
        {//  <api13
            screenWidth = defaultDisplay.getWidth();
        }
        return screenWidth;
    }

    /**
     * 获取手机屏幕高度
     *
     * @param context
     * @return
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static int getScreenHeight(Context context)
    {
        //屏幕高度
        int screenHeight = 0;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();

        //获取手机系统版本信息
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {//  sdk version>=API3
            Point outSize = new Point();
            defaultDisplay.getSize(outSize);
            screenHeight = outSize.y;
        } else
        {//  <api13
            screenHeight = defaultDisplay.getHeight();
        }
        return screenHeight;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue)
    {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static int sp2Heightpx(Context context , int spValue){
        TextView textView = new TextView(context);
        textView.setText("1");
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, spValue);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(ScreenUtil.getScreenWidth(context), View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }

    //获取屏幕原始尺寸高度，包括虚拟功能键高度
    public static int getDpi(Context context){
        int dpi = 0;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        @SuppressWarnings("rawtypes")
        Class c;
        try {
            c = Class.forName("android.view.Display");
            @SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics",DisplayMetrics.class);
            method.invoke(display, displayMetrics);
            dpi=displayMetrics.heightPixels;
        }catch(Exception e){
            e.printStackTrace();
        }
        return dpi;
    }

    /**
     * 获取 虚拟按键的高度
     * @param context
     * @return
     */
    public static  int getBottomStatusHeight(Context context){
        int totalHeight = getDpi(context);
        if (isAllScreen(context)) {
            return 0;
        }else {
            int contentHeight = getScreenHeight(context);
            return totalHeight  - contentHeight;
        }
    }


    /**
     * 获取内容显示的高度
     * @param window
     * @return
     */
    public static int getContentHeight(Window window) {
        // 应用区域
        Rect outRect1 = new Rect();
        window.getDecorView().getWindowVisibleDisplayFrame(outRect1);
        return outRect1.height()+outRect1.top;
    }


    /**
     * 是否为全面屏
     * @param context
     * @return
     */
    public static boolean isAllScreen(Context context) {
        int totalHeight = getDpi(context);
        int totalWidth = getScreenWidth(context);
        float aspect = (float) totalHeight/(float) totalWidth;
        if (aspect >= 2.0) {
            return true;
        }else{
            return false;
        }
    }


    /**
     * 标题栏高度
     * @return
     */
    public static int getTitleHeight(Activity activity){
        return  activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

}
