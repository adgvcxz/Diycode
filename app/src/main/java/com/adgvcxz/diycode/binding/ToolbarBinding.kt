package com.adgvcxz.diycode.binding

import android.databinding.BindingMethod
import android.databinding.BindingMethods
import android.support.v7.widget.Toolbar

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

@BindingMethods(
        BindingMethod(type = Toolbar::class, attribute = "onNavigationClick", method = "setOnNavigationClickListener")
)

object ToolbarBinding