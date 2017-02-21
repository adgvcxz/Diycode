package com.adgvcxz.diycode.util.observable

import android.databinding.ObservableField
import android.os.Parcel
import android.os.Parcelable

/**
 * zhaowei
 * Created by zhaowei on 2017/2/10.
 */

class ObservableString(str: String? = "") : ObservableField<String>(str), Parcelable {

    val CREATOR: Parcelable.Creator<ObservableString> = object : Parcelable.Creator<ObservableString> {

        override fun createFromParcel(parcel: Parcel): ObservableString = ObservableString(parcel.readString())

        override fun newArray(size: Int): Array<ObservableString?> = arrayOfNulls(size)

    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(get())
    }

    override fun describeContents(): Int {
        return 0
    }

}
