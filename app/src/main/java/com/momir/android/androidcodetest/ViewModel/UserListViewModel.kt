package com.momir.android.androidcodetest.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.momir.android.androidcodetest.dataModel.User
import com.momir.android.androidcodetest.dataSource.UserDataSource
import com.momir.android.androidcodetest.dataSource.UserDataSourceFactory
import com.momir.android.androidcodetest.network.NetworkService
import com.momir.android.androidcodetest.network.State
import io.reactivex.disposables.CompositeDisposable

class UserListViewModel : ViewModel() {

    private val networkService = NetworkService.getService()
    var userList: LiveData<PagedList<User>>
    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5
    private val userDataSourceFactory: UserDataSourceFactory

    init {
        userDataSourceFactory = UserDataSourceFactory(compositeDisposable, networkService)
        val config = PagedList.Config.Builder()
                .setPageSize(pageSize)
                .setInitialLoadSizeHint(pageSize * 2)
                .setEnablePlaceholders(false)
                .build()
        userList = LivePagedListBuilder<Int, User>(userDataSourceFactory, config).build()
    }


    fun getState(): LiveData<State> = Transformations.switchMap<UserDataSource, State>(userDataSourceFactory.userDataSourceLiveData, UserDataSource::state)

    fun retry() {
        userDataSourceFactory.userDataSourceLiveData.value?.retry()
    }

    fun listIsEmpty(): Boolean {
        return userList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}