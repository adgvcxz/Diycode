package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.adgvcxz.diycode.binding.adapter.BaseRecyclerViewAdapter
import com.adgvcxz.diycode.ui.base.BaseViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */
@BindingAdapter("items")
fun <T : BaseViewModel> RecyclerView.loadData(items: List<T>) {
    layoutManager = LinearLayoutManager(context)
    val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
//    decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.topic_divider_line))
    addItemDecoration(decoration)
    if (adapter == null) {
        adapter = BaseRecyclerViewAdapter<T>()
    }
    (adapter as BaseRecyclerViewAdapter<T>).setList(items)
}
