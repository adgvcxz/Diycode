package com.adgvcxz.diycode.bean


/**
 * zhaowei
 * Created by zhaowei on 2017/2/19.
 */

class News {
    var id: Int = 0
    lateinit var title: String
    lateinit var createdAt: String
    lateinit var updatedAt: String
    lateinit var user: SimpleUser
    lateinit var nodeName: String
    var nodeId: Int = 0
    var lastReplyUserId: Any? = null
    var lastReplyUserLogin: Any? = null
    var repliedAt: Any? = null
    var address: String? = null
    var repliesCount: Int = 0
}