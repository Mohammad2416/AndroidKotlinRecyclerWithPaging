package com.momir.android.androidcodetest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.momir.android.androidcodetest.ViewModel.UserListViewModel
import com.momir.android.androidcodetest.ViewModel.UserViewHolder
import com.momir.android.androidcodetest.adapters.UserListAdapter
import com.momir.android.androidcodetest.dataModel.User
import com.momir.android.androidcodetest.interfaces.OnItemClickListener
import com.momir.android.androidcodetest.network.State
import kotlinx.android.synthetic.main.activity_list_of_users.*

/**
 * Android JetPack and Architecture Component
 * Paging Library, Retrofit Library, LiveData and RxJava 2
 * */

class ListOfUsersActivity : AppCompatActivity() {


    private lateinit var viewModel: UserListViewModel
    private lateinit var userListAdapter: UserListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_of_users)


        viewModel = ViewModelProviders.of(this).get(UserListViewModel::class.java)

        initAdapter()

        initState()

    }


    private fun initAdapter() {

        userListAdapter = UserListAdapter(object: OnItemClickListener{
            override fun onItemClicked(user: User) {

                intent = Intent(baseContext, MapActivity::class.java)
                intent.putExtra("GEO_LAT", user.address.geo.lat)
                intent.putExtra("GEO_LNG", user.address.geo.lng)
                startActivity(intent)

            }
        }){
            viewModel.retry()
        }


        recyclerListOfUsers.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false) as RecyclerView.LayoutManager?
        recyclerListOfUsers.adapter = userListAdapter
        viewModel.userList.observe(this, Observer {
            userListAdapter.submitList(it)
        })

    }



    private fun initState() {
        listOfUserTxtError.setOnClickListener { viewModel.retry() }
        viewModel.getState().observe(this, Observer { state ->

            listOfUserProgressBar.visibility = if (viewModel.listIsEmpty() && state == State.LOADING) View.VISIBLE else View.GONE

            listOfUserTxtError.visibility = if (viewModel.listIsEmpty() && state == State.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                userListAdapter.setState(state ?: State.DONE)
            }
        })
    }


}
