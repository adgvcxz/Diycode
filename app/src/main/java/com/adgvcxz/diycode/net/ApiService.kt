package com.adgvcxz.diycode.net

import com.adgvcxz.diycode.bean.*
import io.reactivex.Observable
import retrofit2.http.*


/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

interface ApiService {

    companion object {
        val Limit = 10
    }

    @POST("https://www.diycode.cc/oauth/token")
    @FormUrlEncoded
    fun getToken(@Field("client_id") clientId: String, @Field("client_secret") clientSecret: String,
                 @Field("grant_type") grantType: String, @Field("username") username: String,
                 @Field("password") password: String): Observable<Token>

    @GET("topics.json")
    fun getTopics(@Query("offset") offset: Int = 0, @Query("limit") limit: Int = Limit): Observable<List<Topic>>

    @GET("news.json")
    fun getNews(@Query("node_id") nodeId: Int?,
                @Query("offset") offset: Int = 0, @Query("limit") limit: Int = Limit): Observable<List<News>>

    @GET("sites.json")
    fun getSites(): Observable<List<SiteBean>>

    @GET("topics/{id}.json")
    fun getTopicDetail(@Path("id") id: Int): Observable<TopicDetail>

}

