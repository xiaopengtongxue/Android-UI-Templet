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
        var list= listOf<String>("","朋友圈","","扫一扫","摇一摇","","看一看","搜一搜",
                "","附近的人","漂流瓶","","购物","游戏","","小程序","")
        recyclerview.adapter=MyRecyclerviewAdapter(this,list)
    }
}
