package com.xiapeng.testwidget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_oneline.view.*

/**
 * Created by Administrator on 2018/4/5.
 */
class MyRecyclerviewAdapter(context:Context,list:List<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val VIEW_EMPTY = 1
    val VIEW_ITEM = 2
    val myContext=context
    val myList=list

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(holder is ItemViewHolder) {
            holder!!.itemView.list_item.text=myList[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var view: View
        if (viewType == VIEW_EMPTY) {
            view = LayoutInflater.from(myContext).inflate(R.layout.item_blank, parent, false)
            return EmptyViewHolder(view)
        } else{
            view=LayoutInflater.from(myContext).inflate(R.layout.item_oneline,parent,false)
            return ItemViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position==0||position==2||position==5||position==8||position==11||position==14||position==16){
            return VIEW_EMPTY
        }else{
            return VIEW_ITEM
        }
    }

    class EmptyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }

    class ItemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var listitem:TextView

        init {
            listitem=itemView.findViewById<TextView>(R.id.list_item)
        }
    }
}