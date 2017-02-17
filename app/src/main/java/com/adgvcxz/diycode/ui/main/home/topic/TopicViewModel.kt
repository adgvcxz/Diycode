package com.adgvcxz.diycode.ui.main.home.topic

import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.Topic
import com.adgvcxz.diycode.ui.base.BaseViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */

class TopicViewModel(val topic: Topic): BaseViewModel() {

    val username = topic.user!!.name
    val avatar = topic.user!!.avatarUrl



    override fun contentId(): Int = R.layout.item_topic
}
