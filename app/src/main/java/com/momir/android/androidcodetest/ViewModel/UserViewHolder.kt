package com.momir.android.androidcodetest.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.momir.android.androidcodetest.R
import com.momir.android.androidcodetest.dataModel.User
import com.momir.android.androidcodetest.interfaces.OnItemClickListener
import kotlinx.android.synthetic.main.row_uses_api_recyclerview.view.*

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(
        user: User?,
        listener: OnItemClickListener) {
        if (user != null) {
            itemView.row_users_api_recycler_name.text = user.name
            itemView.row_users_api_recycler_username.text = user.username
            itemView.row_users_api_recycler_email.text = user.email

            itemView.setOnClickListener {  listener.onItemClicked(user)  }

        }

    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_uses_api_recyclerview, parent, false)
            return UserViewHolder(view)
        }
    }
}