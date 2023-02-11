package com.example.rxjavaaexample.data.repository

import com.example.rxjavaaexample.data.api.ApiServices
import javax.inject.Inject

class Repository @Inject constructor(var apiServices: ApiServices) {
    fun getUserList() = apiServices.getUserList()
}