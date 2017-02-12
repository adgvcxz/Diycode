package com.adgvcxz.diycode.fragment

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.viewmodel.BaseFragmentViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */

class BaseFragment<out T : BaseFragmentViewModel, out B : ViewDataBinding> : Fragment() {

    val Data = "Data"

    val viewModel: T by lazy {
        arguments.getParcelable<T>(Model)
    }

    val dataBinding: B by lazy {
        DataBindingUtil.inflate<B>(LayoutInflater.from(activity), viewModel.contentId(), null, false)
    }

    companion object {

        val Model = "Model"

        fun <T : BaseFragmentViewModel> newInstance(model: T): BaseFragment<T, ViewDataBinding> {
            val fragment = BaseFragment<T, ViewDataBinding>()
            val bundle = Bundle()
            bundle.putParcelable(Model, model)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val model = savedInstanceState?.getParcelable<T>(Data)
        if (model != null) {
            viewModel.restore(model)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding.setVariable(BR.model, viewModel)
        return dataBinding.root
    }
}
