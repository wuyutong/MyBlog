package com.wyatt.myblog.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.wyatt.myblog.R;
import com.wyatt.myblog.view.read.ReadView1;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView tvPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        tvPro = (TextView) findViewById(R.id.tvPro);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPro.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onCreateBlog(View view) {
        Intent intent = new Intent();
        intent.setClass(this, CreateBlogActivity.class);
        startActivity(intent);
    }
}
