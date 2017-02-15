package com.adgvcxz.diycode.di.component

import android.app.Activity
import android.databinding.ViewDataBinding
import com.adgvcxz.diycode.ui.base.BaseFragment
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/15.
 */

interface FragmentComponent {

    fun getActivity(): Activity

    fun inject(baseFragment: BaseFragment<BaseFragmentViewModel, ViewDataBinding>)

}