package com.adgvcxz.diycode.ui.main.drawer

import android.content.Intent
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.ui.login.LoginActivity
import com.adgvcxz.diycode.observable.ObservableString
import com.adgvcxz.diycode.ui.base.BaseFragmentViewModel

/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */

class DrawerMenuFragmentViewModel : BaseFragmentViewModel() {

    val avatar = ObservableString("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_ca79a146.png")
    var name = ObservableString("${System.currentTimeMillis()}")

    fun onClickAvatar(view: View) = view.context.startActivity(Intent(view.context, LoginActivity::class.java))

    override fun contentId(): Int = R.layout.fragment_drawer_menu

    override fun restore(model: BaseFragmentViewModel) {
        if (model is DrawerMenuFragmentViewModel) {
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
        dest?.writeString(name.get())
    }

    override fun describeContents(): Int {
        return 0
    }

}
