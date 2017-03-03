package com.adgvcxz.diycode.ui.main.home.sites

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.binding.recycler.RefreshRecyclerViewModel
import com.adgvcxz.diycode.net.ApiService
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import com.adgvcxz.diycode.util.extensions.getActionBarHeight
import com.adgvcxz.diycode.util.extensions.getContext
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
            topMargin.set(getContext().getActionBarHeight() * 2)
        }

        override fun request(offset: Int): Observable<List<BaseViewModel>> {
            return apiService.getSites().flatMapIterable { it }
                    .flatMap {
                        val list: List<BaseViewModel> = ArrayList()
                        (list as ArrayList<BaseViewModel>).add(SiteTitleViewModel(it.name))
                        val sites = it.sites
                        if (sites.size % 2 == 0) {
                            (0..(sites.size - 1) step 2).mapTo(list) { SiteViewModel(sites[it], sites[it + 1]) }
                        } else {
                            (0..(sites.size - 2) step 2).mapTo(list) { SiteViewModel(sites[it], sites[it + 1]) }
                            list.add(SiteViewModel(it.sites[it.sites.size - 1], null))
                        }
                        Observable.fromArray(list)
                    }
                    .flatMapIterable { it }
                    .toList()
                    .toObservable()
                    .compose(httpScheduler<List<BaseViewModel>>())
        }
    }
}
