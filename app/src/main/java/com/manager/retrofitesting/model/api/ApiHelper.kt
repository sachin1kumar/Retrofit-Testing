package com.manager.lastfm.model.api

import com.manager.retrofitesting.model.Data
import com.manager.retrofitesting.model.MainData
import retrofit2.Call
import java.util.*

interface ApiHelper {

    fun getEmployeeDetails(): Call<MainData>
}