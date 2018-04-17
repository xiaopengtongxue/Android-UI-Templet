package com.xiapeng.testwidget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

/**
 * Created by Administrator on 2018/4/5.
 */
class MyRecyclerviewAdapter(context:Context,list:MutableList<Cartoon>):RecyclerView.Adapter<MyRecyclerviewAdapter.HorizonScrollViewHolder>(){
    val myContext=context
    val myList=list

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: HorizonScrollViewHolder?, position: Int) {
        holder!!.itemTitle.text=myList[position].name
        holder!!.itemUpdate.text=myList[position].update
        holder!!.itemPicture.setImageResource(myList[position].picture)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HorizonScrollViewHolder {
        var view=LayoutInflater.from(myContext).inflate(R.layout.item_gird, parent, false)
        return HorizonScrollViewHolder(view)
    }

    class HorizonScrollViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemTitle:TextView
        var itemUpdate:TextView
        var itemPicture:ImageView
        init {
            itemTitle=itemView.findViewById(R.id.list_title)
            itemUpdate=itemView.findViewById(R.id.list_update)
            itemPicture=itemView.findViewById(R.id.picture_cartoon)
        }
    }
}