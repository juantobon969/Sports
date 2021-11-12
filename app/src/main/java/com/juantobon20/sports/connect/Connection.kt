package com.juantobon20.sports.connect

import android.os.StrictMode
import com.google.gson.GsonBuilder
import com.juantobon20.sports.utils.URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class Connection {
    private fun <S> createService(serviceClass: Class<S>?): S {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS).build()
        val retrofit =
            Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .client(client).build()
        return retrofit.create(serviceClass!!)
    }

    val iApiRest = createService(IApiRest::class.java)
}