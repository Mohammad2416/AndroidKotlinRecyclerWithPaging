package com.momir.android.androidcodetest.ViewModel

import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.momir.android.androidcodetest.R
import com.momir.android.androidcodetest.network.State
import kotlinx.android.synthetic.main.item_list_footer.view.*

class ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bind(status: State?) {
        itemView.itemListFooterProgressBar.visibility = if (status == State.LOADING) VISIBLE else View.INVISIBLE
        itemView.itemListFooterTxtError.visibility = if (status == State.ERROR) VISIBLE else View.INVISIBLE
    }

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_footer, parent, false)
            view.itemListFooterTxtError.setOnClickListener { retry() }
            return ListFooterViewHolder(view)
        }
    }
}