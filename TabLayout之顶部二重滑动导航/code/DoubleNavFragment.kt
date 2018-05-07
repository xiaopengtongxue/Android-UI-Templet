package com.xiapeng.testwidget

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
/**
 * Created by Administrator on 2018/4/29.
 */
class DoubleNavFragment:Fragment(){
    var list=ArrayList<Fragment>()
    var tabs=ArrayList<String>()
    var mView:View?=null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(mView==null){
            mView=inflater!!.inflate(R.layout.fragment_double_nav,container,false)
            var navition=mView!!.findViewById<TabLayout>(R.id.second_tab_nav)
            var mViewPager=mView!!.findViewById<ViewPager>(R.id.second_vp)
            list.add(ItemFragment())
            list.add(ItemFragment())
            list.add(ItemFragment())
            list.add(ItemFragment())
            list.add(ItemFragment())
            tabs.add("周一")
            tabs.add("周二")
            tabs.add("周三")
            tabs.add("周四")
            tabs.add("周五")
            mViewPager.adapter=MyPagerAdapter(activity.supportFragmentManager)
            navition.setupWithViewPager(mViewPager)
        }

        return mView
    }


    private inner class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return list.get(position)
        }

        override fun getCount(): Int {
            return list.size
        }

        override fun getPageTitle(position: Int): CharSequence {
            return tabs[position]
        }
    }
}