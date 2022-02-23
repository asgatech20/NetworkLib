package com.asgatech.networklib

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asga.network.helper.Constants
import com.asga.network.module.RetrofitModule
import com.asgatech.networklib.api.ApiInterface
import com.asgatech.networklib.app.NetworkManager
import com.asgatech.networklib.constants.AppConstants
import com.asgatech.networklib.model.MockModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject lateinit var apiInterface: ApiInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createRetrofit()
    }

    private fun createRetrofit() {
        sendRequest()
    }

    private fun sendRequest() {
        apiInterface.getMock(1).enqueue(object : Callback<MockModel> {
            override fun onResponse(
                call: Call<MockModel>,
                response: Response<MockModel>
            ) {
                Toast.makeText(this@MainActivity, Gson().toJson(response.body()), Toast.LENGTH_LONG)
                    .show()
            }

            override fun onFailure(call: Call<MockModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "error happened: " + t.message, Toast.LENGTH_LONG)
                    .show()

            }

        })
    }
}