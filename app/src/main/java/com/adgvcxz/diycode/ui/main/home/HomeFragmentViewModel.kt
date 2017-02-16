package com.adgvcxz.diycode.ui.main.home

import android.databinding.ObservableArrayList
import android.support.v4.app.Fragment
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.adapter.BaseFragmentPagerAdapter
import com.adgvcxz.diycode.rxbus.OpenDrawer
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/12.
 */

class HomeFragmentViewModel @Inject constructor(private val fragment: Fragment, private val rxBus: RxBus) : BaseFragmentViewModel() {

    val items = ObservableArrayList<TopicFragmentViewModel>()
    var adapter: BaseFragmentPagerAdapter<TopicFragmentViewModel> = BaseFragmentPagerAdapter(fm = fragment.childFragmentManager)
    val titles = arrayOf("abcd", "abcd", "abcd")

    init {
        items.add(TopicFragmentViewModel())
        items.add(TopicFragmentViewModel())
        items.add(TopicFragmentViewModel())
    }

    override fun onCreateView() {
        super.onCreateView()
        if (fragment is HomeFragment) {
            fragment.log()
        }
    }

    fun openDrawer() = rxBus.post(OpenDrawer())

    override fun contentId(): Int = R.layout.fragment_home

}
