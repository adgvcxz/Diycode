package com.adgvcxz.diycode.ui.main.home.sites

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.Site
import com.adgvcxz.diycode.binding.base.BaseViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/26.
 */

class SiteViewModel(val site: Site) : BaseViewModel() {

    override fun contentId(): Int = R.layout.item_site

}
