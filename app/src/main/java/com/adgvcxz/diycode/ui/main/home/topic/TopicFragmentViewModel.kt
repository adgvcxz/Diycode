package com.adgvcxz.diycode.ui.main.home.topic

import android.app.Activity
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.Topic
import com.adgvcxz.diycode.binding.recycler.RefreshRecyclerViewModel
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.util.extensions.getActionBarHeight
import com.adgvcxz.diycode.util.extensions.getContext
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

class TopicFragmentViewModel @Inject constructor(private val apiService: ApiService) : BaseFragmentViewModel() {

    @Inject
    lateinit var activity: Activity

    val listViewModel = TopicsViewModel()

    override fun contentId(): Int = R.layout.fragment_topic

    override fun onCreateView() {
        super.onCreateView()
        listViewModel.refresh.set(true)
    }

    inner class TopicsViewModel : RefreshRecyclerViewModel<TopicViewModel>() {

        init {
            loadMore.set(true)
            loadAll.set(false)
            topMargin.set(getContext().getActionBarHeight() * 2)
        }

        override fun request(offset: Int): Observable<ArrayList<TopicViewModel>> {
            return apiService.getTopics(offset = offset)
                    .compose(httpScheduler<List<Topic>>())
                    .flatMapIterable { it }
                    .collect({ ArrayList<TopicViewModel>() }, { list, bean ->
                        list.add(TopicViewModel(bean))
                    })
                    .toObservable()
        }
    }
}
