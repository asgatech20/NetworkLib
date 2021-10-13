package com.asga.network.data

/* Created by ibrahim.ali on 9/28/2021 */
data class BasePaginationResponse<T> (
    val totalCount: Int? = null,
    val list: List<T>? = null
){
    val firstItem: T?
        get() = if (list != null && list.isNotEmpty()) list[0] else null
}