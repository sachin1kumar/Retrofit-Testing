package com.manager.lastfm.model.api

import com.manager.retrofitesting.model.Data
import com.manager.retrofitesting.model.MainData
import retrofit2.Call

class ApiHelperImpl(private val apiInterface: ApiInterface) : ApiHelper {

    override fun getEmployeeDetails(): Call<MainData> = apiInterface.getEmployeeDetails()

}