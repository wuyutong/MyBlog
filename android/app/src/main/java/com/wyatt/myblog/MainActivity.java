package com.wyatt.myblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

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
