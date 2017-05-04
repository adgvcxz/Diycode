package com.adgvcxz.diycode.ui.main.home.sites

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.recycler.RefreshRecyclerViewModel
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.util.extensions.actionBarHeight
import com.adgvcxz.diycode.util.extensions.app
import io.reactivex.Observable
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/26.
 */

class SitesFragmentViewModel @Inject constructor(private val apiService: ApiService) : BaseFragmentViewModel() {


    val listViewModel = SitesViewModel()

    override fun onCreateView() {
        super.onCreateView()
        listViewModel.refresh.set(true)
    }

    override fun contentId(): Int = R.layout.fragment_sites

    inner class SitesViewModel : RefreshRecyclerViewModel<BaseViewModel>() {

        init {
            topMargin.set(app.actionBarHeight * 2)
        }

        override fun request(offset: Int): Observable<List<BaseViewModel>> {
            return apiService.getSites().flatMapIterable { it }
                    .flatMap {
                        val list: List<BaseViewModel> = ArrayList()
                        (list as ArrayList<BaseViewModel>).add(SiteTitleViewModel(it.name))
                        it.sites.mapTo(list, ::SiteViewModel)
                        Observable.fromArray(list)
                    }
                    .flatMapIterable { it }
                    .toList()
                    .toObservable()
                    .compose(httpScheduler<List<BaseViewModel>>())
        }

        override fun createLayoutManager(recyclerView: RecyclerView): RecyclerView.LayoutManager {
            val layoutManager = GridLayoutManager(recyclerView.context, 2)
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position >= 1 && position <= items.size) {
                        if (items[position - 1] is SiteTitleViewModel) {
                            return 2
                        }
                    }
                    return 1
                }
            }
            return layoutManager
        }
    }
}
