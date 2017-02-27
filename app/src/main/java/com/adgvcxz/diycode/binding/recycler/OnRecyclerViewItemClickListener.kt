package com.adgvcxz.diycode.binding.recycler

import com.adgvcxz.diycode.binding.base.BaseViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/27.
 */

interface OnRecyclerViewItemClickListener<in T: BaseViewModel> {
    fun OnClick(t: T)
}