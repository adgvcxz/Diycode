package com.adgvcxz.diycode.net

import com.adgvcxz.diycode.Config
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

class RetrofitHelper private constructor(){

    private val apiService: ApiService
    private object Inner {
        val single = RetrofitHelper()
    }

    companion object {
        fun getInstance(): ApiService = Inner.single.apiService
    }

    init {
        val builder = OkHttpClient.Builder()
        builder.initTimeout()
        builder.setRetry()
        builder.initX509()
        if (Config.Debug) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            builder.addInterceptor(loggingInterceptor)
        }
        val gson = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()
        val retrofit = Retrofit.Builder().baseUrl("http://diycode.cc/api/v3")
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }


}
