package com.manager.lastfm.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manager.lastfm.model.api.ApiHelperImpl
import com.manager.lastfm.model.api.RetrofitBuilder
import com.manager.lastfm.util.Constants
import com.manager.myapplication.utils.Resource
import com.manager.retrofitesting.model.Data
import com.manager.retrofitesting.model.MainData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestViewModel : ViewModel() {

    private val result = MutableLiveData<Resource<MainData>?>()

    fun fetchDetails() {
        val apiHelper = ApiHelperImpl(RetrofitBuilder.apiInterface)
        apiHelper.getEmployeeDetails()
            .enqueue(object : Callback<MainData> {
                override fun onFailure(call: Call<MainData>, t: Throwable) {
                    result.postValue(Resource.error("Something went wrong!",null))
                }

                override fun onResponse(call: Call<MainData>, response: Response<MainData>) {
                    result.postValue(Resource.success(response.body()))
                }
            })
    }

    fun getDetail(): LiveData<Resource<MainData>?> {
        return result
    }
}