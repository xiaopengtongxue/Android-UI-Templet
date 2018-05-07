package com.xiapeng.testwidget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.FragmentPagerAdapter
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    var list=ArrayList<Fragment>()
    var tabs=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    fun initView(){
        list.add(ItemFragment())
        list.add(ItemFragment())
        list.add(ItemFragment())
        list.add(ItemFragment())
        tabs.add("一")
        tabs.add("二")
        tabs.add("三")
        tabs.add("四")
        vp.adapter=MyPagerAdapter(supportFragmentManager)
        tl.setupWithViewPager(vp)
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
