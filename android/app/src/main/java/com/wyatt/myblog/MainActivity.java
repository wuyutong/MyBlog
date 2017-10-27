package com.wyatt.myblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    /*****************************
     * 操作栏
     * 1.如何使用
     * 2.关系结构
     * ******************************
     * recycleView
     * ******************************
     * 使用greenDao存储数据
     * ******************************
     * 使用Sharepreference
     * ******************************
     * io存储
     * ******************************
     * 使用RxJava
     * ******************************
     * 使用Retrofit
     * ******************************
     * ******************************
     * ******************************
     * ******************************
     * ******************************
     * ******************************
     * ******************************
     * ******************************
     * 构想：
     *
     * ******************************
     *
     */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onCreateBlog() {
        Intent intent = new Intent();
        intent.setClass(this, CreateBlogActivity.class);
        startActivity(intent);

    }
}
