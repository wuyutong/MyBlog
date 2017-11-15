package com.wyatt.myblog.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wyatt.myblog.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onCreateBlog(View view) {
        Intent intent = new Intent();
        intent.setClass(this, CreateBlogActivity.class);
        startActivity(intent);
    }
}
