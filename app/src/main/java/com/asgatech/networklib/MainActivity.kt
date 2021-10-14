package com.asgatech.networklib

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asga.network.helper.Constants
import com.asga.network.module.RetrofitModule
import com.asgatech.networklib.api.ApiInterface
import com.asgatech.networklib.constants.AppConstants
import com.asgatech.networklib.model.MockModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createRetrofit()
    }

    private fun createRetrofit() {
        val retrofit =
            RetrofitModule.provideRetrofit(Constants.Lang.EN, null, AppConstants.BASE_URL)
        val apiInterface = retrofit!!.create(ApiInterface::class.java)
        sendRequest(apiInterface)
    }

    private fun sendRequest(apiInterface: ApiInterface) {
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