package com.adgvcxz.diycode.ui.main.home.topic

import android.databinding.ObservableArrayList
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.Topic
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import java.util.*
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

class TopicFragmentViewModel @Inject constructor(private val apiService: ApiService) : BaseFragmentViewModel() {

    val items: ObservableArrayList<TopicViewModel> = ObservableArrayList()

    override fun contentId(): Int = R.layout.fragment_topic

    override fun onCreateView() {
        super.onCreateView()
        apiService.getTopics()
                .compose(httpScheduler<List<Topic>>())
                .flatMapIterable { it }
                .collect({ ArrayList<TopicViewModel>() }, { models, topic -> models.add(TopicViewModel(topic)) })
                .toObservable()
                .subscribe{
                    items.addAll(it)
                }

    }
}
