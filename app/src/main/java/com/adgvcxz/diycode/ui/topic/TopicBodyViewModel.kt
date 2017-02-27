package com.adgvcxz.diycode.ui.topic

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.binding.base.BaseViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/27.
 */

class TopicBodyViewModel(val body: String) : BaseViewModel() {
    override fun contentId(): Int = R.layout.item_topic_body

}
