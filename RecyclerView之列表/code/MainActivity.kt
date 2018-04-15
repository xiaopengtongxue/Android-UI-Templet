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
        map.put("item_message","苏宁易购Android架构演进史")
        map.put("item_title","前端之巅")
        map.put("item_time","21:59")
        list.add(map)
        map=HashMap<String,String>()
        map.put("item_message","你还敢熬夜吗？研究首次证实：熬夜提高早死风险")
        map.put("item_title","企鹅科学")
        map.put("item_time","18:31")
        list.add(map)
        map=HashMap<String,String>()
        map.put("item_message","2017金融科技安全分析报告")
        map.put("item_title","FreeBuf")
        map.put("item_time","18:07")
        list.add(map)
        map=HashMap<String,String>()
        map.put("item_message","全球大型电商测试基础框架设计概览")
        map.put("item_title","infoQ")
        map.put("item_time","09:40")
        list.add(map)
        map=HashMap<String,String>()
        map.put("item_message","TensorFlow中文社区论坛(测试版)上线")
        map.put("item_title","谷歌开发者")
        map.put("item_time","周五")
        list.add(map)
        map=HashMap<String,String>()
        map.put("item_message","他比乔布斯还牛，做完这位神的访谈就跟大家说再见")
        map.put("item_title","图灵访谈")
        map.put("item_time","4月1日")
        list.add(map)
        recyclerview.adapter=MyRecyclerviewAdapter(this,list)
    }
}
