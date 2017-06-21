package com.adgvcxz.diycode.widget

import android.view.View
import com.adgvcxz.IModel
import com.adgvcxz.WidgetViewModel
import com.adgvcxz.diycode.R
import com.adgvcxz.recyclerviewmodel.BaseViewHolder
import com.adgvcxz.recyclerviewmodel.IDefaultView

/**
 * zhaowei
 * Created by zhaowei on 2017/6/21.
 */

class EmptyView(val height: Int): IDefaultView<EmptyViewModel> {

    override val layoutId: Int = R.layout.item_empty_view_

    override fun initView(view: View): BaseViewHolder {
        val lp = view.layoutParams
        lp.height = height
        view.layoutParams = lp
        return super.initView(view)
    }

}

class EmptyModel: IModel

class EmptyViewModel: WidgetViewModel<EmptyModel>() {

    override val initModel: EmptyModel = EmptyModel()

}
