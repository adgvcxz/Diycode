package com.adgvcxz.diycode.viewmodel

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.observable.ObservableString

/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */

class DrawerMenuFragmentViewModel : BaseFragmentViewModel() {

    val avatar = ObservableString("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png")
    var name = ObservableString("${System.currentTimeMillis()}")

    override fun contentId(): Int {
        return R.layout.fragment_drawer_menu
    }

    override fun restore(model: BaseFragmentViewModel) {
        if (model is DrawerMenuFragmentViewModel) {
            Log.e("zhaow", model.name.get())
            name.set(model.name.get())
        }
    }

    companion object {
        val CREATOR: Parcelable.Creator<DrawerMenuFragmentViewModel> = object : Parcelable.Creator<DrawerMenuFragmentViewModel> {

            override fun createFromParcel(parcel: Parcel): DrawerMenuFragmentViewModel {
                val model = DrawerMenuFragmentViewModel()
                model.name.set(parcel.readString())
                return model
            }

            override fun newArray(size: Int): Array<DrawerMenuFragmentViewModel?> = arrayOfNulls(size)
        }
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
//        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        dest?.writeString(name.get())
    }

    override fun describeContents(): Int {
//        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
        return 0
    }

}
