package com.adgvcxz.diycode.ui.main.home.topic

import android.content.Intent
import android.view.View
import com.adgvcxz.diycode.Config
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.Topic
import com.adgvcxz.diycode.binding.base.BaseViewModel
import com.adgvcxz.diycode.ui.topic.TopicDetailActivity

/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */

class TopicViewModel(val topic: Topic) : BaseViewModel() {

    override fun contentId(): Int = R.layout.item_topic

    fun click(view: View) {
        val intent = Intent(view.context, TopicDetailActivity::class.java)
        intent.putExtra(Config.Extra, topic.id)
        view.context.startActivity(intent)
    }
}
