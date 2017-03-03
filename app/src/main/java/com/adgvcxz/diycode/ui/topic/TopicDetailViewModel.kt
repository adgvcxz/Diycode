package com.adgvcxz.diycode.ui.topic

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.recycler.RefreshRecyclerViewModel
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.BaseActivityViewModel
import com.adgvcxz.diycode.util.extensions.formatList
import com.adgvcxz.diycode.util.extensions.singleToList
import com.adgvcxz.diycode.util.extensions.string
import io.reactivex.Observable
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
        title.set(R.string.topic.string())
        backArrow.set(true)
    }

    override fun contentId(): Int = R.layout.activity_topic_detail

    inner class TopicDetailListViewModel : RefreshRecyclerViewModel<BaseViewModel>() {

        init {
            loadAll.set(false)
            loadMore.set(true)
        }

        override fun request(offset: Int): Observable<List<BaseViewModel>> {
            if (offset == 0) {
                return apiService.getTopicDetail(id)
                        .singleToList { TopicBodyViewModel(it.body) }
                        .compose(httpScheduler<List<BaseViewModel>>())
            } else {
                return apiService.getTopicReplies(id = id, offset = offset - 1)
                        .formatList(::TopicReplyViewModel)
                        .compose(httpScheduler<List<BaseViewModel>>())
            }

        }

        override fun updateLoadAll(it: List<BaseViewModel>) {
            loadAll.set(offset > 0 && it.size < ApiService.Limit)
        }
    }
}