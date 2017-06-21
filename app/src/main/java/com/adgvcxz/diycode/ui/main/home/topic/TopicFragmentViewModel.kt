package com.adgvcxz.diycode.ui.main.home.topic

import com.adgvcxz.AFViewModel
import com.adgvcxz.IModel
import com.adgvcxz.IMutation
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.TopMarginRecyclerViewModel
import com.adgvcxz.diycode.util.extensions.actionBarHeight
import com.adgvcxz.diycode.util.extensions.app
import com.adgvcxz.recyclerviewmodel.ListResult
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/16.
 */

class TopicFragmentViewModel @Inject constructor(private val apiService: ApiService) :
        AFViewModel<TopicFragmentViewModel.TopicFragmentModel>() {

    override val initModel: TopicFragmentModel = TopicFragmentModel()

    class TopicFragmentModel : IModel {
        val topMargin: Int = app.actionBarHeight * 2
    }

    val listViewModel = TopicsViewModel()

    inner class TopicsViewModel : TopMarginRecyclerViewModel() {

        override val initModel: Model = Model(null, true, true)

        override fun request(refresh: Boolean): Observable<ListResult> {
            return Observable.just(refresh)
                    .map {
                        if (refresh) 0
                        else currentModel().items.size - 2
                    }
                    .flatMap {
                        apiService.getTopics(it)
                                .map { ListResult(it.map(::TopicViewModel)) }
                                .onErrorReturn { ListResult(null) }
                    }
                    .subscribeOn(Schedulers.io())
        }

        override fun scan(model: Model, mutation: IMutation): Model {
            return super.scan(model, mutation)
        }
    }
}
