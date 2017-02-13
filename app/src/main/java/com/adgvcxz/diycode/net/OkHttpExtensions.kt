package com.adgvcxz.diycode.net

import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

/**
 * zhaowei
 * Created by zhaowei on 2017/2/13.
 */

fun OkHttpClient.Builder.initX509() {
    try {
        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<out X509Certificate> {
                return arrayOf()
            }

            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
            }
        })
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())
        sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun OkHttpClient.Builder.initTimeout() {
    connectTimeout(3, TimeUnit.SECONDS)
    readTimeout(3, TimeUnit.SECONDS)
    writeTimeout(3, TimeUnit.SECONDS)
}

fun OkHttpClient.Builder.setRetry() = retryOnConnectionFailure(false)!!
