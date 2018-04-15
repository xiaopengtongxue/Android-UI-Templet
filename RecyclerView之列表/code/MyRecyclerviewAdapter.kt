package com.xiapeng.testwidget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Administrator on 2018/4/5.
 */
class MyRecyclerviewAdapter(context:Context,list:MutableList<Map<String,String>>):RecyclerView.Adapter<MyRecyclerviewAdapter.CardViewHolder>(){
    val myContext=context
    val myList=list

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder?, position: Int) {
        holder!!.itemTitle.text=myList[position].get("item_title")
        var message=myList[position].get("item_message")
        if(message!!.length>19)
            message=message!!.substring(0,18)+"..."
        holder!!.itemMessage.text=message
        holder!!.itemTime.text=myList[position].get("item_time")
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CardViewHolder {
        var view=LayoutInflater.from(myContext).inflate(R.layout.item_card, parent, false)
        return CardViewHolder(view)
    }

    class CardViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var itemTitle:TextView
        var itemMessage:TextView
        var itemTime:TextView
        init {
            itemTitle=itemView.findViewById(R.id.item_name)
            itemMessage=itemView.findViewById(R.id.item_message)
            itemTime=itemView.findViewById(R.id.item_time)
        }
    }
}