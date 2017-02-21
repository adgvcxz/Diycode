package com.adgvcxz.diycode.ui.main.home.news

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.News
import com.adgvcxz.diycode.ui.base.view.BaseViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/19.
 */

class NewsViewModel(val news: News): BaseViewModel() {

    override fun contentId(): Int = R.layout.item_news



}
