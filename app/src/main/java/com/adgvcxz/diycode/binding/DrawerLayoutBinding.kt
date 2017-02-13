package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.databinding.InverseBindingListener
import android.databinding.adapters.ListenerUtil
import android.support.v4.widget.DrawerLayout
import android.support.v4.widget.DrawerLayout.DrawerListener
import android.view.Gravity
import android.view.View
import com.adgvcxz.diycode.R
import io.reactivex.Observable

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

@BindingAdapter("drawerOpen")
fun DrawerLayout.setDrawerOpen(open: Boolean) = Observable.just(this.isDrawerOpen(Gravity.START))
        .filter { isOpen -> isOpen != open }
        .subscribe {
            if (open) {
                this.openDrawer(Gravity.START)
            } else {
                this.closeDrawer(Gravity.START)
            }
        }!!

@InverseBindingAdapter(attribute = "drawerOpen")
fun DrawerLayout.isDrawerOpen() = this.isDrawerOpen(Gravity.START)

@BindingAdapter("drawerOpenAttrChanged")
fun DrawerLayout.setDrawerListener(isOpenDrawerAttrChanged: InverseBindingListener) {

    val newListener = object : DrawerListener {
        override fun onDrawerClosed(drawerView: View?) {
        }

        override fun onDrawerStateChanged(newState: Int) {
            isOpenDrawerAttrChanged.onChange()
        }

        override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
        }

        override fun onDrawerOpened(drawerView: View?) {
            isOpenDrawerAttrChanged.onChange()
        }
    }

    val oldListener = ListenerUtil.trackListener(this, newListener, R.id.drawerLayoutListener)
    if (oldListener != null) {
        this.removeDrawerListener(oldListener)
    }
    this.addDrawerListener(newListener)
}