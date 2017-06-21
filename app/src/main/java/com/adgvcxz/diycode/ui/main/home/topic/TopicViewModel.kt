package com.adgvcxz.diycode.ui.main.home.topic

import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.databinding.ViewDataBinding
import android.util.Log
import android.view.View
import com.adgvcxz.IModel
import com.adgvcxz.WidgetViewModel
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.Config
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.bean.Topic
import com.adgvcxz.diycode.binding.observable.ObservableString
import com.adgvcxz.diycode.ui.topic.TopicDetailActivity
import com.adgvcxz.recyclerviewmodel.BaseViewHolder
import com.adgvcxz.recyclerviewmodel.IDefaultView
import com.adgvcxz.recyclerviewmodel.IView
import java.util.*

/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */

class TopicView: IDefaultView<TopicViewModel> {

    override val layoutId: Int = R.layout.item_topic

    override fun initView(view: View): BaseViewHolder {
        DataBindingUtil.bind<ViewDataBinding>(view)
        return super.initView(view)
    }

    override fun bind(viewHolder: BaseViewHolder, viewModel: TopicViewModel) {
        val binding = DataBindingUtil.getBinding<ViewDataBinding>(viewHolder.itemView)
        binding.setVariable(BR.model, viewModel.currentModel())
        binding.executePendingBindings()
    }
}

class TopicViewModel(val topic: Topic) : WidgetViewModel<TopicViewModel.TopicModel>() {

    override val initModel: TopicModel = TopicModel(topic)

    class TopicModel(topic: Topic): IModel {
        val avatar: ObservableString = ObservableString(topic.user.avatar)
        val title: ObservableString = ObservableString(topic.title)
        val username: ObservableString = ObservableString(topic.user.login)
        val date: ObservableField<Date> = ObservableField(topic.updatedAt)
    }


    fun click(view: View) {
        val intent = Intent(view.context, TopicDetailActivity::class.java)
        intent.putExtra(Config.Extra, topic.id)
        view.context.startActivity(intent)
    }
}
