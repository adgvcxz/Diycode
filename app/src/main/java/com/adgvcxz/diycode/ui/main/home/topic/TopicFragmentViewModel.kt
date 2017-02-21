package com.adgvcxz.diycode.ui.main.home.topic

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.Topic
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.ui.base.view.RecyclerViewModel
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

class TopicFragmentViewModel @Inject constructor(private val apiService: ApiService) : BaseFragmentViewModel() {

    val listViewModel = TopicsViewModel()

    override fun contentId(): Int = R.layout.fragment_topic

    override fun onCreateView() {
        super.onCreateView()
        listViewModel.loadData()
    }

    inner class TopicsViewModel : RecyclerViewModel<TopicViewModel>() {
        override fun request(offset: Int): Observable<ArrayList<TopicViewModel>> {
            return apiService.getTopics()
                    .compose(httpScheduler<List<Topic>>())
                    .flatMapIterable { it }
                    .collect({ ArrayList<TopicViewModel>() }, { list, bean -> list.add(TopicViewModel(bean)) })
                    .toObservable()
        }
    }
}
