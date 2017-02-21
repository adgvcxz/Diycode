package com.adgvcxz.diycode.ui.main.home.topic

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.Topic
import com.adgvcxz.diycode.ui.base.view.BaseViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */

class TopicViewModel(val topic: Topic): BaseViewModel() {

    override fun contentId(): Int = R.layout.item_topic

    fun click() {
    }
}
