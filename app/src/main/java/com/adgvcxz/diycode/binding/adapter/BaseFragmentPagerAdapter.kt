package com.adgvcxz.diycode.binding.adapter

import android.databinding.ObservableList
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.ui.base.TopicFragment
import com.adgvcxz.diycode.util.extensions.ensureChangeOnMainThread
import java.lang.ref.WeakReference

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

class BaseFragmentPagerAdapter<in T : BaseFragmentViewModel>(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val callback = WeakReferenceOnListChangedCallback(this)
    private var items: List<T>? = null
    private var titles: Array<String>? = null

    override fun getCount(): Int {
        return if (items == null) 0 else items!!.size
    }

    override fun getItem(position: Int): Fragment {
        return TopicFragment()
    }

    fun setItems(items: List<T>) {
        if (this.items == items) {
            return
        }
        if (this.items is ObservableList) {
            (this.items as ObservableList<T>).removeOnListChangedCallback(callback)
        }
        if (items is ObservableList) {
            items.addOnListChangedCallback(callback)
        }
        this.items = items
        notifyDataSetChanged()
    }

    fun setTitles(titles: Array<String>) {
        this.titles = titles
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles?.get(position) ?: ""
    }

    class WeakReferenceOnListChangedCallback<T : BaseFragmentViewModel>(adapter: BaseFragmentPagerAdapter<T>) : ObservableList.OnListChangedCallback<ObservableList<T>>() {

        private val adapterRef = WeakReference<BaseFragmentPagerAdapter<T>>(adapter)

        override fun onChanged(p0: ObservableList<T>?) {
            val adapter = adapterRef.get()
            if (adapter != null) {
                ensureChangeOnMainThread()
                adapter.notifyDataSetChanged()
            }
        }

        override fun onItemRangeInserted(p0: ObservableList<T>?, p1: Int, p2: Int) = onChanged(p0)

        override fun onItemRangeMoved(p0: ObservableList<T>?, p1: Int, p2: Int, p3: Int) = onChanged(p0)

        override fun onItemRangeChanged(p0: ObservableList<T>?, p1: Int, p2: Int) = onChanged(p0)

        override fun onItemRangeRemoved(p0: ObservableList<T>?, p1: Int, p2: Int) = onChanged(p0)
    }

}
