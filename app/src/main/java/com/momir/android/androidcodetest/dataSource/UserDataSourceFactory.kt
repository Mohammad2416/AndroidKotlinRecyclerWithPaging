package com.momir.android.androidcodetest.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.momir.android.androidcodetest.dataModel.User
import com.momir.android.androidcodetest.network.NetworkService
import io.reactivex.disposables.CompositeDisposable

class UserDataSourceFactory(
        private val compositeDisposable: CompositeDisposable,
        private val networkService: NetworkService
        ): DataSource.Factory<Int, User>() {

    val userDataSourceLiveData = MutableLiveData<UserDataSource>()

    override fun create(): DataSource<Int, User> {
        val userDataSource = UserDataSource(networkService, compositeDisposable)
        userDataSourceLiveData.postValue(userDataSource)
        return userDataSource
    }
}