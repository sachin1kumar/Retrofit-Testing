package com.manager.retrofitesting.model.api

import com.manager.retrofitesting.model.MainData
import retrofit2.Call

interface ApiHelper {

    fun getEmployeeDetails(): Call<MainData>
}