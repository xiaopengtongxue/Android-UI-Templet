package com.xiapeng.testwidget

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent



/**
 * Created by Administrator on 2018/5/1.
 */
class NoScrollViewPager : ViewPager {
    private var noScroll = true

    constructor(context: Context):super(context){}

    constructor(context: Context, attrs: AttributeSet):super(context,attrs){}

    fun setNoScroll(noScroll: Boolean) {
        this.noScroll = noScroll
    }

    override fun scrollTo(x: Int, y: Int) {
        super.scrollTo(x, y)
    }

    override fun onTouchEvent(arg0: MotionEvent): Boolean {
        return if (noScroll)
            false
        else
            super.onTouchEvent(arg0)
    }

    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        return if (noScroll)
            false
        else
            super.onInterceptTouchEvent(arg0)
    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, smoothScroll)
    }

    override fun setCurrentItem(item: Int) {
        super.setCurrentItem(item, false)//表示切换的时候，不需要切换时间。
    }
}