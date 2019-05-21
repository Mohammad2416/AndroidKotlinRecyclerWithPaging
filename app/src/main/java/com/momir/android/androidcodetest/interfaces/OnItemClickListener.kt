package com.momir.android.androidcodetest.interfaces

import com.momir.android.androidcodetest.dataModel.User

interface OnItemClickListener {
    fun onItemClicked(user: User)
}