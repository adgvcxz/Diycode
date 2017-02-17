package com.adgvcxz.diycode.bean

import android.databinding.BaseObservable
import com.google.gson.annotations.SerializedName


/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */

class Topic : BaseObservable() {

    var id: Int = 0

    lateinit var title: String

    lateinit var createdAt: String

    lateinit var updatedAt: String

    lateinit var repliedAt: String

    var repliesCount: Int = 0

    lateinit var nodeName: String

    var nodeId: Int = 0

    var lastReplyUserId: Int? = null

    var lastReplyUserLogin: String? = null

    lateinit var user: User

    var isDeleted: Boolean = false

    var isExcellent: Boolean = false

    lateinit var abilities: Abilities


    class User : BaseObservable() {
        var id: Int = 0
        var login: String = ""
        var name: String = ""
        @SerializedName("avatar_url") var avatar: String = ""
    }

    class Abilities {
        var isUpdate: Boolean = false
        var isDestroy: Boolean = false
    }
}