package com.wyatt.myblog.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.wyatt.myblog.R
import kotlinx.android.synthetic.main.activity_create_blog.*

class CreateBlogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_blog)
        tvTitle.setText(getMyTitle())
    }

    fun onCreateBlog(view: View) {
        val intent = Intent()
        intent.setClass(this, CreateBlogActivity::class.java)
        startActivity(intent)
    }

    fun getMyTitle(type:Int = 3) = if (type == 1) "创建" else "编辑$type"
}
