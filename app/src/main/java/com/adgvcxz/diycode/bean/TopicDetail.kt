package com.adgvcxz.diycode.bean

import com.google.gson.annotations.SerializedName
import java.util.*


/**
 * zhaowei
 * Created by zhaowei on 2017/2/27.
 */

class TopicDetail {
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
    var deleted: Boolean = false
    var excellent: Boolean = false
    lateinit var abilities: Abilities
    lateinit var body: String
    var hits: Int = 0
    var likesCount: Int = 0
    var suggestedAt: String? = null
    var followed: Boolean = false
    var liked: Boolean = false
    var favorited: Boolean = false
}