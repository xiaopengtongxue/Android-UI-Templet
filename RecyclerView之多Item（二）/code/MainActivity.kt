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
        var list:MutableList<Map<String,String>> = mutableListOf()
        var map=HashMap<String,String>()
        map.put("type","video")
        map.put("item_title","公路事故时，如果规定责任")
        list.add(map)
        map=HashMap<String,String>()
        map.put("type","onePicture")
        map.put("item_title","老公出轨，你好如何处理")
        list.add(map)
        map=HashMap<String,String>()
        map.put("type","morePicture")
        map.put("item_title","脱单秘籍")
        list.add(map)
        map=HashMap<String,String>()
        map.put("type","noPicture")
        map.put("item_title","第三次世界大战即将爆发")
        list.add(map)
        map=HashMap<String,String>()
        map.put("type","video")
        map.put("item_title","谁说最后的凶手")
        list.add(map)
        map=HashMap<String,String>()
        map.put("type","morePicture")
        map.put("item_title","不能说的秘密，女性美容")
        list.add(map)
        recyclerview.adapter=MyRecyclerviewAdapter(this,list)
    }
}
