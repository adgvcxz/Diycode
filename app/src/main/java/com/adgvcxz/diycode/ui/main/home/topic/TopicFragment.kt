package com.adgvcxz.diycode.ui.main.home.topic

import android.support.v7.widget.LinearLayoutManager
import com.adgvcxz.addTo
import com.adgvcxz.bindTo
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.databinding.FragmentTopicBinding
import com.adgvcxz.diycode.ui.base.BaseFragmentNew
import com.adgvcxz.diycode.util.extensions.app
import com.adgvcxz.diycode.widget.EmptyView
import com.adgvcxz.diycode.widget.EmptyViewModel
import com.adgvcxz.diycode.widget.ItemLoadingView
import com.adgvcxz.recyclerviewmodel.RecyclerAdapter
import com.adgvcxz.recyclerviewmodel.RecyclerViewModel
import com.adgvcxz.recyclerviewmodel.itemClicks
import com.jakewharton.rxbinding2.support.v4.widget.refreshes
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
        val adapter = RecyclerAdapter(viewModel.listViewModel) {
            when(it) {
                is EmptyViewModel -> EmptyView(viewModel.currentModel().topMargin)
                is TopicViewModel -> TopicView()
                else -> ItemLoadingView()
            }
        }

        binding.recyclerView.let {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = adapter
        }

        viewModel.listViewModel.model
                .map { it.isRefresh }
                .filter { it != binding.swipeRefreshLayout.isRefreshing }
                .subscribe(binding.swipeRefreshLayout.refreshing())
                .addTo(disposables)

        binding.swipeRefreshLayout.refreshes()
                .map { RecyclerViewModel.Event.refresh }
                .bindTo(viewModel.listViewModel.action)
                .addTo(disposables)

        adapter.itemClicks()
                .filter { it == adapter.itemCount - 1 }
                .map { RecyclerViewModel.Event.loadMore }
                .bindTo(viewModel.listViewModel.action)
                .addTo(disposables)

        viewModel.listViewModel.action.onNext(RecyclerViewModel.Event.refresh)
    }
}