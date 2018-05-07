package com.xiapeng.testwidget

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_nav.*
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView


class NavActivity : AppCompatActivity() {
    private var fragments: Array<ItemFragment>? = null
    private var lastShowFragment = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                if (lastShowFragment != 0) {
                    switchFrament(lastShowFragment, 0)
                    lastShowFragment = 0
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                if (lastShowFragment != 1) {
                    switchFrament(lastShowFragment, 1)
                    lastShowFragment = 1
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                if (lastShowFragment != 2) {
                    switchFrament(lastShowFragment, 2)
                    lastShowFragment = 2
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_more -> {
                if (lastShowFragment != 3) {
                    switchFrament(lastShowFragment, 3)
                    lastShowFragment = 3
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        initFragments()
        val menuView = navigation.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false

            for (i in 0 until menuView.childCount) {
                val itemView = menuView.getChildAt(i) as BottomNavigationItemView
                itemView.setShiftingMode(false)  //设置false 让底部导航的动画效果始终保持 <= 3 的效果。
                itemView.setChecked(itemView.itemData.isChecked)

            }
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }

    fun switchFrament(lastIndex: Int, index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragments!![lastIndex])
        if (!fragments!![index].isAdded()) {
            transaction.add(R.id.fragment_container, fragments!![index])
        }
        transaction.show(fragments!![index]).commitAllowingStateLoss()
    }

    private fun initFragments() {
        fragments = arrayOf<ItemFragment>(ItemFragment(), ItemFragment(), ItemFragment(),ItemFragment())
        lastShowFragment = 0
        supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragments!![0])
                .show(fragments!![0])
                .commit()
    }
}
