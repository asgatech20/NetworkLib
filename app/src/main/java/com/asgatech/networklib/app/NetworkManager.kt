package com.asgatech.networklib.app

import com.asga.network.helper.Constants
import com.asga.network.module.RetrofitModule
import com.asgatech.networklib.api.ApiInterface
import com.asgatech.networklib.constants.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author: Muhammad Noamany
 * @Date: 1/3/2022
 * @Email: muhammadnoamany@gmail.com
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkManager {
    @Provides
    @Singleton
    fun createRetrofit(): ApiInterface {
        val retrofit =
            RetrofitModule.provideRetrofit(Constants.Lang.EN, null, AppConstants.BASE_URL)
        return retrofit!!.create(ApiInterface::class.java)
    }

}