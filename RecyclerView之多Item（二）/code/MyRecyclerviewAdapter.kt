package com.xiapeng.testwidget

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_video.view.*

/**
 * Created by Administrator on 2018/4/5.
 */
class MyRecyclerviewAdapter(context:Context,list:MutableList<Map<String,String>>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val VIEW_NO_PICTURE = 1
    val VIEW_VIDEO = 2
    val VIEW_ONE_PICTURE = 3
    val VIEW_MORE_PICTURE = 4
    val myContext=context
    val myList=list

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if(holder is OnePictureViewHolder) {
            holder!!.itemView.item_title.text=myList[position].get("item_title")
        }else if (holder is MorePictureViewHolder){
            holder!!.itemView.item_title.text=myList[position].get("item_title")
        }else if (holder is VideoViewHolder){
            holder!!.itemView.item_title.text=myList[position].get("item_title")
        }else if (holder is NoPictureViewHolder){
            holder!!.itemView.item_title.text=myList[position].get("item_title")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var view: View
        if (viewType == VIEW_VIDEO) {
            view = LayoutInflater.from(myContext).inflate(R.layout.item_video, parent, false)
            return VideoViewHolder(view)
        } else if (viewType == VIEW_ONE_PICTURE){
            view=LayoutInflater.from(myContext).inflate(R.layout.item_one_picture,parent,false)
            return OnePictureViewHolder(view)
        } else if (viewType == VIEW_MORE_PICTURE){
            view=LayoutInflater.from(myContext).inflate(R.layout.item_more_picture,parent,false)
            return MorePictureViewHolder(view)
        } else {
            view=LayoutInflater.from(myContext).inflate(R.layout.item_no_picture,parent,false)
            return NoPictureViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = myList.get(position).getValue("type")

        if (item.equals("video")) {
            return VIEW_VIDEO
        } else if (item.equals("onePicture")) {
            return VIEW_ONE_PICTURE
        } else if (item.equals("morePicture")) {
            return VIEW_MORE_PICTURE
        }else  {
            return VIEW_NO_PICTURE
        }
    }



    class NoPictureViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }

    class OnePictureViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }

    class MorePictureViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }

    class VideoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    }
}