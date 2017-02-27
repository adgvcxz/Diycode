package com.adgvcxz.diycode.bean

import java.util.*


/**
 * zhaowei
 * Created by zhaowei on 2017/2/19.
 */

class News {
    var id: Int = 0
    lateinit var title: String
    lateinit var createdAt: Date
    lateinit var updatedAt: Date
    lateinit var user: SimpleUser
    lateinit var nodeName: String
    var nodeId: Int = 0
    var lastReplyUserId: Int? = null
    var lastReplyUserLogin: String? = null
    var repliedAt: Date? = null
    var address: String? = null
    var repliesCount: Int = 0
}