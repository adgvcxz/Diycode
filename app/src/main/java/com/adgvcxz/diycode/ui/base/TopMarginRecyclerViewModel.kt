package com.adgvcxz.diycode.ui.base

import com.adgvcxz.IMutation
import com.adgvcxz.diycode.widget.EmptyViewModel
import com.adgvcxz.recyclerviewmodel.RecyclerViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/6/21.
 */

abstract class TopMarginRecyclerViewModel: RecyclerViewModel() {
    override fun scan(model: Model, mutation: IMutation): Model {
        val result = super.scan(model, mutation)
        if (result.items.isNotEmpty() && mutation is DataMutation.SetData) {
            result.items = arrayListOf(EmptyViewModel()) + result.items
        }
        return result
    }
}
