package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.adgvcxz.diycode.binding.adapter.BaseRecyclerViewAdapter
import com.adgvcxz.diycode.ui.base.BaseViewModel
import java.util.*

/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */
@BindingAdapter("items")
fun <T : BaseViewModel> RecyclerView.loadData(items: ArrayList<T>?) {
    if (items != null) {
        layoutManager = LinearLayoutManager(context)
        if (adapter == null) {
            adapter = BaseRecyclerViewAdapter<T>()
        }
        (adapter as BaseRecyclerViewAdapter<T>).setList(items)
    }
}
