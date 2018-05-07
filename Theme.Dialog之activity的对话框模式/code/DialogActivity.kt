package com.xiapeng.testwidget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import kotlinx.android.synthetic.main.activity_dialog.*

class DialogActivity : AppCompatActivity(),View.OnClickListener {
    override fun onClick(v: View?) {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_dialog)
        image_delete.setOnClickListener(this)
        setFinishOnTouchOutside(false)
    }
}
