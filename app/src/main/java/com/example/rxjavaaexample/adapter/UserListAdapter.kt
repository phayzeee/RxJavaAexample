package com.example.rxjavaaexample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaaexample.R
import com.example.rxjavaaexample.data.model.UserListResponse.UserListResponse
import com.example.rxjavaaexample.databinding.UserListRowBinding

class UserListAdapter(val context: Context): RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    private val userList= ArrayList<UserListResponse>()

    fun addUserList(List : ArrayList<UserListResponse>){
        userList.clear()
        userList.addAll(List)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        with(holder){
            binding.tvName.text = userList[position].name
            binding.tvUName.text = userList[position].username
            binding.tvEmail.text = userList[position].email
        }
    }

    override fun getItemCount() = userList.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = UserListRowBinding.bind(view)
    }
}