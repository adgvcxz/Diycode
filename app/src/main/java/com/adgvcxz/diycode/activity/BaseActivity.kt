package com.adgvcxz.diycode.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.viewmodel.BaseActivityViewModel
import com.adgvcxz.diycode.R

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

abstract class BaseActivity<out T : BaseActivityViewModel, out B : ViewDataBinding> : AppCompatActivity() {

    val SaveStateValue = "Data"

    val viewModel: T by lazy {
        generateViewModel()
    }

    val dataBinding: B by lazy {
        DataBindingUtil.setContentView<B>(this, viewModel.contentId())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.setVariable(BR.model, viewModel)
        val toolbar: Toolbar? = dataBinding.root.findViewById(R.id.toolbar) as Toolbar?
        if (toolbar != null) {
            setSupportActionBar(toolbar)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putParcelable(SaveStateValue, viewModel)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        val model = savedInstanceState?.getParcelable<BaseActivityViewModel>(SaveStateValue)
        if (model != null) {
            viewModel.restore(model)
        }
    }

    abstract fun generateViewModel(): T
}
