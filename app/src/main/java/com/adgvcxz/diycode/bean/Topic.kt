package com.adgvcxz.diycode.bean

import android.databinding.BaseObservable
import java.util.*


/**
 * zhaowei
 * Created by zhaowei on 2017/2/17.
 */

class Topic : BaseObservable() {
    var id: Int = 0
    lateinit var title: String
    lateinit var createdAt: Date
    lateinit var updatedAt: Date
    var repliedAt: Date? = null
    var repliesCount: Int = 0
    lateinit var nodeName: String
    var nodeId: Int = 0
    var lastReplyUserId: Int? = null
    var lastReplyUserLogin: String? = null
    lateinit var user: SimpleUser
    var isDeleted: Boolean = false
    var isExcellent: Boolean = false
    lateinit var abilities: Abilities
}