package com.adgvcxz.diycode.ui.topic

import android.os.Bundle
import com.adgvcxz.diycode.Config
import com.adgvcxz.diycode.databinding.ActivityTopicDetailBinding
import com.adgvcxz.diycode.ui.base.BaseActivity

class TopicDetailActivity : BaseActivity<TopicDetailViewModel, ActivityTopicDetailBinding>() {

    override fun initInject() {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = intent.getIntExtra(Config.Extra, 0)
        viewModel.id = id
    }
}
