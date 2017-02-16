package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.support.v4.view.ViewPager
import com.adgvcxz.diycode.binding.adapter.BaseFragmentPagerAdapter
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */


@BindingAdapter(value = *arrayOf("items", "adapter", "titles"))
fun <T : BaseFragmentViewModel> ViewPager.loadData(items: List<T>, adapter: BaseFragmentPagerAdapter<T>, titles: Array<String>) {
    val oldAdapter = getAdapter()
    adapter.setItems(items)
    if (oldAdapter == null) {
        adapter.setTitles(titles)
        setAdapter(adapter)
    }
}

//@BindingAdapter(value = *arrayOf("items"))
//fun ViewPager.loadData(items: List<TopicFragmentViewModel>) {
//    Log.e("zhaow", "========")
//}