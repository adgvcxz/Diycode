package com.adgvcxz.diycode.bean

import com.google.gson.annotations.SerializedName


/**
 * zhaowei
 * Created by zhaowei on 2017/2/26.
 */

class Site {
    var name: String = ""
    var url: String = ""
    @SerializedName("avatar_url") var avatar: String = ""
}