package com.adgvcxz.diycode.viewmodel

import android.os.Parcel
import android.os.Parcelable
import com.adgvcxz.diycode.R
import com.adgvcxz.diycode.observable.ObservableString

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class MainActivityViewModel : BaseActivityViewModel() {

    val text = ObservableString("${System.currentTimeMillis()}")

    override fun contentId(): Int {
        return R.layout.activity_main
    }

    override fun restore(model: BaseActivityViewModel) {
        if (model is MainActivityViewModel) {
            text.set(model.text.get())
        }
    }

    val CREATOR: Parcelable.Creator<MainActivityViewModel> = object : Parcelable.Creator<MainActivityViewModel> {

        override fun createFromParcel(parcel: Parcel): MainActivityViewModel {
            val model = MainActivityViewModel()
            model.text.set(parcel.readString())
            return model
        }

        override fun newArray(size: Int): Array<MainActivityViewModel?> = arrayOfNulls(size)
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(text.get())
    }

    override fun describeContents(): Int {
        return 0
    }

}
