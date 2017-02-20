package com.adgvcxz.diycode.ui.main.home.news

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.News
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.ui.base.RecyclerViewModel
import io.reactivex.Observable
import java.util.*
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
        listViewModel.loadData()
    }

    inner class NewsListViewModel : RecyclerViewModel<NewsViewModel>() {
        override fun request(offset: Int): Observable<ArrayList<NewsViewModel>> {
            return apiService.getNews(null)
                    .compose(httpScheduler<List<News>>())
                    .flatMapIterable { it }
                    .collect({ ArrayList<NewsViewModel>() }, { list, news -> list.add(NewsViewModel(news)) })
                    .toObservable()

        }

    }
}
