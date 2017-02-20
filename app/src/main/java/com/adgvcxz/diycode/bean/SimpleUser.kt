package com.adgvcxz.diycode.bean

import android.databinding.BaseObservable
import com.google.gson.annotations.SerializedName

/**
 * zhaowei
 * Created by zhaowei on 2017/2/19.
 */

class SimpleUser : BaseObservable() {
    var id: Int = 0
    var login: String = ""
    var name: String = ""
    @SerializedName("avatar_url") var avatar: String = ""
}