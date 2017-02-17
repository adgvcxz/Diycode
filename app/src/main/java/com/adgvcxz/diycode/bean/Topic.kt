package com.adgvcxz.diycode.bean

import com.google.gson.annotations.SerializedName



/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */

class Topic {
    var id: Int = 0
    var title: String = ""
    var createdAt: String = ""
    var updatedAt: String = ""
    var repliedAt: String = ""
    var repliesCount: Int = 0
    var nodeName: String = ""
    var nodeId: Int = 0
    var lastReplyUserId: Int = 0
    var lastReplyUserLogin = ""
    var user: User? = null
    var isDeleted: Boolean = false
    var isExcellent: Boolean = false
    var abilities: Abilities? = null

    class User {
        var id: Int = 0
        var login: String = ""
        var name: String = ""
        var avatarUrl: String = ""
    }

    class Abilities {
        var isUpdate: Boolean = false
        var isDestroy: Boolean = false
    }
}