package com.momir.android.androidcodetest.network

import com.momir.android.androidcodetest.BASE_URL
import com.momir.android.androidcodetest.USERS_API
import com.momir.android.androidcodetest.dataModel.User
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    //https://jsonplaceholder.typicode.com/users?_start=0&_limit=3

    @GET(USERS_API)
    fun getUsers(@Query("_start") page: Int, @Query("_limit") pageSize: Int): Single<List<User>>

    companion object {
        fun getService(): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)
        }
    }
}