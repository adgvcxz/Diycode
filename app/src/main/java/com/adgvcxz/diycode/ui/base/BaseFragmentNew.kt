package com.adgvcxz.diycode.ui.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adgvcxz.AFViewModel
import com.adgvcxz.IModel
import com.adgvcxz.diycode.BR
import com.adgvcxz.diycode.di.component.DaggerFragmentComponent
import com.adgvcxz.diycode.di.component.FragmentComponent
import com.adgvcxz.diycode.di.module.FragmentModule
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * zhaowei
 * Created by zhaowei on 2017/5/8.
 */

abstract class BaseFragmentNew<out B : ViewDataBinding, V : AFViewModel<M>, M : IModel> : Fragment() {

    val disposables: CompositeDisposable by lazy { CompositeDisposable() }

    abstract val layoutId: Int

    @Inject
    lateinit var viewModel: V

    val binding: B by lazy {
        DataBindingUtil.inflate<B>(LayoutInflater.from(activity), layoutId, null, false)
    }

    val fragmentComponent: FragmentComponent by lazy {
        val activity = this.activity
        DaggerFragmentComponent.builder()
                .activityComponent((activity as BaseActivityNew<*, *, *>).activityComponent)
                .fragmentModule(FragmentModule(this))
                .build()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        inject()
        binding.setVariable(BR.model, viewModel.currentModel())
        viewModel.model.skip(1).subscribe()
        initBinding()
        return binding.root
    }

    open fun initBinding() {

    }

    abstract fun inject()

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.dispose()
    }

}