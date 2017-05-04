package com.adgvcxz.diycode.ui.main.home.news

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.News
import com.adgvcxz.diycode.binding.recycler.RefreshRecyclerViewModel
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.util.extensions.actionBarHeight
import com.adgvcxz.diycode.util.extensions.app
import com.adgvcxz.diycode.util.extensions.formatList
import io.reactivex.Observable
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/19.
 */

class NewsFragmentViewModel @Inject constructor(private val apiService: ApiService) : BaseFragmentViewModel() {

    val listViewModel = NewsListViewModel()

    override fun contentId(): Int = R.layout.fragment_news

    override fun onCreateView() {
        super.onCreateView()
        listViewModel.refresh.set(true)
    }

    inner class NewsListViewModel : RefreshRecyclerViewModel<NewsViewModel>() {

        init {
            loadMore.set(true)
            loadAll.set(false)
            topMargin.set(app.actionBarHeight * 2)
        }

        override fun request(offset: Int): Observable<List<NewsViewModel>> {
            return apiService.getNews(nodeId = null, offset = offset)
                    .compose(httpScheduler<List<News>>())
                    .formatList(::NewsViewModel)
        }
    }
}
