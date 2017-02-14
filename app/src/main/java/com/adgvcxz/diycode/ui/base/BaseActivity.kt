package com.adgvcxz.diycode.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.DiyCodeApp
import com.adgvcxz.diycode.ui.base.BaseActivityViewModel
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.di.component.ActivityComponent
import com.adgvcxz.diycode.di.component.DaggerActivityComponent
import com.adgvcxz.diycode.di.module.ActivityModule
//import com.adgvcxz.diycode.di.component.ActivityComponent
//import com.adgvcxz.diycode.di.module.ActivityModule
import com.adgvcxz.diycode.ui.base.BaseFragment
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

abstract class BaseActivity<T : BaseActivityViewModel, out B : ViewDataBinding> : AppCompatActivity() {

    val SaveStateValue = "Data"

    @Inject
    lateinit var viewModel: T

    val dataBinding: B by lazy {
        DataBindingUtil.setContentView<B>(this, viewModel.contentId())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInject()
        dataBinding.setVariable(BR.model, viewModel)
        viewModel.onCreate(this)
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy(this)
    }

    fun <T : BaseFragmentViewModel> generateFragment(t : T): BaseFragment<BaseFragmentViewModel, ViewDataBinding> {
        return BaseFragment.newInstance(t)
    }

    protected fun getActivityComponent(): ActivityComponent {
        return DaggerActivityComponent.builder()
                .appComponent(DiyCodeApp.appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }

    abstract fun initInject()

//    override fun onSaveInstanceState(outState: Bundle?) {
//        super.onSaveInstanceState(outState)
//        outState?.putParcelable(SaveStateValue, viewModel)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//        super.onRestoreInstanceState(savedInstanceState)
//        val model = savedInstanceState?.getParcelable<BaseActivityViewModel>(SaveStateValue)
//        if (model != null) {
//            viewModel.restore(model)
//        }
//    }

}
