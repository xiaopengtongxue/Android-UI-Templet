package com.xiapeng.testwidget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    fun initView(){
        val layoutManager= LinearLayoutManager(this)
        layoutManager.orientation= LinearLayoutManager.VERTICAL
        recyclerview.layoutManager=layoutManager
        recyclerview.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))
        var list = ArrayList<String>()
        list.add("男神俱乐部")
        list.add("女神俱乐部")
        list.add("拳头帮")
        list.add("人人乐")
        list.add("萌娃大本营")
        list.add("运动快乐")
        recyclerview.adapter=MyRecyclerviewAdapter(this,list)
    }
}
