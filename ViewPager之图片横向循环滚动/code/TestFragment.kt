package com.xiapeng.testwidget

import android.os.*
import android.support.v4.app.Fragment
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit


/**
 * Created by Administrator on 2018/4/26.
 */
class TestFragment:Fragment() , ViewPager.OnPageChangeListener {
    var mView:View?=null
    var tips = ArrayList<View>()
    var mImages = ArrayList<ImageView>()
    var mImgId = arrayListOf<Int>()
    var mViewPager:ViewPager?=null
    var  curPosition=0
    var schedule:ScheduledExecutorService= Executors.newSingleThreadScheduledExecutor()
    var handler=object :Handler(){
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            curPosition=curPosition+1
            mViewPager!!.setCurrentItem(curPosition)
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(mView==null) {
            mView = inflater!!.inflate(R.layout.test_layout, container, false)
            mViewPager = mView!!.findViewById<ViewPager>(R.id.viewPager)
            mImgId.add(R.mipmap.c1)
            mImgId.add(R.mipmap.c2)
            mImgId.add(R.mipmap.c3)
            mImgId.add(R.mipmap.c4)

            for (i in 0..mImgId.size-1) {
                var imageView = ImageView(this.activity)
                imageView.setBackgroundResource(mImgId[i])
                mImages.add(imageView)
            }

            tips.add(mView!!.findViewById(R.id.v_dot0))
            tips.add(mView!!.findViewById(R.id.v_dot1))
            tips.add(mView!!.findViewById(R.id.v_dot2))
            tips.add(mView!!.findViewById(R.id.v_dot3))

            mViewPager!!.adapter=MyPager(mImages)
            mViewPager!!.setOnPageChangeListener(this)
        }
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        schedule.scheduleWithFixedDelay(ScrollImageTask(),2,2,TimeUnit.SECONDS)
    }

    inner class ScrollImageTask:Runnable{
        override fun run() {
            this@TestFragment.handler.obtainMessage().sendToTarget()
        }

    }


    class MyPager(images :ArrayList<ImageView>) : PagerAdapter(){
        var mImages=images
        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return Int.MAX_VALUE
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            container!!.removeView(mImages.get(position%mImages.size))
        }

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            container!!.addView(mImages.get(position%mImages.size))
            return mImages[position%mImages.size]
        }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        curPosition=position
        for (i in 0 until tips.size) {
            if (i == position%tips.size) {
                tips[i].setBackgroundResource(R.drawable.dot_focused)
            } else {
                tips[i].setBackgroundResource(R.drawable.dot_normal)
            }
        }
    }

}