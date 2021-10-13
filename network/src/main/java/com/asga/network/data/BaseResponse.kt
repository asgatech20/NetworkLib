package com.asga.network.data

/* Created by ibrahim.ali on 9/28/2021 */
/*
* BaseResponse class is wrapper for network response
* contains:
*  1- message server message and it maybe empty
* 2- code to check the status of response
* 3- data attribute contains  server response
* 4- success boolean to check if calling has been success of not
* */
data class BaseResponse<T> (
    val message: String? = null,
    val data: T? = null,
    val code: Int? = null,
    val success: Boolean? = null
)