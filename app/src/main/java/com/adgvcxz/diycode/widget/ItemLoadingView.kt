package com.adgvcxz.diycode.widget

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.View
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.R
import com.adgvcxz.recyclerviewmodel.BaseViewHolder
import com.adgvcxz.recyclerviewmodel.IDefaultView
import com.adgvcxz.recyclerviewmodel.LoadingItemViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/6/19.
 */
class ItemLoadingView : IDefaultView<LoadingItemViewModel> {

    override val layoutId: Int = R.layout.item_loading_new

    override fun initView(view: View): BaseViewHolder {
        DataBindingUtil.bind<ViewDataBinding>(view)
        return super.initView(view)
    }

    override fun bind(viewHolder: BaseViewHolder, viewModel: LoadingItemViewModel) {
        val binding = DataBindingUtil.getBinding<ViewDataBinding>(viewHolder.itemView)
        binding.setVariable(BR.model, viewModel.currentModel())
        binding.executePendingBindings()
    }
}