package com.example.rxjavaaexample.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavaaexample.data.model.UserListResponse.UserListResponse
import com.example.rxjavaaexample.data.repository.Repository
import com.example.rxjavaaexample.utils.Resource
import com.google.gson.Gson
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableSingleObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject


class AppViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    //val loadUserList = MutableLiveData<Boolean>()
    private val _userListResponse = MutableLiveData<Resource<ArrayList<UserListResponse>>>()
    val userListResponse get() = _userListResponse
    //val userListLoadingError = MutableLiveData<String>()

    fun getUserList() {
        //loadUserList.value = true
        _userListResponse.postValue(Resource.loading(null))
        compositeDisposable.add(
            repository.getUserList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Response<ArrayList<UserListResponse>>>() {
                    override fun onSuccess(value: Response<ArrayList<UserListResponse>>) {
//                        loadUserList.value = false
//                        userListResponse.value = value.body()
//                        userListLoadingError.value = "Success"
                        if (value.body()!=null){
                            if (value.code() == 200){
                                _userListResponse.postValue(Resource.success(value.body()!!))
                            }else{
                                val gson = Gson()
                                val message = gson.fromJson(
                                    value.errorBody()!!.charStream(),
                                    UserListResponse::class.java
                                )
                                _userListResponse.postValue(Resource.error(message.toString(),null))
                            }
                        }else{
                            val gson = Gson()
                            val message = gson.fromJson(
                                value.errorBody()!!.charStream(),
                                UserListResponse::class.java
                            )
                            _userListResponse.postValue(Resource.error(message.toString(),null))
                        }
                    }

                    override fun onError(e: Throwable) {
//                        loadUserList.value = false
//                        userListLoadingError.value = "Failed to load"
                        _userListResponse.postValue(Resource.error(e.toString(),null))
                        e.printStackTrace()
                    }
                })
        )
    }
}