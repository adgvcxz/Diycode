package com.adgvcxz.diycode.net

import com.adgvcxz.diycode.bean.Token
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

interface ApiService {

    @POST("https://www.diycode.cc/oauth/token")
    @FormUrlEncoded
    fun getToken(@Field("client_id") clientId: String, @Field("client_secret") clientSecret: String,
                 @Field("grant_type") grantType: String, @Field("username") username: String,
                 @Field("password") password: String): Observable<Token>
}

