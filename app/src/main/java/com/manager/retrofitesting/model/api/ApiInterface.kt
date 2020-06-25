package com.manager.lastfm.model.api

import com.manager.retrofitesting.model.Data
import com.manager.retrofitesting.model.MainData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/api/v1/employees")
    fun getEmployeeDetails(): Call<MainData>
}