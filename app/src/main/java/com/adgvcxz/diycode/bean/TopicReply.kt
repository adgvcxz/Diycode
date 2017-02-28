package com.adgvcxz.diycode.bean

import com.google.gson.annotations.SerializedName
import java.util.*


/**
 * zhaowei
 * Created by zhaowei on 2017/2/27.
 */
class TopicReply {
    var id: Int = 0
    @SerializedName("body_html") lateinit var body: String
    lateinit var createdAt: Date
    lateinit var updatedAt: Date
    var deleted: Boolean = false
    var topicId: Int = 0
    lateinit var user: SimpleUser
    var likesCount: Int = 0
    lateinit var abilities: Abilities
}