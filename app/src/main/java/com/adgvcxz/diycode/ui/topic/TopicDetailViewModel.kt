package com.adgvcxz.diycode.ui.topic

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.recycler.RefreshRecyclerViewModel
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.BaseActivityViewModel
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/27.
 */

class TopicDetailViewModel @Inject constructor(private val apiService: ApiService) : BaseActivityViewModel() {

    val listViewModel = TopicDetailListViewModel()

    var id: Int = 0
        set(value) {
            field = value
            listViewModel.refresh.set(true)
        }

    init {
        title.set("Topic")
    }

    override fun contentId(): Int = R.layout.activity_topic_detail

    inner class TopicDetailListViewModel : RefreshRecyclerViewModel<BaseViewModel>() {

        init {
            loadAll.set(false)
            loadMore.set(true)
        }

        override fun request(offset: Int): Observable<ArrayList<BaseViewModel>> {
            if (offset == 0) {
                return apiService.getTopicDetail(id)
                        .flatMap { Observable.just(ArrayList<BaseViewModel>(Arrays.asList(TopicBodyViewModel(it.body)))) }
                        .compose(httpScheduler<ArrayList<BaseViewModel>>())
            } else {
                return apiService.getTopicReplies(id = id, offset = offset - 1)
                        .flatMapIterable { it }
                        .collect({ ArrayList<BaseViewModel>() }, { list, reply ->
                            list.add(TopicReplyViewModel(reply))
                        }).toObservable()
                        .compose(httpScheduler<ArrayList<BaseViewModel>>())
            }

        }

        override fun updateLoadAll(it: ArrayList<BaseViewModel>) {
            loadAll.set(offset > 0 && it.size < ApiService.Limit)
        }
    }
}