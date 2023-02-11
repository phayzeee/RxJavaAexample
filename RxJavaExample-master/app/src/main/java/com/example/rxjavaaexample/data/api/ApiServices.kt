package com.example.rxjavaaexample.data.api

import com.example.rxjavaaexample.data.model.UserListResponse.UserListResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface ApiServices {
    @GET("users")
    fun getUserList(): Single<Response<ArrayList<UserListResponse>>>
}