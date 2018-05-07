package com.xiapeng.testwidget

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class ItemFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_tab,container,false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager= GridLayoutManager(this.activity,3)
        var recyclerview=view!!.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager=layoutManager
        var list:MutableList<Cartoon> = mutableListOf()
        var mCartoon=Cartoon()
        mCartoon.name="死亡游戏"
        mCartoon.update="更新至12话"
        mCartoon.picture=R.mipmap.c1
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="今日有喜"
        mCartoon.update="更新至16话"
        mCartoon.picture=R.mipmap.c2
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="好好先生"
        mCartoon.update="更新至122话"
        mCartoon.picture=R.mipmap.c3
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="岳桑"
        mCartoon.update="更新至300话"
        mCartoon.picture=R.mipmap.c4
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="游戏人生"
        mCartoon.update="更新至221话"
        mCartoon.picture=R.mipmap.c5
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="爱你一万年"
        mCartoon.update="更新至27话"
        mCartoon.picture=R.mipmap.c6
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="顺悟空"
        mCartoon.update="更新至36话"
        mCartoon.picture=R.mipmap.c7
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="大学"
        mCartoon.update="更新至137话"
        mCartoon.picture=R.mipmap.c8
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="病毒"
        mCartoon.update="更新至65话"
        mCartoon.picture=R.mipmap.c9
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="神秘型号"
        mCartoon.update="更新至14话"
        mCartoon.picture=R.mipmap.c10
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="外文局"
        mCartoon.update="更新至145话"
        mCartoon.picture=R.mipmap.c11
        list.add(mCartoon)
        mCartoon=Cartoon()
        mCartoon.name="反派必胜"
        mCartoon.update="更新至223话"
        mCartoon.picture=R.mipmap.c12
        list.add(mCartoon)
        recyclerview.adapter=MyRecyclerviewAdapter(this.activity,list)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }
}
