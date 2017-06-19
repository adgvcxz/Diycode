package com.adgvcxz.diycode.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.adgvcxz.AFViewModel
import com.adgvcxz.IModel
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.DiyCodeApp
import com.adgvcxz.diycode.di.component.ActivityComponent
import com.adgvcxz.diycode.di.component.DaggerActivityComponent
import com.adgvcxz.diycode.di.module.ActivityModule
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/5/4.
 */

abstract class BaseActivityNew<out B: ViewDataBinding, V: AFViewModel<M>, M: IModel>: AppCompatActivity() {

    abstract val layoutId: Int

    val binding: B by lazy {
        DataBindingUtil.setContentView<B>(this, layoutId)
    }

    @Inject
    lateinit var viewModel: V

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .appComponent(DiyCodeApp.appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInject()
        binding.setVariable(BR.model, viewModel.currentModel())
        viewModel.model.skip(1).subscribe()
        initBinding()
    }

    open fun initBinding() {

    }

    abstract fun initInject()
}