package com.example.rxjavaaexample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.rxjavaaexample.adapter.UserListAdapter
import com.example.rxjavaaexample.data.model.UserListResponse.UserListResponse
import com.example.rxjavaaexample.data.repository.Repository
import com.example.rxjavaaexample.databinding.ActivityMainBinding
import com.example.rxjavaaexample.ui.viewModel.AppViewModel
import com.example.rxjavaaexample.ui.viewModel.ViewModelFactory
import com.example.rxjavaaexample.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var userListAdapter: UserListAdapter
    @Inject
    lateinit var repository: Repository
    private val appViewModel: AppViewModel by viewModels {
        ViewModelFactory(repository)
    }
    private var userList = ArrayList<UserListResponse>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appViewModel.getUserList()
        initObserver()
    }

    private fun initObserver() {
        appViewModel.userListResponse.observe(this) { Response ->
            Response?.let {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        binding.loading.visibility = View.VISIBLE
                    }
                    Resource.Status.SUCCESS -> {
                        binding.loading.visibility = View.GONE
                        userList.clear()
                        userList.addAll(Response.data!!)
                        setupUI()
                    }
                    Resource.Status.ERROR -> {
                        binding.loading.visibility = View.GONE
                        Toast.makeText(this, Response.message.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
//        appViewModel.userListLoadingError.observe(this
//        ) { dataError ->
//            dataError?.let {
//                Log.e("Random Dish API Error", dataError)
//                Toast.makeText(this,dataError.toString(),Toast.LENGTH_SHORT).show()
//            }
//        }
//        appViewModel.loadUserList.observe(this) { loadRandomDish ->
//            loadRandomDish?.let {
//                Log.i("Random Dish Loading", "$loadRandomDish")
//
//                if (loadRandomDish) {
//                    binding.loading.visibility = View.VISIBLE
//                } else {
//                    binding.loading.visibility = View.GONE
//
//                }
//            }
//        }
    }

    private fun setupUI() {
        userListAdapter.addUserList(userList)
        binding.RvList.adapter = userListAdapter
    }
}