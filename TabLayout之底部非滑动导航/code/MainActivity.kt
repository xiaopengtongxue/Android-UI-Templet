package com.xiapeng.testwidget

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.app.FragmentPagerAdapter
import android.widget.TextView
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    var list=ArrayList<Fragment>()
    var tabs=ArrayList<String>()
    var images=ArrayList<Int>()

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
        images.add(R.drawable.selector_tab_image)
        images.add(R.drawable.selector_tab_image)
        images.add(R.drawable.selector_tab_image)
        images.add(R.drawable.selector_tab_image)
        vp.adapter=MyPagerAdapter(supportFragmentManager)
        tl.setupWithViewPager(vp)

        for (i in 0 until tabs.size) {
            //获得到对应位置的Tab
            val itemTab = tl.getTabAt(i)
            if (itemTab != null) {
                //设置自定义的标题
                itemTab!!.setCustomView(R.layout.item_tab)
                val textView = itemTab!!.getCustomView()!!.findViewById<TextView>(R.id.tabtext)
                textView.setText(tabs.get(i))
                val imageView = itemTab!!.getCustomView()!!.findViewById<ImageView>(R.id.tabicon)
                imageView.setImageResource(images[i])
            }
        }

        tl.getTabAt(0)!!.customView!!.isSelected=true
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
