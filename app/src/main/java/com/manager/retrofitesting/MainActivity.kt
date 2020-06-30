package com.manager.retrofitesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.manager.retrofitesting.viewmodel.RequestViewModel
import com.manager.retrofitesting.util.Status

class MainActivity : AppCompatActivity() {

    lateinit var detail: TextView
    private lateinit var requestViewModel: RequestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        detail = findViewById(R.id.tv_detail)
        initViewModel()
        fetchEmployeeDetails()
    }

    private fun fetchEmployeeDetails() {
        requestViewModel.getDetail().observe(this, androidx.lifecycle.Observer { it ->
            when(it?.status){
                Status.SUCCESS -> {
                    detail.text = "Employee name:"+it.data?.data?.get(0)?.employee_name +"\n"+
                            "Employee age:"+it.data?.data?.get(0)?.employee_age +"\n"+
                            "Employee salary:"+it.data?.data?.get(0)?.employee_salary
                }
                Status.ERROR -> {
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
                else -> {
                    //do nothing for now.
                }
            }

        })

        requestViewModel.fetchDetails()
    }

    private fun initViewModel() {
        requestViewModel = ViewModelProviders.of(this).get(RequestViewModel::class.java)
    }
}