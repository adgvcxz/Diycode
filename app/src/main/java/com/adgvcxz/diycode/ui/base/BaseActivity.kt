package com.adgvcxz.diycode.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.DiyCodeApp
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.di.component.ActivityComponent
import com.adgvcxz.diycode.di.component.DaggerActivityComponent
import com.adgvcxz.diycode.di.module.ActivityModule
import com.adgvcxz.diycode.util.extensions.rx
import javax.inject.Inject


/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

abstract class BaseActivity<T : BaseActivityViewModel, out B : ViewDataBinding> : AppCompatActivity() {

    @Inject
    lateinit var viewModel: T

    val dataBinding: B by lazy {
        DataBindingUtil.setContentView<B>(this, viewModel.contentId())
    }


    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
                .appComponent(DiyCodeApp.appComponent)
                .activityModule(ActivityModule(this))
                .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initInject()
        dataBinding.setVariable(BR.model, viewModel)
        initToolbar()
        viewModel.onCreate()
    }

    private fun initToolbar() {
        val toolbar = dataBinding.root.findViewById(R.id.toolbar) as Toolbar?
        setSupportActionBar(toolbar)
        if (!TextUtils.isEmpty(viewModel.title.get())) {
            supportActionBar?.title = viewModel.title.get()
        }
        viewModel.title.rx().subscribe { supportActionBar?.title = it }
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    abstract fun initInject()

}
