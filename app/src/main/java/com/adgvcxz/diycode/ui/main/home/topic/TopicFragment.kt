package com.adgvcxz.diycode.ui.main.home.topic

import com.adgvcxz.addTo
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.databinding.FragmentTopicBinding
import com.adgvcxz.diycode.ui.base.BaseFragmentNew
import com.jakewharton.rxbinding2.support.v4.widget.refreshing

/**
 * zhaowei
 * Created by zhaowei on 2017/6/9.
 */

class TopicFragment : BaseFragmentNew<FragmentTopicBinding, TopicFragmentViewModel, TopicFragmentViewModel.TopicFragmentModel>() {

    override val layoutId: Int = R.layout.fragment_topic

    override fun inject() {
        fragmentComponent.inject(this)
    }

    override fun initBinding() {
        viewModel.listViewModel.model
                .map { it.isRefresh }
                .distinctUntilChanged()
                .subscribe(binding.swipeRefreshLayout.refreshing())
                .addTo(disposables)
    }
}