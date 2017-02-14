package com.adgvcxz.diycode.ui.main.home

import android.os.Parcel
import android.view.View
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.rxbus.ClickNavigation
import com.adgvcxz.diycode.rxbus.RxBus
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/12.
 */

class HomeFragmentViewModel: BaseFragmentViewModel() {


    fun onClickNavigation(view: View) = RxBus.instance.post(ClickNavigation())


    override fun contentId(): Int {
        return R.layout.fragment_home
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

}
