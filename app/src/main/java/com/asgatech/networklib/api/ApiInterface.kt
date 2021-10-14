package com.asgatech.networklib.api

import com.asgatech.networklib.model.MockModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @Author: Muhammad Noamany
 * @Date: 10/13/2021
 * @Date: muhammadnoamany@gmail.com
 */
interface ApiInterface {

    @GET("todos/{id}")
    fun getMock(@Path("id") id:Int): Call<MockModel>

}