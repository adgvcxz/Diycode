package com.adgvcxz.diycode.ui.main.home.sites

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.SiteBean
import com.adgvcxz.diycode.binding.base.BaseViewModel
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

        override fun request(offset: Int): Observable<ArrayList<BaseViewModel>> {
            return apiService.getSites()
                    .compose(httpScheduler<List<SiteBean>>())
                    .flatMapIterable { it }
                    .collect({ ArrayList<BaseViewModel>() }, { list, bean ->
                        list.add(SiteTitleViewModel(bean.name))
                        if (bean.sites.size % 2 == 0) {
                            (0..(bean.sites.size - 1) step 2).mapTo(list) { SiteViewModel(bean.sites[it], bean.sites[it + 1]) }
                        } else {
                            (0..(bean.sites.size - 2) step 2).mapTo(list) { SiteViewModel(bean.sites[it], bean.sites[it + 1]) }
                            list.add(SiteViewModel(bean.sites[bean.sites.size - 1], null))
                        }
                    }).toObservable()
        }
    }
}
