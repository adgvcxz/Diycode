package com.adgvcxz.diycode.binding.recycler

import android.support.v7.widget.RecyclerView

/**
 * zhaowei
 * Created by zhaowei on 2017/3/6.
 */

interface LayoutManagerFactory {
    fun createLayoutManager(recyclerView: RecyclerView): RecyclerView.LayoutManager
}
